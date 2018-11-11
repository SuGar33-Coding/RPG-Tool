package GUI;
import backEnd.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.File;

public class MainFrame {
    private static JFrame frame = new JFrame("RPG Tool");
    private JPanel mainMenu;
    private JButton newCharacterButton;
    private JButton loadCharacterButton;
    private JButton diceyButton;
    private JPanel hello;
    private JLabel hiThere;
    private JPanel buttons;

    public MainFrame() {
        loadCharacterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("Characters");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);

            }
        });
        diceyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new DI().DicePanel);
                frame.validate();
                frame.repaint();

            }
        });
        newCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new CharForm().charPan);
                frame.validate();
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new MainFrame().mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /* add debugging statements to here */
        boolean debug = true;
        if (debug) {
            RPGCharacter c = new RPGCharacter("/Users/cassandra/IdeaProjects/GM-Tool/Characters");
            System.out.println(c.getStrength());
            System.out.println(c.getCharisma());
            for (Item i : c.items)
                System.out.println(i.getName());
        }
    }

    public static void init(){
        frame.setContentPane(new MainFrame().mainMenu);
        frame.validate();
        frame.repaint();
    }

    public String str2path(String path){
        String rhet = "";
        String sep = System.getProperty("file.separator");
        for(int i = 0; i < path.length(); i++)
            if(path.charAt(i)=='/')
                rhet = rhet + sep;
            else
                rhet = rhet + path.charAt(i);

        return rhet;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
