package GUI;

import backEnd.*;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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

    /* Active inventory */
    static Inventory inventory;

    static boolean debug = false;

    public MainFrame() {
        loadCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("Characters");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                if (fc.getName(fc.getSelectedFile()) != null) {
                    CharForm charFrame = new CharForm();
                    JPanel pan = charFrame.charPan;
                    frame.setContentPane(pan);
                    frame.validate();
                    frame.repaint();
                    JSONObject charJSON = RPGCharacter.loadCharJSON(fc.getName(fc.getSelectedFile()));
                    inventory = new Inventory(charJSON);
                    charFrame.updateFormData(charJSON);

                    if (debug){
                        System.out.println(inventory);
                    }
                }
            }
        });
        diceyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame diceFrame = new JFrame("Dicey");
                diceFrame.setContentPane(new DI().DicePanel);
                diceFrame.setPreferredSize(new Dimension(500, 300));
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

                inventory = new Inventory();

                if (debug) {
                    System.out.println(inventory);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("Characters");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                String fldrName = fc.getName(fc.getSelectedFile());
                File dir = new File("Characters" + System.getProperty("file.separator") + fldrName);

                if (dir.isDirectory() != false) {
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
        MainFrame mainFrame = new MainFrame();
        JPanel mainMenu = mainFrame.getMainMenu();
        frame.setContentPane(mainMenu);
        frame.setPreferredSize(new Dimension(700, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /* add debugging statements to here */
        if (debug) {
        }

        /* Uncomment if you think you're cool enough */
        //mainFrame.letsRide();
    }

    public static void init() {
        MainFrame mainFrame = new MainFrame();
        JPanel mainMenu = mainFrame.getMainMenu();
        frame.setContentPane(mainMenu);
        frame.validate();
        frame.repaint();
    }

    public String str2path(String path) {
        String rhet = "";
        String sep = System.getProperty("file.separator");
        for (int i = 0; i < path.length(); i++)
            if (path.charAt(i) == '/')
                rhet = rhet + sep;
            else
                rhet = rhet + path.charAt(i);

        return rhet;
    }

    public void letsRide() {
        /* tfw you use an infinitely incrementing for loop as opposed to a while(true) */
        for (int i = 1; i > 0; i++) {
            welcomePanel.setBackground(Color.BLACK);
            welcomePanel.setBackground(Color.BLUE);
            welcomePanel.setBackground(Color.red);
            welcomePanel.setBackground(Color.green);
            welcomePanel.setBackground(Color.gray);
            welcomePanel.setBackground(Color.yellow);
            welcomePanel.setBackground(Color.cyan);
            welcomePanel.setBackground(Color.orange);
            welcomePanel.setBackground(Color.pink);
            buttons.setBackground(Color.BLACK);
            buttons.setBackground(Color.BLUE);
            buttons.setBackground(Color.red);
            buttons.setBackground(Color.green);
            buttons.setBackground(Color.gray);
            buttons.setBackground(Color.yellow);
            buttons.setBackground(Color.cyan);
            buttons.setBackground(Color.orange);
            buttons.setBackground(Color.pink);
        }
    }

    public JPanel getMainMenu() {
        return mainMenu;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
