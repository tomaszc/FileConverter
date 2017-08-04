import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Convert base64-text-file into binary-file<br/>
 * To override defaults execute by: java ConvertTextFileIntoBinary base64-text-file binary-file
 */
public class ConvertTextFileIntoBinary
{
    public static void main(String[] args) throws Exception
    {
        //validate
        String inputFile = (args != null && args.length > 0) ? args[0] : "input-text.file";
        String outputFile = (args != null && args.length > 1) ? args[1] : "output-binary.file";
        if( !( new File(inputFile) ).exists() ){
            throw new RuntimeException("Input file '"+inputFile+"' doesn't exist at path: " + (new File(inputFile)).getAbsolutePath() );
        }

        //convert
        System.out.println("converting base64-text-file: '" + inputFile + "' into binary-file: '" + outputFile + "'");
        byte[] decodedFromBase64 = readBase64FileIntoByteArray(inputFile);

        //write output
        writeBinaryFile(outputFile, decodedFromBase64);
        System.out.println("DONE: binary-file '" + inputFile + ", " + decodedFromBase64.length + "long was created");
    }

    private static byte[] readBase64FileIntoByteArray(String filename) throws Exception
    {
        Path pathToFile = Paths.get(filename);
        byte[] bytesFromFile = Files.readAllBytes(pathToFile);
        return Base64.decodeBase64(bytesFromFile);
    }

    private static void writeBinaryFile(String outputFilename, byte[] bytes) throws Exception
    {
        FileOutputStream fos = new FileOutputStream(outputFilename);
        fos.write(bytes);
        fos.close();
    }
}
