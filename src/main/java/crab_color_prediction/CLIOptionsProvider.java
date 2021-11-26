package crab_color_prediction;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CLIOptionsProvider implements OptionsProvider {
    private static final String INPUTFILE = "Inputfile";
    private static final String INSTANCE = "Instance";
    private static final String HELP = "Help";
    private static final String ATTRIBUTEORDER = "Order";

    private final String[] clArguments;
    private Options options;
    private CommandLine commandLine;
    private String inputfile;
    private String instance;

    /**
     * The constructor method.
     * @param args The commandline arguments
     */
    public CLIOptionsProvider(final String[] args) {
        this.clArguments = args;
        initialize();
    }

    /**
     * Initialize the program options and process the given commandline input.
     */
    public void initialize() {
        buildOptions();
        processCommandLine();
    }

    /**
     * Return the help if requested.
     * @return the help
     */
    public boolean helpRequested() {
        return this.commandLine.hasOption(HELP);
    }

    /**
     * Return the instance if requested.
     * @return the instance
     */
    public boolean instanceGiven() {
        return this.commandLine.hasOption(INSTANCE);
    }

    /**
     * Return the file if requested.
     * @return the file
     */
    public boolean fileGiven() {
        return this.commandLine.hasOption(INPUTFILE);
    }

    /**
     * Return the order of the attributes if requested.
     * @return the order of the attributes
     */
    public boolean attributeOrderRequested() {
        return this.commandLine.hasOption(ATTRIBUTEORDER);
    }

    /**
     * Constructs the help option, file option, instance option and attribute order option.
     */
    private void buildOptions() {
        this.options = new Options();
        Option helpOption = new Option("h", HELP, false, "Returns the help information");
        Option fileOption = new Option("f", INPUTFILE, true, "The inputfile to be classified");
        Option instanceOption = new Option("i", INSTANCE, true, "A string of an instance to be classified.\n" +
                 "the order for the attributes will be shown using -a. \n" +
                 "Id columns are not allowed");
        Option attributeOrder = new Option("a", ATTRIBUTEORDER, false, "the order of the attributes ");

        options.addOption(helpOption);
        options.addOption(fileOption);
        options.addOption(instanceOption);
        options.addOption(attributeOrder);
    }

    /**
     * Parses the commandline options and checks if the input was a file or instance. It then checks if the given file or
     * instance is legal. If so, sets the inputfile or instance variables to the given input. If not, throw an exception.
     */
    private void processCommandLine() {
        try{
            //Parse the commandline arguments
            CommandLineParser parser = new DefaultParser();
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (commandLine.hasOption(INPUTFILE)) {
                if(isLegalDatafile(commandLine.getOptionValue(INPUTFILE))) {
                    this.inputfile = commandLine.getOptionValue(INPUTFILE);
                } else {
                    throw new IllegalArgumentException("Datafile not valid, please give .arff file");
                }
            } else if (commandLine.hasOption(INSTANCE)) {
                if(isLegalInstance(this.commandLine.getOptionValue(INSTANCE))) {
                    this.instance = commandLine.getOptionValue(INSTANCE);
                } else {
                    throw new IllegalArgumentException("Instance not valid.");
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Prints the help.
     */
    public void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Crab subspecies prediction App", options);
    }

    /**
     * Prints the order of the attributes from the Order file. Throws an exception if the file is not found.
     * @throws IOException If the file is not found
     */
    public void printAttHelp() throws IOException {
        BufferedReader r = new BufferedReader( new FileReader( "Data/Order" ) );
        StringBuilder s = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            s.append(line).append("\n");
        }
        System.out.print(s);

    }

    /**
     * Checks if the inputfile is a legal file.
     * @param datafile The inputfile
     * @return true if the file is legal, false if the file is not legal
     */
    private boolean isLegalDatafile(String datafile) {
        try {
            if(datafile.endsWith(".arff")) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Filetype not valid");
        }
        return false;
    }

    /**
     * Checks if the input instance is legal. Throws an exception if the input instance does not match the required
     * amount of attributes.
     * @param instance The input instance
     * @return true if the instance is legal, false if the instance is not legal
     */
    private boolean isLegalInstance(String instance) {
        try {
            String[] instances = commandLine.getOptionValue(INSTANCE).split(",");
            if(instances.length == 7) {
                return true;
            } else {
                throw new IllegalArgumentException("Instance has incorrect amount of values, should have 7 values.");
            }
        } catch (Exception ex) {
            System.out.println();
            return false;
        }
    }

    /**
     * A getter for the inputfile.
     * @return the inputfile
     */
    @Override
    public String getInputDataFile() {
        return this.inputfile;
    }

    /**
     * A getter for the instance.
     * @return the instance
     */
    @Override
    public String getInstance() {
        return this.instance;
    }
}
