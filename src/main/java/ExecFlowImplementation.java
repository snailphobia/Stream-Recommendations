public class ExecFlowImplementation extends ExecFlow {
    public ExecFlowImplementation() {
        super();
    }

    @Override
    public void setReaders(String basePath, String[] inputsPath) {
        control.setStreamerCSV(new ParseCSV(basePath + inputsPath[0], 1));
        control.setContentCSV(new ParseCSV(basePath + inputsPath[1], 2));
        control.setUserCSV(new ParseCSV(basePath + inputsPath[2], 3));
        control.setCommandFile(new CommandFetch(basePath + inputsPath[3]));
        System.err.println("Readers set");
    }

    @Override
    public void readFiles() {
        control.getStreamerCSV().readLine.accept(control.getStreamerCSV().getBR());
        control.getContentCSV().readLine.accept(control.getContentCSV().getBR());
        control.getUserCSV().readLine.accept(control.getUserCSV().getBR());
        control.getCommandFile().parseReadline();
        System.err.println("Files read");
    }

    @Override
    public void executeCommands() {
        control.getCommandFile().getCommands().forEach((it) -> { it.execute(); });
    }

    @Override
    public void closeReaders() {
        control.getStreamerCSV().closeReader();
        control.getContentCSV().closeReader();
        control.getUserCSV().closeReader();
        System.err.println("Readers closed");
    }
}
