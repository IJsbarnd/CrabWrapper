package crab_color_prediction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public class WriteToFile {
    /**
     * Start the writing to the new file.
     * @param inputInstance the input instance
     */
    public void start(String inputInstance) {
        String instanceFile = "src/main/data/instances.arff";
        String attributesTemplate = "src/main/data/arffdatatest.arff";
        System.out.println("inputinstance = " + inputInstance);

        WriteToFile instanceToFile = new WriteToFile();
        instanceToFile.copyFile(attributesTemplate, instanceFile);
        instanceToFile.writeToFile(instanceFile, inputInstance);
    }

    /**
     * Make a copy of the file and replace it in the path.
     * @param attributesTemplate the template file to use as a template for the new file
     * @param instanceFile the instance file to write to
     */
    private void copyFile(String attributesTemplate, String instanceFile){
        Path path = Paths.get(attributesTemplate);
        try {
            Path copy = Paths.get(instanceFile);
            Files.copy(path, copy, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write to the new instance file.
     * @param instanceFile the file to write to
     * @param inputInstance the instance to write to the file
     */
    private void writeToFile(String instanceFile, String inputInstance) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(instanceFile), StandardOpenOption.APPEND))
        {
            writer.write("\n" + inputInstance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

