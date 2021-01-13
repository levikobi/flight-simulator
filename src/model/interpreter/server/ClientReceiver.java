package model.interpreter.server;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver {
    private BufferedReader inFromClient;
    private String prevLine;

    public ClientReceiver(BufferedReader inFromClient) {
        this.inFromClient = inFromClient;
        this.prevLine = "";
    }

    public void run() {
        String currLine;
        try {
            currLine = inFromClient.readLine();
            VariablesManager.updateVariables(currLine);
        } catch (IOException e) { e.printStackTrace(); }

    }

    public void end() {
        try {
            inFromClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
