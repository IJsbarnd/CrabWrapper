package crab_color_prediction;

import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;

public class WekaUsage {
    public static String modelFile = "src/main/data/SimpleModel.model";
    private final String inputfile;

    /**
     * The constructor method for WekaUsage.
     * @param inputfile
     */
    public WekaUsage(String inputfile) {
        this.inputfile = inputfile;
    }

    /**
     * The method that uses the other methods to classify the instance/file.
     */
    protected void start() {
        String datafile = "src/main/data/crabdata.arff";
        try {
            SimpleLogistic simpleModelFile = loadClassifier();
            Instances unknownInstances = loadArff(inputfile);
            classifyNewInstances(simpleModelFile, unknownInstances);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the input file and returns the instances from the file.
     * @param inputfile the input file.
     * @return the instances from the file.
     * @throws IOException if the file is unreadable
     */
    private Instances loadArff(String inputfile) throws IOException {
        try{
            //Make a datasource of the inputfile
            DataSource source = new DataSource(inputfile);
            Instances instances = source.getDataSet();
            instances.setClassIndex(instances.numAttributes() - 1);
            return instances;
        } catch (Exception ex) {
            throw new IOException("File unreadable");
        }
    }

    /**
     * Read the model file.
     * @return the model file
     * @throws Exception if the file is not found
     */
    private SimpleLogistic loadClassifier() throws Exception {
        return (SimpleLogistic) weka.core.SerializationHelper.read(modelFile);
    }

    /**
     * Classifies the new instances.
     * @param reg the simplelogistics model
     * @param unknownInstances the instances to be classified.
     * @throws Exception if the modelfile is unreadable.
     */
    private void classifyNewInstances(SimpleLogistic reg, Instances unknownInstances) throws Exception {
        Instances labeled = new Instances(unknownInstances);
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double classlabel = reg.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(classlabel);
        }
        System.out.println(labeled);
    }
}

