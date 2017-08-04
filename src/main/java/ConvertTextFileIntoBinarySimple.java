import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Almost same as {@link ConvertTextFileIntoBinary} but shorter for manual rewrite if github is blocked<br/>
  */
public class ConvertTextFileIntoBinarySimple
{
    public static void main(String[] args) throws Exception
    {
        String inputFile = "input-text.file";
        String outputFile = "output-binary.file";

        byte[] bytesFromFile = Files.readAllBytes( Paths.get(inputFile) );
        byte[] decoded = Base64.decodeBase64(bytesFromFile);

        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(decoded);
        fos.close();
    }
}
