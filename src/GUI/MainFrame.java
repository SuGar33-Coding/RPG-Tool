package GUI;
import backEnd.*;

import javax.swing.*;
import java.awt.*;
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
    private JPanel welcomePanel;
    private JLabel welcomeLabel;
    private JPanel buttons;
    private JButton deleteButton;

    public MainFrame() {
        loadCharacterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("Characters");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                RPGCharacter actor = new RPGCharacter(fc.getName(fc.getSelectedFile()));
                CharForm charFrame = new CharForm();
                JPanel pan = charFrame.charPan;
                frame.setContentPane(pan);
                frame.validate();
                frame.repaint();
                charFrame.updateData(actor);
            }
        });
        diceyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame diceFrame = new JFrame("Dicey");
                diceFrame.setContentPane(new DI().DicePanel);
                diceFrame.setPreferredSize(new Dimension(500,300));
                diceFrame.pack();
                diceFrame.setVisible(true);
                /*frame.setContentPane(new DI().DicePanel);
                frame.validate();
                frame.repaint();*/

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("Characters");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                String fldrName = fc.getName(fc.getSelectedFile());
                File dir = new File("Characters"+System.getProperty("file.separator")+fldrName);

                if(dir.isDirectory() != false) {
                    File[] listFiles = dir.listFiles();
                    for (File file : listFiles) {
                        file.delete();
                    }
                    dir.delete();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new MainFrame().mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /* add debugging statements to here */
        boolean debug = false;
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
