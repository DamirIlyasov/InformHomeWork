package com.ilyasov.tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;


public class TetrisServer extends Tetris {
    public TetrisServer() {
        title = "TetrisServer";
        beforeInitialisation();
        initializeUser();
        afterInitialisation();
       this.setLocationRelativeTo(null);
        this.setVisible(true);
   }


    public static void main(String[] args) {
        TetrisServer tetrisServer = new TetrisServer();
        tetrisServer.startWork();
    }
    void initializeUser(){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}