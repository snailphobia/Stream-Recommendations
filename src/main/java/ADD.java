import java.util.Arrays;

public class ADD extends Command {
    public ADD(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var streamers = control.getStreamers();
        var streams = control.getStreams();

        if (streamers.get(this.getID()).getAllowedCommands().contains(CmdType.ADD) == false)
            return;

        final String[] newStream = new String[8];
        newStream[0] = this.getArgs()[0]; newStream[1] = this.getArgs()[1];
        newStream[2] = this.getArgs()[2]; newStream[3] = "0"; // number of streams: 0
        newStream[4] = this.getID().toString(); // streamer ID
        newStream[5] = this.getArgs()[3]; newStream[6] = "0"; // date added? [placeholder]
        // stream title needs to be put into one piece from all remaining args
        this.getArgs()[4] = String.join(" ", Arrays.copyOfRange(this.getArgs(), 4, this.getArgs().length));
        newStream[7] = this.getArgs()[4]; // stream title
        final Stream newStreamObj = (Stream) FieldFactory.getInstance().createField(newStream, 2);
        streams.put(newStreamObj.getID(), newStreamObj);
    }
}
