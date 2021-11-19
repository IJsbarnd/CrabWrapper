package crab_color_prediction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOptionsProvider implements OptionsProvider {
    private String inputDataFile;

    public InputOptionsProvider() {
        initialize();
    }

    private void initialize() {
        Scanner scanner = new Scanner(System.in);
        fetchDataFile(scanner);
    }

    private void fetchDataFile(final Scanner scanner) {
        String dataFile = "";
        System.out.println("Name of the arff datafile:");
        try {
            dataFile = scanner.next();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input!");
            System.exit(0);
        }
        this.inputDataFile = dataFile;
    }

    @Override
    public String getInputDataFile() {
        return inputDataFile;
    }

    @Override
    public String getInstance() {
        return null;
    }
}
