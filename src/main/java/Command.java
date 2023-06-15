public abstract class Command implements ICMDExecute {
    private String command;
    private Integer ID;
    private String[] args;
    private CmdType type;

    public Command(String command, Integer ID, String[] args) {
        this.command = command;
        this.ID = ID;
        this.args = args;
        this.type = CmdType.valueOf(command.toUpperCase());
    }

    public String getCommand() { return command; }
    public Integer getID() { return ID; }
    public String[] getArgs() { return args; }
    public CmdType getType() { return type; }

    /* (non-Javadoc)
     * @see ICMDExecute#execute()
     */
    public abstract void execute();
}
