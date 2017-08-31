import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.lang.reflect.Array;

public class Main extends Application{

    public void start(Stage stage){
        File userDirectory = new File(System.getProperty("user.dir"));
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(userDirectory);
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setTitle("Select File");
        File selectedFile =  fileChooser.showOpenDialog(stage);
        String printLine = getLastFileLine(selectedFile);
        System.out.println(userDirectory.getPath());
        try {
            File file = createFile(getFileName(selectedFile.getName()),userDirectory);
            FileWriter writer = new FileWriter(file);
            writer.write(printLine);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printFile(File selectFile){

        try {
            BufferedReader br = new BufferedReader(new FileReader(selectFile));
            String line = null;
            while ((line = br.readLine()) != null) {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String getFileName(String fileName){

        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        return fileName;
    }

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

    File createFile(String fileName, File fileDirectory) throws IOException {
        File newFile = new File(String.format("%s\\%s_out.txt",fileDirectory.getPath(),fileName));
        newFile.createNewFile();
        return newFile;
    }

    public static void main (String[] args) {

    launch(args);
    }
}
