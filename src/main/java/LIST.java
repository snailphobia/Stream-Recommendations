public class LIST extends Command {
    public LIST(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var streamers = control.getStreamers();
        var users = control.getUsers();
        // two cases, list for user or list for streamer
        if (streamers.get(this.getID()) != null && streamers.get(this.getID()).getAllowedCommands().contains(CmdType.LIST)) {
            System.err.println("found streamer by ID: " + this.getID() + ", name found: " + streamers.get(this.getID()).getName());
            streamers.get(this.getID()).listStreams();
        }
        if (users.get(this.getID()) != null && users.get(this.getID()).getAllowedCommands().contains(CmdType.LIST)) {
            System.err.println("found user by ID: " + this.getID() + ", name found: " + users.get(this.getID()).getName());
            users.get(this.getID()).listStreams();
        }
    }
}
