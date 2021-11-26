package crab_color_prediction;


public class MessageSender {
    private final OptionsProvider optionsProvider;

    /**
     * A constructor for MessageSender.
     * @param optionsProvider the options provider object
     */
    public MessageSender(final OptionsProvider optionsProvider) {
        this.optionsProvider = optionsProvider;
    }

    /**
     * Throws an exception if there is no optionsProvider object and prints the user settings if there is.
     */
    public void start() {
        if (optionsProvider == null) {
            throw new IllegalStateException("Options controller not found");
        } else {
            printUserSettings();
        }
        System.out.println("Program finished");

    }

    /**
     * The method that prints the inputfile name.
     */
    private void printUserSettings() {
        String inputFile = optionsProvider.getInputDataFile();
        System.out.println("Your input file: " + inputFile);
    }
}
