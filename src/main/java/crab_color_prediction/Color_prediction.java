package crab_color_prediction;
import java.io.IOException;


public class Color_prediction {
    public static void main(String[] args) throws IOException {
        CLIOptionsProvider optionsProvider = new CLIOptionsProvider(args);

        if (args.length == 0) {
            optionsProvider.printHelp();
        }

        if (optionsProvider.helpRequested()) {
            optionsProvider.printHelp();
        }

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
