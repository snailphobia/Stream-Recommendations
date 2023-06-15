import java.util.List;

public class DELETE extends Command {
    public DELETE(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var streamers = control.getStreamers();
        var streams = control.getStreams();
        var users = control.getUsers();

        if (streamers.get(this.getID()).getAllowedCommands().contains(CmdType.DELETE) == false)
            return;
            users.forEach((key, value) -> {
            List<Integer> history = value.getStreams();
            int IDtoDelete = Integer.parseInt(this.getArgs()[0]);
            if (history.contains(IDtoDelete))
                history.remove(history.indexOf(IDtoDelete));
        });
        streams.remove(Integer.parseInt(this.getArgs()[0]));
    }
    
}
