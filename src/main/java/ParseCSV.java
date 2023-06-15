import java.io.*;

public class ParseCSV extends ReadCSV {
    private Integer type = 0;
    public ParseCSV(String path, Integer type) {
        super(path);
        /* 
         * 1 - Streamer
         * 2 - Stream
         * 3 - User
         */
        this.type = type;
        setReadline();
    }

    @Override
    public void setReadline() {
        var ff = FieldFactory.getInstance();
        readLine = (br) -> {
            try {
                String line = null;
                while ((line = br.readLine()) != null) {
                    line = line.replace("\",\"", "~").replace("\"", "");
                    String[] data = line.split("~");
                    var newInput = ff.createField(data, type);
                    if (type == 1)
                        Control.getInstance().getStreamers().put(newInput.getID(), (Streamer) newInput);
                    else if (type == 2)
                        Control.getInstance().getStreams().put(newInput.getID(), (Stream) newInput);
                    else if (type == 3)
                        Control.getInstance().getUsers().put(newInput.getID(), (User) newInput);
                    else throw new IllegalArgumentException("Invalid type");
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        };

        return;
    }
}
