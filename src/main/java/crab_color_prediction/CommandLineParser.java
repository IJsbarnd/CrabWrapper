package crab_color_prediction;

public class CommandLineParser {
    private CommandLineParser(){

    }
    public static void main(final String[] args) {
        try {
            CLIOptionsProvider optionsProvider = new CLIOptionsProvider(args);
            if (optionsProvider.helpRequested()) {
                optionsProvider.printHelp();
            }
        } catch (IllegalStateException ex) {
            System.out.println("Something went wrong!");
        }
    }
}
