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

    public CLIOptionsProvider(final String[] args) {
        this.clArguments = args;
        initialize();
    }

    public void initialize() {
        buildOptions();
        processCommandLine();
    }

    public boolean helpRequested() {
        return this.commandLine.hasOption(HELP);
    }

    public boolean instanceGiven() {
        return this.commandLine.hasOption(INSTANCE);
    }

    public boolean fileGiven() {
        return this.commandLine.hasOption(INPUTFILE);
    }

    public boolean attributeOrderRequested() {return this.commandLine.hasOption(ATTRIBUTEORDER); }

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

    private void processCommandLine() {
        try{
            CommandLineParser parser = new DefaultParser();
            this.commandLine = parser.parse(this.options, this.clArguments);
            if (commandLine.hasOption(INPUTFILE)) {
                if(isLegalDatafile(commandLine.getOptionValue(INPUTFILE))) {
                    System.out.println("Classifying " + this.commandLine.getOptionValue(INPUTFILE));
                    this.inputfile = commandLine.getOptionValue(INPUTFILE);
                } else {
                    throw new IllegalArgumentException("Datafile not valid, please give .arff file");
                }
            } else if (commandLine.hasOption(INSTANCE)) {
                if(isLegalInstance(this.commandLine.getOptionValue(INSTANCE))) {
                    System.out.println("Classifying " + this.commandLine.getOptionValue(INSTANCE));
                    this.instance = commandLine.getOptionValue(INSTANCE);
                } else {
                    throw new IllegalArgumentException("Instance not valid.");
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Crab subspecies prediction App", options);
    }

    public void printAttHelp() throws IOException {
        BufferedReader r = new BufferedReader( new FileReader( "Data/Order" ) );
        StringBuilder s = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            s.append(line).append("\n");
        }
        System.out.print(s);

    }

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


    @Override
    public String getInputDataFile() {
        return this.inputfile;
    }

    @Override
    public String getInstance() {
        return this.instance;
    }
}
