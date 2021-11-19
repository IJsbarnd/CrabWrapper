package crab_color_prediction;


public class MessageSender {
    private final OptionsProvider optionsProvider;
    public MessageSender(final OptionsProvider optionsProvider) {
        this.optionsProvider = optionsProvider;
    }
    public void start() {
        if (optionsProvider == null) {
            throw new IllegalStateException("Options controller not found");
        } else {
            printUserSettings();
        }
        System.out.println("Program finished");

    }
    private void printUserSettings() {
        String inputFile = optionsProvider.getInputDataFile();
        System.out.println("Your input file: " + inputFile);
    }
}
