import java.util.*;

public class User extends Field {
    private final List<Integer> streams = new ArrayList<Integer>();
    private final List<CmdType> allowedCommands = new ArrayList<CmdType>() {
        {
            add(CmdType.LIST);
            add(CmdType.LISTEN);
            add(CmdType.RECOMMEND);
            add(CmdType.SURPRISE);
        }
    };

    // Constructor
    public User(Integer ID, String name) {
        super(name, ID);
    }

    // Getters
    public List<Integer> getStreams() { return streams; }
    public List<CmdType> getAllowedCommands() { return allowedCommands; }

    public void listStreams() {
        var streamHistory = new ArrayList<Stream>();
        streams.forEach((it) -> {
            System.err.println("for stream id: " + it);
            var stream = Control.getInstance().getStreams().get(it);
            streamHistory.add(stream);
        });
        System.out.println(formatJSONList(streamHistory));
    }
}
