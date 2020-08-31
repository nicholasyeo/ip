package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command to add event tasks.
 */
public class EventCommand extends AddCommand {

    /**
     * A local date instance to store the date of the event.
     */
    private final LocalDate date;

    /**
     * Constructs a command that adds an event task.
     *
     * @param description The description of the event.
     * @param date The date of the event.
     */
    public EventCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, date);
        manager.addTask(task);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(task, manager.getTasks().size());
    }
}
