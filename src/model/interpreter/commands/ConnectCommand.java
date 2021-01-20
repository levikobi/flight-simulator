package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "connect";
    public static PrintWriter out;
    private static Socket socket;
    private static boolean connected = false;

    @Override
    public double execute() {
        String ip = strings.next();
        int port = (int) ShuntingYard.calc(strings.next());

        do {
            try {
                socket = new Socket(ip, port);
                socket.setSoTimeout(1000);
                connected = true;
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ignored) { }
            try {
                wait(10);
            } catch (InterruptedException ignored) { }
        } while (!connected);

        return 1;
    }

    public static void closeConnection() {
        out.println("bye");
        out.close();
        connected = false;
        try {
            socket.close();
        } catch (IOException ignored) { }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5402);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("set /controls/flight/rudder 1");
        out.println("set /controls/flight/rudder -1");
        out.println("set /controls/flight/rudder 1");
    }
}