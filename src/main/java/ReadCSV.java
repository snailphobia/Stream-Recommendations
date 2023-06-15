import java.util.function.Consumer;
import java.io.*;

public abstract class ReadCSV {
    public Consumer<BufferedReader> readLine;
    private BufferedReader br = null;

    public ReadCSV(final String path) {
        try {
            this.br = new BufferedReader(new java.io.FileReader(path));
            br.readLine(); // skip the first line, which is the header
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + path);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * @return void
     * @throws IOException
     * closes the reader object if it exists
     */
    public void closeReader() {
        try {
            if (br == null)
                throw new Exception("Reader field is null, nothing to close");
            br.close();
        } catch (IOException e) {
            System.err.println("Error closing file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * @return void
     * @throws IOException
     * this method is used to set the lambda function that will be used to read the file
     * the resulting line will be passed further to be processed according to 
     * the type of the file provided
     */
    public abstract void setReadline();

    public BufferedReader getBR() { return br; } 
}
