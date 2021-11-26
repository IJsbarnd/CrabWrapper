package crab_color_prediction;
import java.io.IOException;


public class Color_prediction {
    /**
     * The main method. Starts the program using the input from the commandline.
     * @param args The command line arguments.
     * @throws IOException when we can't read a file or something like that.
     **/
    public static void main(String[] args) throws IOException {
        CLIOptionsProvider optionsProvider = new CLIOptionsProvider(args);
        //In case of no arguments
        if (args.length == 0) {
            optionsProvider.printHelp();
        }
        //If the help was requested
        if (optionsProvider.helpRequested()) {
            optionsProvider.printHelp();
        }
        //If the order of the attributes was requested
        if (optionsProvider.attributeOrderRequested()){
            optionsProvider.printAttHelp();
        }

        if (optionsProvider.instanceGiven()) {
            //Write instance to template file
            String instanceFile = "src/main/data/instances.arff";
            WriteToFile instanceToFile = new WriteToFile();
            instanceToFile.start(optionsProvider.getInstance());
            //Use new file on Wekarunner
            WekaUsage wekaRunner = new WekaUsage(instanceFile);
            wekaRunner.start();

        }
        if (optionsProvider.fileGiven()) {
            //Use file on WekaRunner
            WekaUsage wekarunner = new WekaUsage(optionsProvider.getInputDataFile());
            wekarunner.start();
        }

    }

}
