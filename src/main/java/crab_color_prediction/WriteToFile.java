package crab_color_prediction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public class WriteToFile {
    public void start(String inputInstance) {
        String instanceFile = "src/main/data/instances.arff";
        String attributesTemplate = "src/main/data/arffdatatest.arff";
        System.out.println("inputinstance = " + inputInstance);

        WriteToFile instanceToFile = new WriteToFile();
        instanceToFile.copyFile(attributesTemplate, instanceFile);
        instanceToFile.writeToFile(instanceFile, inputInstance);
    }



    private void copyFile(String attributesTemplate, String instanceFile){
        Path path = Paths.get(attributesTemplate);
        try {
            Path copy = Paths.get(instanceFile);
            Files.copy(path, copy, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String instanceFile, String inputInstance) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(instanceFile), StandardOpenOption.APPEND))
        {
            writer.write("\n" + inputInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

