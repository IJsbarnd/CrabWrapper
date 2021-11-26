package crab_color_prediction;

public class InputRequestUsage {
    public static void main(final String[] args) {
        OptionsProvider optionsProvider = new InputOptionsProvider();
        MessageSender Sender = new MessageSender(optionsProvider);
        Sender.start();
    }
}
