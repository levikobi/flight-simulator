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

    public void calculateShortestPath(List<String> grid, String start, String target) throws IOException, NullPointerException {
        if (grid == null || start == null || target == null) throw new NullPointerException();
        for (String line : grid) {
            outToSocket.println(line);
        }
        outToSocket.println("end");
        outToSocket.println(start);
        outToSocket.println(target);
        outToSocket.println(450);
        outToSocket.flush();
        path = inFromSocket.readLine();
    }

    public String getShortestPath() {
        return path;
    }

//    public static void main(String[] args) throws Exception {
//        Socket temp = new Socket("127.0.0.1", 5000);
//        PrintWriter out  = new PrintWriter(temp.getOutputStream());
//        BufferedReader in = new BufferedReader(new InputStreamReader(temp.getInputStream()));
//
//        out.println("1,1,1");
//        out.println("1,1,1");
//        out.println("1,1,1");
//        out.println("end");
//        out.println("0,0");
//        out.println("2,2");
//        out.println("500");
//        out.flush();
//        System.out.println(in.readLine());
//        System.out.println("end");
//    }
}
