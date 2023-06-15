import java.util.List;

public abstract class Field {
    private String name;
    private Integer ID;

    // Constructor
    public Field(String name, Integer ID) {
        this.name = name;
        this.ID = ID;
    }

    // Getters
    public String getName() { return name; }
    public Integer getID() { return ID; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setID(Integer ID) { this.ID = ID; }

    // toString
    @Override
    public String toString() {
        return "Field [ID=" + ID + ", name=" + name + "]";
    }

    
    /**
     * @param streams
     * @return
     * given a list of streams, will return a string in JSON format
     * containing the streams, which are formated as per the toJSON() method
     * this method is defined here because it may be used by a user instance
     */
    String formatJSONList(List<Stream> streams) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[");
        streams.forEach((it) -> {
            sb.append(it.toJSON());
            sb.append(",");
        });
        if (streams.isEmpty() == true) sb.append("]");
        else {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        };
        return sb.toString();
    }
}
