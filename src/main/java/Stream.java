import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public class Stream extends Field implements IJSON {
    private Integer streamType, genre, streamerID;
    private Long nOfStreams, length, dateAdded;

    // Constructor
    
    // "streamType","id","streamGenre","noOfStreams","streamerId","length","dateAdded","name"
    public Stream(int streamType, int ID, int streamGenre, long nOfStreams, int streamerId, long length,
            long dateAdded, String name) {
                super(name, ID);
                this.streamType = streamType;
                this.genre = streamGenre;
                this.nOfStreams = nOfStreams;
                this.streamerID = streamerId;
                this.length = length;
                this.dateAdded = dateAdded;
    }

    // Getters
    public Integer getStreamType() { return streamType; }
    public Integer getGenre() { return genre; }
    public Integer getStreamerID() { return streamerID; }
    public Long getNOfStreams() { return nOfStreams; }
    public Long getLength() { return length; }
    public Long getDateAdded() { return dateAdded; }

    // Setters
    public void setStreamType(Integer streamType) { this.streamType = streamType; }
    public void setGenre(Integer genre) { this.genre = genre; }
    public void setStreamerID(Integer streamerID) { this.streamerID = streamerID; }
    public void setNOfStreams(Long nOfStreams) { this.nOfStreams = nOfStreams; }
    public void setLength(Long length) { this.length = length; }
    public void setDateAdded(Long dateAdded) { this.dateAdded = dateAdded; }

    public final Runnable incrementNOfStreams = () -> {
        this.setNOfStreams(this.getNOfStreams() + 1);
    };

    @Override
    public String toJSON() {
        Function<Long, String> secondsToTime = (seconds) -> {
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            long secs = seconds % 60;
            if (hours == 0) {
                if (minutes == 0)
                    return String.format("%02d", secs);
                return String.format("%02d:%02d", minutes, secs);
            }
            return String.format("%02d:%02d:%02d", hours, minutes, secs);
        };

        StringBuilder sb = new StringBuilder("");
        sb.append("{");
        sb.append("\"id\":\"" + 
                this.getID() + "\"," +
                "\"name\":\"" +
                this.getName() + "\"," +
                "\"streamerName\":\"" +
                Control.getInstance().getStreamers().get(this.getStreamerID()).getName() + "\"," +
                "\"noOfListenings\":\"" +
                this.getNOfStreams() + "\"," +
                "\"length\":\"" +
                secondsToTime.apply(this.getLength()) + "\"," +
                "\"dateAdded\":\"" +
                new SimpleDateFormat("dd-MM-yyyy").format(new Date(this.getDateAdded() * 1000)) + "\""
                ); // add -1 to the amount of milliseconds to unfuck the timezone
        sb.append("}");
        return sb.toString();
    }
}
