public class ProiectPOO {

    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("Nothing to read here");
            return;
        }

        // set the files to read
        final String basePath = "src/main/resources/";
        final ExecFlow execDev = new ExecFlowImplementation();

        execDev.send_it(basePath, args);
    }
}
