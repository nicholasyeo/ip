package duke;

import java.util.List;
import java.util.ArrayList;

import duke.task.Task;

public class TaskManager {

    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public void markTaskAsDone(Task task) {
        task.markAsDone();
    }
}