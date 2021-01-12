package model.interpreter.commands;

public class DisconnectCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "disconnect";

    @Override
    public double execute() {
        ConnectCommand.closeConnection();
        OpenServerCommand.stopServer();
        return 1;
    }
}