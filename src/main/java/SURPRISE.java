import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SURPRISE extends RECOMMEND {
    public SURPRISE(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    private final Function<User, List<Integer>> filterEligibleStreamers = (user) -> {
        var eligibleStreamersIDs = new ArrayList<Integer>();
        var streamers = Control.getInstance().getStreamers();

        // copy all contents of streamers to eligibleStreamersIDs
        streamers.forEach((key, value) -> eligibleStreamersIDs.add(key));

        // remove all streamers for which there is a stream in the user's stream history
        user.getStreams().forEach((streamID) -> {
            var stream = Control.getInstance().getStreams().get(streamID);
            eligibleStreamersIDs.remove(stream.getStreamerID());
        });
        
        return eligibleStreamersIDs;
    };
    
    private final Comparator<? super Stream> sortingAlgorithm = (stream1, stream2) -> {
        if (stream1.getDateAdded().equals(stream2.getDateAdded()))
            return (int)(stream2.getNOfStreams() - stream1.getNOfStreams());
        return stream2.getDateAdded().compareTo(stream1.getDateAdded());
    };
    
    private final BiFunction<User, Map<Integer, Stream>, List<Stream>> filterEligibleStreams = (user, streams) -> {
        var eligibleStreamersIDs = filterEligibleStreamers.apply(user);
        var eligibleStreams = new ArrayList<Stream>();
        streams.forEach((key, value) -> {
            if (eligibleStreamersIDs.contains(value.getStreamerID()))
            eligibleStreams.add(value);
        });
        
        eligibleStreams.sort(sortingAlgorithm);
        return eligibleStreams;
    };

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var user = control.getUsers().get(this.getID());
        var streams = control.getStreams();

        var eligibleStreams = filterEligibleStreams.apply(user, streams);
        var printList = new ArrayList<Stream>();
        int counter = 0;
        for (var stream : eligibleStreams) {
            if (counter == 3) break;
            if (mapStreamTypes.apply(this.getArgs()[0]) == stream.getStreamType()) {
                printList.add(stream);
                counter++;
            }
        }
        System.out.println(user.formatJSONList(printList));
    }
}
