package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CharForm {
    public JPanel charPan;
    private JPanel namePan;
    private JPanel infoPan;
    private JPanel statsPanel;
    private JLabel player;
    private JTextField playerF;
    private JTextField charF;
    private JLabel race;
    private JLabel character;
    private JTextField raceF;
    private JTextField classF;
    private JTextField levelF;
    private JTextField alignmentF;
    private JTextField xpF;
    private JLabel classlab;
    private JLabel level;
    private JLabel alignment;
    private JLabel xp;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel strength;
    private JLabel dexterity;
    private JLabel constitution;
    private JLabel intelligence;
    private JLabel wisdom;
    private JLabel charisma;
    private JLabel SB;
    private JLabel DB;
    private JLabel CB;
    private JLabel IB;
    private JLabel WB;
    private JLabel ChB;
    private JPanel majStats;
    private JPanel skillsPan;
    private JCheckBox conSave;
    private JCheckBox wisSave;
    private JCheckBox acrobaticsCheckBox;
    private JCheckBox arcanaCheckBox;
    private JCheckBox deceptionCheckBox;
    private JCheckBox insightCheckBox;
    private JCheckBox investigationCheckBox;
    private JCheckBox natureCheckBox;
    private JCheckBox performanceCheckBox;
    private JCheckBox religionCheckBox;
    private JCheckBox stealthCheckBox;
    private JCheckBox dexSave;
    private JCheckBox intSave;
    private JCheckBox chSave;
    private JCheckBox animalhCheckBox;
    private JCheckBox athleticsCheckBox;
    private JCheckBox checkBox17;
    private JCheckBox intimidationCheckBox;
    private JCheckBox medicineCheckBox;
    private JCheckBox perceptionCheckBox;
    private JCheckBox persuasionCheckBox;
    private JCheckBox sleightOfHandCheckBox;
    private JCheckBox survivalCheckBox;
    private JCheckBox strSave;
    private JCheckBox historyCheckBox;
    private JButton updateSaveButton;

    public CharForm() {
        updateSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> data = new ArrayList<>();
                data.add(playerF.getText());
                data.add(charF.getText());
                data.add(classF.getText());
                data.add(raceF.getText());
                data.add(levelF.getText());
                data.add(alignmentF.getText());
                data.add(xpF.getText());

            }
        });
    }
}