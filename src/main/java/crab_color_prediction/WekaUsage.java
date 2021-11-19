package crab_color_prediction;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;

public class WekaUsage {
    public static String modelFile = "src/main/data/J48model.model";
    private final String inputfile;

    public WekaUsage(String inputfile) {
        this.inputfile = inputfile;
    }

    protected void start() {
        String datafile = "src/main/data/crabdata.arff";
        try {
            System.out.println(inputfile);
            J48 modelSourceFile = loadClassifier();
            Instances unknownInstances = loadArff(inputfile);
            //System.out.println("\nunclassified unknownInstances = \n" + unknownInstances);
            classifyNewInstances(modelSourceFile, unknownInstances);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Instances loadArff(String inputfile) throws IOException {
        try{
            System.out.println(inputfile);
            DataSource source = new DataSource(inputfile);
            Instances instances = source.getDataSet();
            instances.setClassIndex(instances.numAttributes() - 1);
            return instances;
        } catch (Exception ex) {
            throw new IOException("File unreadable");
        }
    }

    private void printInstances(Instances instances) {
        for (int i = 0; i < instances.numAttributes(); i++) {
            System.out.println("Instance" + i + ":" + instances.attribute(i));
        }
    }

    private J48 buildClassifier(Instances instances) throws Exception {
        String[] options = new String[1];
        options[0] = "-U";
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(instances);
        return tree;
    }

    private void saveClassifier(J48 j48) throws Exception {
        weka.core.SerializationHelper.write(modelFile, j48);
    }

    private J48 loadClassifier() throws Exception {
        return (J48) weka.core.SerializationHelper.read(modelFile);
    }

    private void classifyNewInstances(J48 tree, Instances unknownInstances) throws Exception {
        Instances labeled = new Instances(unknownInstances);
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double classlabel = tree.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(classlabel);
        }
        System.out.println(labeled);
    }
}

