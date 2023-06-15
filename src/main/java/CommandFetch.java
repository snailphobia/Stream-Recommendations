import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandFetch extends ReadCommands {
    private List<Command> commands = new ArrayList<Command>();

    public CommandFetch(final String path) {
        super(path);
    }

    /* (non-Javadoc)
     * @see ReadCommands#parseReadline()
     */
    @Override
    public void parseReadline() {
        try {
            String line;
            while ((line = getBR().readLine()) != null) {
                String[] splitLine = line.split(" ");
                // the arguments are splitLine starting from index 2 to last
                Command cmd = newCommandInstance(splitLine[1], Integer.parseInt(splitLine[0]), 
                                        Arrays.copyOfRange(splitLine, 2, splitLine.length));
                commands.add(cmd);
                System.err.println(cmd.getCommand() + " " + cmd.getType() + " " + cmd.getArgs().length + " " + cmd.getID());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * @param command - the command type
     * @param ID - the user/streamer ID (depends on the command type)
     * @param args - the arguments for the command
     * @return Command
     * creates a new command instance according to the command type
     * and returns it; if the command type is invalid, returns null
     * and prints an error message
     */
    private Command newCommandInstance(String command, Integer ID, String[] args) {
        CmdType cType = CmdType.valueOf(command);
        switch (cType) {
            case LIST:
                return new LIST(command, ID, args);
            case ADD:
                return new ADD(command, ID, args);
            case DELETE:
                return new DELETE(command, ID, args);
            case LISTEN:
                return new LISTEN(command, ID, args);
            case RECOMMEND:
                return new RECOMMEND(command, ID, args);
            case SURPRISE:
                return new SURPRISE(command, ID, args);

            default:
                System.err.println("Invalid command type: " + cType);
                return null;
        }
    }

    public List<Command> getCommands() { return commands; }
}
