package GUI;

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
                ClassLoader loader = MainFrame.class.getClassLoader();
                String filePath = loader.getResource("GUI").getPath();
                filePath = str2path(filePath);
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
                File dir = new File("newChar");
                dir.mkdir();
                Writer writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("newChar/newChar.txt"), "utf-8"));
                    writer.write("Something");
                } catch (IOException ex) {
                    // Report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
                }
            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new MainFrame().mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
