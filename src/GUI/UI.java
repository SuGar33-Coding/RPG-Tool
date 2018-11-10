package GUI;

import backEnd.Dicey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    private static JFrame frame = new JFrame("RPG Tool");
    private JPanel mainMenu;
    private JButton newCharacterButton;
    private JButton loadCharacterButton;
    private JButton diceyButton;
    private JPanel hello;
    private JLabel hiThere;
    private JPanel buttons;

    public UI() {
        loadCharacterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                System.out.println(fc.getSelectedFile());
                System.out.println("AHH");
            }
        });
        diceyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new DI().DicePanel);
                frame.validate();
                frame.repaint();

            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new UI().mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void init(){
        frame.setContentPane(new UI().mainMenu);
        frame.validate();
        frame.repaint();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
