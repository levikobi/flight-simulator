package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

public class Pathfinder extends Observable {

    protected Socket socket;
    protected PrintWriter outToSocket;
    protected BufferedReader inFromSocket;

    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            outToSocket = new PrintWriter(socket.getOutputStream());
            inFromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to pathfinding server successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateShortestPath(List<String> grid, String start, String target) {
        for (String line : grid) {
            outToSocket.println(line);
        }
        outToSocket.println("end");
        outToSocket.println(start);
        outToSocket.println(target);
        outToSocket.println(450);
        outToSocket.flush();
    }

    public String getShortestPath() {
        try {
            return inFromSocket.readLine();
        } catch (IOException e) { e.printStackTrace(); }
        return "";
    }
}
