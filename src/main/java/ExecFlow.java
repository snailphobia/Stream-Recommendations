public abstract class ExecFlow {
    final Control control = Control.getInstance();

    /**
     * @param basePath - the base path of the files
     * @param inputsPath - the path of the files
     * @return void
     * sets the reader devices according to their types
     */
    public abstract void setReaders(String basePath, String[] inputsPath);

    /**
     * @return void
     * uses the lambda implemented inside each device
     * to read the csv files and parses the commands
     */
    public abstract void readFiles();
    

    /**
     * @return void
     * each command has an execute lambda implemented
     * according to the command type
     */
    public abstract void executeCommands();


    /**
     * @return void
     * closes the reader devices, called when done
     */
    public abstract void closeReaders();

    /**
     * @param basePath - the base path of the files to be read
     * @param inputsPath - the names passed as arguments from the command line
     * @return void
     * this method is used to run all the above methods automatically
     */
    public final void send_it(String basePath, String[] inputsPath) {
        setReaders(basePath, inputsPath);
        readFiles();
        executeCommands();
        closeReaders();

        // delete the instances
        control.youShouldKillYourself();
        FieldFactory.getInstance().youShouldKillYourself();
    }
}
