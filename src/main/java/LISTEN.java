public class LISTEN extends Command {
    public LISTEN(String command, Integer ID, String[] args) {
        super(command, ID, args);
    }

    @Override
    public void execute() {
        Control control = Control.getInstance();
        var streams = control.getStreams();
        var users = control.getUsers();

        users.get(this.getID()).getStreams().add(Integer.parseInt(this.getArgs()[0]));
        streams.get(Integer.parseInt(this.getArgs()[0])).incrementNOfStreams.run();
    }
}
