package com.ilyasov.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Tetris extends JFrame {
    volatile boolean isWinner = false;
    int informCode = 0;
    String title;
    private static final long serialVersionUID = 1L;
    JLabel statusbar;
    JLabel score;
    Board board;
    BufferedReader bufferedReader;
    PrintWriter printWriter;
    Socket socket;
    Thread thread;
    boolean isClosed = false;

    void startWork(){
        thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (board.isGameOver) {
                        printWriter.println("1");
                    }
                    if (isClosed) {
                        printWriter.println("2");
                    }
                    if (isWinner){
                        dispose();
                        JFrame frame = new JFrame("Tetris");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JLabel label = new JLabel("You WIN");
                        frame.getContentPane().add(label);
                        frame.setPreferredSize(new Dimension(200,100));
                        frame.pack();
                        frame.setVisible(true);
                        try {
                            thread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
        Thread helperThread = new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        informCode = Integer.parseInt(bufferedReader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (informCode != 0){
                        break;
                    }
                }
                isWinner = true;
            }
        });
        helperThread.start();
    }
    public void beforeInitialisation() {
        score = new JLabel("Score:");
        statusbar = new JLabel(" 0");
        add(score, BorderLayout.SOUTH);
        add(statusbar, BorderLayout.SOUTH);
        board = new Board(this);
        JOptionPane.showMessageDialog(board,
                "Welcome to TetrisServer \n "
                        + "Instructions for the game\n"
                        + " You have to eliminate rows of blocks by fitting them in the empty spaces\n"
                        + " Keys : \n"
                        + " Navigation:\n"
                        + " 1. Left : LEFT\n"
                        + " 2. Right: RIGHT\n"
                        + " Rotation\n"
                        + " 1. Left : UP\n"
                        + " 2. Right:DOWN\n"
                        + " Drop down :SPACE\n"
                        + " Pause     :Press <P>\n"
                        + " Close this window \n"
                        + " When your opponent will be connected game begins");
    }

    void afterInitialisation() {
        add(board);
        board.start();

        setSize(200, 400);
        setTitle(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                printWriter.println("3");
                isClosed = true;
                dispose();
                try {
                    thread.wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public JLabel getStatusBar() {
        return statusbar;
    }
}
