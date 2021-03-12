package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ShogiDragon extends JFrame{
    private JPanel ShogiDragon;
    private JButton startbot;
    public static Integer cc = 0;

    public ShogiDragon() {
        setSize(400,500);
        startbot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cc++;
                    ShogiMachine.player(new String[0]);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shogi Dragon");
        frame.setContentPane(new ShogiDragon().ShogiDragon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
