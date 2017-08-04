import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.io.*;


/**
 * Convert binary-file into base64-text-file<br/>
 * To override defaults execute by: java ConvertBinaryFileIntoTextFile binary-file base64-text-file
 */
public class ConvertBinaryFileIntoTextFile
{

    public static void main(String[] args) throws Exception
    {
        //validate
        String inputFile = (args != null && args.length > 0) ? args[0] : "binary-input.file";
        String outputFile = (args != null && args.length > 1) ? args[1] : "output-text.file";
        if( !( new File(inputFile) ).exists() ){
            throw new RuntimeException("Input file '"+inputFile+"' doesn't exist at path: " + (new File(inputFile)).getAbsolutePath() );
        }

        //convert
        System.out.println("converting binary file: '" + inputFile + "' into base64-string file: '" + outputFile + "'");
        String fileAsBase64String = loadFileAsBase64String(inputFile);

        //write output
        writeStringToFile(outputFile, fileAsBase64String);
        System.out.println("DONE: base64-text-file '" + outputFile + "', " + fileAsBase64String.length() + " chars long was created");
    }

    private static String loadFileAsBase64String(String fileName) throws Exception
    {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ByteArrayOutputStream bos = new ByteArrayOutputStream())
        {
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1; )
            {
                bos.write(buf, 0, readNum); //no doubt here is 0
            }

            //NOTE: Always remember to encode your base 64 string in utf8 format other wise you may always get problems on browser.
            return StringUtils.newStringUtf8(Base64.encodeBase64(bos.toByteArray()));
        }
    }

    private static void writeStringToFile(String outputFilename, String value) throws Exception
    {
        FileWriter fileWriter = new FileWriter(outputFilename);
        fileWriter.write(value);
        fileWriter.close();
    }
}
