package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;
import model.interpreter.server.DataReaderServer;

public class OpenServerCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "openDataServer";

    private static DataReaderServer server;

    @Override
    public double execute() {
        int port = (int) ShuntingYard.calc(strings.next());
        int hz = (int) ShuntingYard.calc(strings.next());
        server = new DataReaderServer(port, hz);
        server.start();
        return 1;
    }

    public static void stopServer() {
        server.stop();
    }
}
