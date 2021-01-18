package model.interpreter.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class DataReaderServer {
    private volatile boolean stop = false;
    private final int port;
    private final int freq;

    public DataReaderServer(int port, int freq) {
        this.port = port;
        this.freq = freq;
    }

    public void start() {
//        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) { e.printStackTrace(); }
//        }).start();
    }

    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(port);
//        while (!stop) {
            server.setSoTimeout(30000);
            try {
                acceptNewConnection(server);
            } catch (SocketTimeoutException ignored) { }
//        }
        server.close();
    }

    private void acceptNewConnection(ServerSocket server) throws Exception {
        Socket theSimulator = server.accept(); //Blocking call
        BufferedReader simulatorInput = new BufferedReader(new InputStreamReader(theSimulator.getInputStream()));
        ClientReceiver clientReceiver = new ClientReceiver(simulatorInput);
        System.out.println("Connected to FlightGear!");

        int delay = 1000 / freq;
        new Thread(() -> {
            while (!stop) {
                clientReceiver.run();
//                try {
//                    Thread.sleep(delay);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            clientReceiver.end();
            try {
                theSimulator.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void stop() {
        this.stop = true;
    }
}
