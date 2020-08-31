package duke.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents a Duke storage that deals with
 * loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * The file instance to load tasks from and save tasks to.
     */
    private final File file;

    /**
     * Initializes a storage instance for a particular file path.
     *
     * @param filePath The path of the file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a list of tasks after loading them from the specified file.
     *
     * @return A list of tasks.
     * @throws DukeException If the file is not found.
     */
    public List<Task> loadTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArray = data.split(" \\| ");
                String letter = dataArray[0];
                int bit = Integer.parseInt(dataArray[1]);
                String description = dataArray[2];
                Task task;
                if (letter.equals("T")) {
                    task = new Todo(description);
                } else if (letter.equals("D")) {
                    LocalDate time = LocalDate.parse(dataArray[3]);
                    task = new Deadline(description, time);
                } else {
                    LocalDate time = LocalDate.parse(dataArray[3]);
                    task = new Event(description, time);
                }
                if (bit == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            String errorMessage = "No previous save file found, "
                    + "starting up with no saved records...\n";
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Saves tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DukeException If the path specified is a directory,
     * or if the file cannot be created or opened.
     */
    public void saveTasks(List<Task> tasks) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                char letter = task.toString().charAt(1);
                int bit = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String data;
                if (letter == 'T') {
                    data = letter + " | " + bit + " | " + description;
                } else if (letter == 'D') {
                    String time = task.getDate().toString();
                    data = letter + " | " + bit + " | " + description + " | " + time;
                } else { // letter == 'E'
                    String time = task.getDate().toString();
                    data = letter + " | " + bit + " | " + description + " | " + time;
                }
                myWriter.write(data + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            String errorMessage = "An error occurred, "
                    + "unable to save tasks to file :(";
            throw new DukeException(errorMessage);
        }
    }
}
