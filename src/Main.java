import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.lang.reflect.Array;

public class Main extends Application{

    /**
     * start instantiate the stage needed for fileChooser
     * FileChooser allows user to select a file of text data to be read and
     * to write the last line of the input file to an output file of the same name + _out
     * @param stage for UI
     * */
    public void start(Stage stage){
        File userDirectory = new File(System.getProperty("user.dir"));
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(userDirectory);
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Select File");
        File selectedFile =  fileChooser.showOpenDialog(stage);
        String printLine = getLastFileLine(selectedFile);
        try {
            File file = createFile(getFileName(selectedFile.getName()),userDirectory);
            writeToFile(file, printLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Writes a single string to an exsisting file
     *
     * @param fileName the name of the exsisting file
     *                 @param fileText the String/text to be written
     * */
    private void writeToFile(File fileName, String fileText){

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(fileText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Received  file name and removes the extension
     *
     * @parm fileName a String containing a file name and single extension
     * @return the file name without the extension
     * */
    String getFileName(String fileName){

        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        return fileName;
    }

    /**
     * Retreives the last line of the input file
     *
     * @parm selectFile the file to be read
     * @return the last line of the input file
     * */
    String getLastFileLine(File selectFile){
        String line = null;
        String lineOut = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(selectFile));
            while ((line = br.readLine()) != null) {
                lineOut = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineOut;
    }

    /**
     * Creates a new text file in a specified accessible location
     *
     * @parm fileName the name of the text file to be created (_out.txt will be appended to name)
     * @parm    the location the file is to be created in
     * @return newFile is the file object
     *
     * */
    File createFile(String fileName, File fileDirectory) throws IOException {
        File newFile = new File(String.format("%s\\%s_out.txt",fileDirectory.getPath(),fileName));
        newFile.createNewFile();
        return newFile;
    }

    public static void main (String[] args) {

    launch(args);
    }
}
