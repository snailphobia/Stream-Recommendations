import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RECOMMEND extends Command implements AltSortingAlgo {
    public RECOMMEND(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    private final Function<User, List<Integer>> filterEligibleStreamers = (user) -> {
        var eligibleStreamersIDs = new ArrayList<Integer>();

        // add all streamers for which there is a stream in the user's stream history
        user.getStreams().forEach((streamID) -> {
            var stream = Control.getInstance().getStreams().get(streamID);
            eligibleStreamersIDs.add(stream.getStreamerID());
        });
        
        return eligibleStreamersIDs;
    };

    final Function<String, Integer> mapStreamTypes = (string) -> {
        switch (string) {
            case "SONG":
                return 1;
            case "PODCAST":
                return 2;
            case "AUDIOBOOK":
                return 3;
            default:
                return -1; 
        }
    };
    
    private final Comparator<? super Stream> sortingAlgorithm = (a, b) -> (int)(b.getNOfStreams() - a.getNOfStreams());
    
    final BiFunction<User, Map<Integer, Stream>, List<Stream>> filterEligibleStreams = (user, streams) -> {
        var remainingStreams = new ArrayList<Stream>();
        var eligibleStreamersIDs = filterEligibleStreamers.apply(user);

        streams.forEach((key, value) -> {
            if (user.getStreams().contains(key) == false && 
                eligibleStreamersIDs.contains(value.getStreamerID()))
                remainingStreams.add(value);
        });

        // computeScores.accept(user, mapStreamTypes.apply(this.getArgs()[0]));
        // remainingStreams.sort(sortingAlgorithmV2);

        // sort remainingStreams in descending order by number of streams
        remainingStreams.sort(sortingAlgorithm);
        return remainingStreams;
    };

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var streams = control.getStreams();
        var users = control.getUsers();

        User user = users.get(this.getID());
        var remainingStreams = filterEligibleStreams.apply(user, streams);
        int counter = 0;
        List<Stream> listToPrint = new ArrayList<Stream>();
        for (var stream : remainingStreams) {
            if (counter == 5) break;
            if (mapStreamTypes.apply(this.getArgs()[0]) == stream.getStreamType()) {
                listToPrint.add(stream);
                counter++;
            }
        }

        System.out.println(user.formatJSONList(listToPrint));
    }
}
