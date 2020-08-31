package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to mark tasks as done.
 */
public class DoneCommand extends Command {

    /**
     * The task number on the list to be marked as done.
     */
    private final String taskNumber;

    /**
     * Constructs a command that marks a task as done.
     *
     * @param taskNumber The task number to be marked as done.
     */
    public DoneCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = manager.getTasks().get(index);
            manager.markTaskAsDone(task);
            storage.saveTasks(manager.getTasks());
            return ui.showDoneMessage(task);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! "
                    + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
