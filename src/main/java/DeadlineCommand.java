import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {

    private final LocalDate date;

    public DeadlineCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Deadline(description, date);
        manager.addTask(task);
        ui.showAddMessage(task, manager.getTasks().size());
        storage.saveTasks(manager.getTasks());
    }
    
}