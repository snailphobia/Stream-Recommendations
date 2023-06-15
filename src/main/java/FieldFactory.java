public class FieldFactory {
    // singleton
    private static FieldFactory instance = null;

    // constructor
    private FieldFactory() {}

    // get instance
    public static FieldFactory getInstance() {
        if(instance == null) {
            instance = new FieldFactory();
        }
        return instance;
    }

    /**
     * @param data - raw arguments passed from the file
     * @param type - the type of the field to create
     * @return Field - the corresponding Field type
     * will work as a factory for the fields
     * this method will create the field according to the type
     * and return it as a Field type
     */
    public Field createField(final String[] data, Integer type) {
        switch (type) {
            case 3:
                var ret = new User(Integer.parseInt(data[0]), data[1]);
                addStreamIDs(ret, data[2]);
                return ret;
            case 1:
                return new Streamer(Integer.parseInt(data[0]), data[2], Integer.parseInt(data[1]));
            case 2:
                return new Stream(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                                    Integer.parseInt(data[2]), Long.parseLong(data[3]), 
                                    Integer.parseInt(data[4]), Long.parseLong(data[5]), 
                                    Long.parseLong(data[6]), data[7]);
            default:
                return null;
        }
    }

    /**
     * @param user - the user to add the stream IDs to
     * @param streamIDs - a string containing the IDs of the streams to add
     * @return void
     * this method will split the string of stream IDs and add them to the user's stream list
     */
    private void addStreamIDs(Field user, String streamIDs) {
        String[] idList = streamIDs.split(" ");
        for (String id : idList) {
            ((User) user).getStreams().add(Integer.parseInt(id));
        }
        return;
    }

    public void youShouldKillYourself() {
        instance = null;
    }
}
