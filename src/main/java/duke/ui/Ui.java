package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the class that deals with interactions with the user.
 */
public class Ui {

    /**
     * The greeting message to be shown when the programme starts.
     */
    private static final String GREETINGS = "Hello! I'm Duke.\n"
            + "What can I do for you?\n";

    /**
     * The exit message to be shown when the programme terminates.
     */
    private static final String GOODBYE = "Goodbye! Hope to see you again soon!";

    /**
     * A scanner object to take in user input.
     */
    private final Scanner sc;

    /**
     * Initializes a Ui object with the scanner ready to take in inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the next line of user input.
     *
     * @return The next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns string representation of the welcome message to the user.
     *
     * @return The welcome message to the user.
     */
    public String showWelcomeMessage() {
        return GREETINGS;
    }

    /**
     * Returns string representation of the exit message to the user.
     *
     * @return The exit message to the user.
     */
    public String showExitMessage() {
        return GOODBYE;
    }

    /**
     * Returns string representation of the message when a task is being added.
     *
     * @param task The task to be added.
     * @param numTasks The total number of tasks after adding.
     * @return The message indicating a task has been added.
     */
    public String showAddMessage(Task task, int numTasks) {
        String message = "Okay! Task added for you!\n";
        message = message.concat(task.toString() + "\n");
        message = message.concat("Now you have " + numTasks + " task(s) in the list." + "\n");
        return message;
    }

    /**
     * Returns string representation of the message when a task is being deleted.
     *
     * @param task The task to be deleted.
     * @param numTasks The total number of tasks after deleting.
     * @return The message indicating a task has been deleted.
     */
    public String showDeleteMessage(Task task, int numTasks) {
        String message = "Noted. The following task is removed:";
        message = message.concat(task.toString() + "\n");
        message = message.concat("Now you have " + numTasks + " task(s) in the list." + "\n");
        return message;
    }

    /**
     * Returns string representation of the message when a task is being marked as done.
     *
     * @param task The task to be marked as done.
     * @return The message indicating a task has been marked as done.
     */
    public String showDoneMessage(Task task) {
        String message = "Good job! I've marked this task as done:";
        message = message.concat(task + "\n");
        return message;
    }

    /**
     * Displays the error message when an error occurred.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Returns string representation of the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The list of tasks to the user.
     */
    public String displayTasks(List<Task> tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "No tasks added to your list yet!\n";
        } else {
            message = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                message = message.concat(i + 1 + ". " + tasks.get(i) + "\n");
            }
        }
        return message;
    }

    /**
     * Returns string representation of the list of matching tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String displayMatchingTasks(List<Task> tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "No matching tasks found in your list!\n";
        } else {
            message = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                message = message.concat(i + 1 + ". " + tasks.get(i) + "\n");
            }
        }
        return message;
    }
}