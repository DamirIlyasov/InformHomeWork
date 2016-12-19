package com.ilyasov.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TetrisClient extends Tetris{
    public static void main(String[] args) {
        TetrisClient tetrisClient = new TetrisClient();
        tetrisClient.startWork();
    }
    public TetrisClient(){
        title = "TetrisClient";
        beforeInitialisation();
        initialiseServer();
        afterInitialisation();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    void initialiseServer(){
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),8080);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
