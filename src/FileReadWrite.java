/*
* This class is implemented to read and write files to
* */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class FileReadWrite {

    private static String folderPath;

    static String ReadFile(String fileName)
    {
        folderPath = pow.folderPath;
        String filePath = folderPath+fileName;
        StringBuilder ReadData = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
                stream.forEach(ReadData::append);
        }
        catch (IOException e)
        {
            System.out.println("File does not exist.");
        }
        if(ReadData.toString().length()==0)
        {
            return fileName + " is Empty or Does not exist.";
        }
        else {
            return ReadData.toString();
        }

    }
    static void WriteFile(String fileName, String DataToWrite)
    {
        String filePath = folderPath+fileName;
        try
        {
            Path file = Paths.get(filePath);
            Files.write(file, DataToWrite.getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
