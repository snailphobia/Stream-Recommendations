import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public interface AltSortingAlgo {
    final Map<Integer, Integer> scores = new HashMap<>();

    public final BiConsumer<User, Integer> computeScores = (user, typeOfStream) -> {
        final Control control = Control.getInstance();
        Long totalStreams = 0L, numOfStreams = 0L;
        Map<Integer, Double> genreScore = new HashMap<>(), 
                            streamerScore = new HashMap<>(),
                            popularityScore = new HashMap<>();
        
        for (Map.Entry<Integer, Stream> entry : control.getStreams().entrySet()) {
            if (entry.getValue().getStreamType() == typeOfStream) {
                totalStreams += entry.getValue().getNOfStreams();
                numOfStreams += 1;
            }
        }

        for (Map.Entry<Integer, Stream> entry : control.getStreams().entrySet()) {
            if (entry.getValue().getStreamType() == typeOfStream && user.getStreams().contains(entry.getValue().getID())) {
                genreScore.put(entry.getValue().getGenre(), 
                                genreScore.getOrDefault(entry.getValue().getGenre(), 0.0) + 1.0 / numOfStreams);
                streamerScore.put(entry.getValue().getStreamerID(), 
                                streamerScore.getOrDefault(entry.getValue().getStreamerID(), 0.0) + 1.0 / numOfStreams);
                popularityScore.put(entry.getValue().getID(), 
                                popularityScore.getOrDefault(entry.getValue().getID(), 0.0) + entry.getValue().getNOfStreams() / totalStreams);
            }
        }
        
        control.getStreams().forEach((sID, s) -> {
            scores.put(sID, (int) (genreScore.getOrDefault(s.getGenre(), 0.0) + streamerScore.getOrDefault(s.getStreamerID(), 0.0) + popularityScore.getOrDefault(sID, 0.0)));
        });
    };

    /**
     * @return integer value - as per comparator standard
     * this lambda will sort the streams also based on their genre and
     * favourite streamers, using a proportional value mechanic:
     * from the streams that have been listened to by the user, each
     * genre will score proportionally to how often it appears, as well as
     * each streamer; for each stream, the score is:
     * genreScore + streamerScore + popularityScore
     * genreScore = numOfGenreAppearances / totalNOfStreams
     * streamerScore = numOfStreamerAppearances / totalNOfStreams
     * popularityScore = stream.getNOfStreams() / totalNOfStreams 
     * 
     * must first run preparation method to calculate scores based on UID
	 * using the computeScores method defined here
     */
    public final Comparator<? super Stream> sortingAlgorithmV2 = (A, B) -> {
        return scores.get(B.getID()) - scores.get(A.getID());
    };


}
