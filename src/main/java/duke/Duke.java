package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private final Storage storage;

    private TaskManager manager;

    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            manager = new TaskManager(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager();
        }
    }

    public void run() {
        ui.showWelcomeMessage();   
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(manager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
