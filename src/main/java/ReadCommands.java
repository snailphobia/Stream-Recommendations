import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.List;
public abstract class ReadCommands {
    private BufferedReader br;

    public ReadCommands(final String path) {
        try {
            this.br = new BufferedReader(new java.io.FileReader(path));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        }
    }

    /**
     * @return void
     * @throws IOException
     * each line of the file will be read and split into
     * 3 parts, first being the ID, second being the command
     * and third being the arguments as a string array
     * 
     * recommendation - the resulting commands will be 
     * aggregated into a list of commands
     */
    public abstract void parseReadline();

    public abstract List<Command> getCommands();
    public BufferedReader getBR() { return br; }
}
