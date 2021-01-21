package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public final class Pathfinder {

    private Socket socket;
    private PrintWriter outToSocket;
    private BufferedReader inFromSocket;

    private String path;

    public void connect(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        outToSocket = new PrintWriter(socket.getOutputStream());
        inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void calculateShortestPath(List<String> grid, String start, String target) throws Exception {
        if (grid == null || start == null || target == null) throw new NullPointerException();
        for (String line : grid) {
            outToSocket.println(line);
        }
        outToSocket.println("end");
        outToSocket.println(start);
        System.out.println("Pathfinder " + start);
        outToSocket.println(target);
        outToSocket.println(450);
        outToSocket.flush();
        path = inFromSocket.readLine();
    }

    public String getShortestPath() {
        return path;
    }
}
