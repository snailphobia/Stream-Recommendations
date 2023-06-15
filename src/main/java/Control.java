import java.util.*;

public class Control {
    // singleton
    private static Control instance = null;
    private ReadCSV streamerCSV, contentCSV, userCSV;
    private ReadCommands commandFile;
    private final Map<Integer, Streamer> streamers = new HashMap<Integer, Streamer>();
    private final Map<Integer, Stream> streams = new HashMap<Integer, Stream>();
    private final Map<Integer, User> users = new HashMap<Integer, User>();

    // constructor
    private Control() {}

    // get instance
    public static Control getInstance() {
        if(instance == null) {
            instance = new Control();
        }
        return instance;
    }

    // fuck it we ball
    public void youShouldKillYourself() {
        instance = null;
    }

    // set & get
    public void setStreamerCSV(ReadCSV streamerCSV) { this.streamerCSV = streamerCSV; }
    public void setContentCSV(ReadCSV contentCSV) { this.contentCSV = contentCSV; }
    public void setUserCSV(ReadCSV userCSV) { this.userCSV = userCSV; }
    public void setCommandFile(ReadCommands commandFile) { this.commandFile = commandFile; }
    public ReadCSV getStreamerCSV() { return streamerCSV; }
    public ReadCSV getContentCSV() { return contentCSV; }
    public ReadCSV getUserCSV() { return userCSV; }
    public ReadCommands getCommandFile() { return commandFile; }

    public Map<Integer, Streamer> getStreamers() { return streamers; }
    public Map<Integer, Stream> getStreams() { return streams; }
    public Map<Integer, User> getUsers() { return users; }
}
