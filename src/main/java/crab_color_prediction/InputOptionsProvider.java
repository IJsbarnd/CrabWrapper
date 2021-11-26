package crab_color_prediction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOptionsProvider implements OptionsProvider {
    private String inputDataFile;

    /**
     * Initializes the datafile fetching.
     */
    public InputOptionsProvider() {
        Scanner scanner = new Scanner(System.in);
        fetchDataFile(scanner);
    }

    /**
     * Fetches the data file from the scanner and throws an exception if the input is invalid.
     * @param scanner A scanner object with the datafile
     */
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

    /**
     * A getter for the inputfile.
     * @return the inputfile
     */
    @Override
    public String getInputDataFile() {
        return inputDataFile;
    }

    /**
     * A getter for the input instance.
     * @return the input instance
     */
    @Override
    public String getInstance() {
        return null;
    }
}
