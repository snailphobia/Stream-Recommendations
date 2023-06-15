public interface ICMDExecute {
    /**
     * @return void
     * will execute the command implemented by a derived class
     * from the following list:
     * ADD, REMOVE, LIST, LISTEN, RECOMMEND, SURPRISE
     */
    public void execute();
}
