package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String path_to_file){
        file = new File(path_to_file);
        try {
            checkExist(file.getParentFile());
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("unable to create file");
            e.printStackTrace();
        }
    }

    private void checkExist(File file){
        if (file.exists()) {
            return;
        } else {
            checkExist(file.getParentFile());
            file.mkdir();
        }
    }

    public void retrieveTasks(TaskList taskList) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data);
            }
            myReader.close();
            lines.forEach(s -> taskList.add(s));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveToFile(TaskList taskList){
        try {
            String txt = taskList.saveTasklist();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(txt);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

}
