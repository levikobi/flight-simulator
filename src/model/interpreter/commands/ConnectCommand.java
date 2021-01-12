package model.interpreter.commands;

import model.interpreter.expressions.ShuntingYard;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ConnectCommand extends AbstractCommand {
    public static final String DISPLAY_NAME = "connect";
    public static PrintWriter out;
    private static Socket socket;
    private static boolean connected = false;

    @Override
    public double execute() {
        String ip = strings.next();
        int port = (int) ShuntingYard.calc(strings.next());

        // really bad code..
        do {
            try {
                socket = new Socket(ip, port);
                socket.setSoTimeout(1000);
                connected = true;
                out = new PrintWriter(socket.getOutputStream());
//                System.out.print("Connected to " + ip + " on port " + port + ". ");
//                System.out.println("Running on " + Thread.currentThread().getName() + " thread.");
            } catch (IOException ignored) { }
            try {
                sleep(10);
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
}