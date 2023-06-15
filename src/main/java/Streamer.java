import java.util.ArrayList;
import java.util.List;

public class Streamer extends Field {
    private final Integer streamerType;
    private final List<CmdType> allowedCommands = new ArrayList<CmdType>() {
        {
            add(CmdType.LIST);
            add(CmdType.ADD);
            add(CmdType.DELETE);
        }
    };
    
    // Constructor
    public Streamer(Integer streamerType, String name, Integer ID) {
        super(name, ID);
        this.streamerType = streamerType;
    }

    // Getters
    public Integer getStreamerType() { return streamerType; }
    public List<CmdType> getAllowedCommands() { return allowedCommands; }

    public void listStreams() {
        var streams = Control.getInstance().getStreams();
        final var streamsFound = new ArrayList<Stream>();
        System.err.println("Listing streams for streamer: " + this.getID());
        streams.forEach((k, v) -> {
            if (v.getStreamerID().equals(this.getID())) {
                streamsFound.add(v);
            }
        });
        System.out.println(formatJSONList(streamsFound));
    }
}
