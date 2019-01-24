package GUI;

import backEnd.*;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// TODO: Make it so that if someone does an incorrect input in any text field, it shows error on JOptionPane.  Or put any error in general on JOptionPane.

/* TODO:    BUGS!!!!!!!!
    No description for currency and equipment;
    didn't do a detail page for spells;

    set min size for text fields.

    double digit bonuses for skills break everything. (CharForm line 312);

    make sure that windows made from charForm do not go off screen;

    make damage field in inventory for weapons bigger;

    check if you can leave attk bonus blank
 */

public class MainFrame {
    private static boolean maintenance = true;


    private static JFrame frame = new JFrame("RPG Tool");
    private JPanel mainMenu;
    private JButton newCharacterButton;
    private JButton loadCharacterButton;
    private JButton diceyButton;
    private JPanel welcomePanel;
    private JLabel welcomeLabel;
    private JPanel buttons;
    private JButton deleteButton;
    private int tester = 0;

    static String filepath;

    /* Active character */
    static RPGCharacter character;

    static String sep = "@";  // Separator used in JSON character files to separate different pieces of data.
    static Dimension mainDim = new Dimension(725, 800);

    /* List of the component types so that fonts and backgrounds can be easily changed */
    static String[] componentFonts = {"Button.font","Label.font","Panel.font","CheckBox.font","CheckBoxMenuItem.font","ScrollPane.font",
            "TabbedPane.font","TextField.font","TextArea.font","RadioButton.font"};
    static String[] componentBackgrounds = {"Panel.background","Frame.background","ScrollPane.background","TabbedPane.background",
            "TextArea.background","Label.background","CheckBox.background","CheckBoxMenuItem.background","RadioButton.background"};
    static Color defaultColor = new Color(174,162,135);

    static boolean debug = false;

    static CharForm charFrame;



    public MainFrame() {
        /* When load character is pressed, creates an object to interact with computers file system, and user
           Will select character folder (characters are currently stored in folders, not just one file)
         */

        loadCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc;
                fc = new JFileChooser(new File(filepath));
                //JOptionPane.showMessageDialog(null,filepath,"alert",JOptionPane.ERROR_MESSAGE);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                if (fc.getName(fc.getSelectedFile()) != null) {
                    JSONObject charJSON = IO.loadCharJSON(fc.getName(fc.getSelectedFile()));

                    character = new RPGCharacter(charJSON);

                    charFrame = new CharForm(character,frame);
                    charFrame.updateFormData(character);
                    JPanel pan = charFrame.charPan;
                    frame.setContentPane(pan);
                    frame.validate();
                    frame.repaint();
                }
            }
        });

        // Creates new frame for dice rolling
        diceyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceyWidth = 500;
                JFrame diceFrame = new JFrame("Dicey");
                diceFrame.setContentPane(new DI().DicePanel);
                diceFrame.setPreferredSize(new Dimension(diceyWidth, 300));
                diceFrame.pack();
                diceFrame.setLocation(frame.getLocation().x-diceyWidth,frame.getLocation().y);
                diceFrame.setVisible(true);

            }
        });
        newCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character = new RPGCharacter();

                charFrame = new CharForm(character, frame);
                frame.setContentPane(charFrame.charPan);
                frame.validate();
                frame.repaint();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(filepath);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(mainMenu);
                String fldrName = fc.getName(fc.getSelectedFile());
                File dir = new File(filepath + System.getProperty("file.separator") + fldrName);

                if (dir.isDirectory() != false) {
                    File[] listFiles = dir.listFiles();
                    for (File file : listFiles) {
                        file.delete();
                    }
                    dir.delete();
                }
            }
        });

        /*settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tester == 0){
                    for(String s : componentBackgrounds)
                        UIManager.put(s, Color.red);
                    tester = 1;
                    mainMenu.setBackground(Color.darkGray);
                }
                else{
                    for(String s : componentBackgrounds)
                        UIManager.put(s, Color.lightGray);
                    tester = 0;
                    mainMenu.setBackground(defaultColor);
                }

            }
        });*/

    }

    public static void main(String[] args) {
        try{
            if(maintenance)
                filepath = "Characters";
            else
                filepath = MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()+
                        System.getProperty("file.separator")+".." + System.getProperty("file.separator") + "Characters";
        } catch (Exception ex){System.out.println("Something went wrong");}

        for(String s : componentFonts)
            UIManager.put(s, new Font("STLiti", Font.BOLD, 16));
        for(String s : componentBackgrounds)
            UIManager.put(s, defaultColor);
        UIManager.put("Button.background",Color.lightGray);

        MainFrame mainFrame = new MainFrame();
        JPanel mainMenu = mainFrame.getMainMenu();
        frame.setContentPane(mainMenu);
        frame.setPreferredSize(mainDim);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)((screenSize.getWidth()-frame.getWidth())/2);
        int centerXadjust = 100; // Inv and Spell frames are slightly larger, so we should shift the main screen left a bit
        int centerY = 25;
        frame.setLocation(centerX-centerXadjust,centerY);
        frame.setVisible(true);

        mainFrame.setNotOpaque();
        // So if we set all upper panels to opaque(false), background color on main panel will come through

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

    private void setNotOpaque(){
        welcomePanel.setOpaque(false);
        buttons.setOpaque(false);
        //buttons.remove(settingsButton);
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

    public static String getFilepath(){return filepath;}

    public static CharForm getCharFrame(){return charFrame;}
}
