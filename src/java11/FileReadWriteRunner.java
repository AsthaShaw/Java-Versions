package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadWriteRunner {

    public static void main(String[] args) {

        //readString and writeString makes reading and writing strings so easy
        Path path= Paths.get("src/java11/sample.txt");
        try {
            String fileContent= Files.readString(path);
            System.out.println(fileContent);

            String newFileContent=fileContent.replace("Line","Lines");
            Path newFilePath=Paths.get("src/java11/sample-new.txt");
            Files.writeString(newFilePath, newFileContent);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
