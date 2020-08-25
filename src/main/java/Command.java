public abstract class Command {

    public abstract void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}