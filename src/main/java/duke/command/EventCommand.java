package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

public class EventCommand extends AddCommand {

    private final LocalDate date;
    
    public EventCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, date);
        manager.addTask(task);
        ui.showAddMessage(task, manager.getTasks().size());
        storage.saveTasks(manager.getTasks());
    }
}