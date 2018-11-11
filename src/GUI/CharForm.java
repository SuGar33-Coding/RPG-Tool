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
    private JTextField strengthF;
    private JTextField dexterityF;
    private JTextField constitutionF;
    private JTextField intelligenceF;
    private JTextField wisdomF;
    private JTextField charismaF;
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
        updateSaveButton.addActionListener(new ActionListener() {  // TODO: Handle parsing errors
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
                data.add("true");
                data.add("10");
                data.add("20");
                data.add("50");
                data.add("45");
                data.add("8");
                data.add(levelF.getText());
                data.add("3");
                data.add(strengthF.getText());
                data.add(dexterityF.getText());
                data.add(constitutionF.getText());
                data.add(intelligenceF.getText());
                data.add(wisdomF.getText());
                data.add(charismaF.getText());
                data.add(Boolean.toString(strSave.isSelected()));
                data.add(Boolean.toString(athleticsCheckBox.isSelected()));
                data.add(Boolean.toString(dexSave.isSelected()));
                data.add(Boolean.toString(acrobaticsCheckBox.isSelected()));
                data.add(Boolean.toString(sleightOfHandCheckBox.isSelected()));
                data.add(Boolean.toString(stealthCheckBox.isSelected()));
                data.add(Boolean.toString(conSave.isSelected()));
                data.add(Boolean.toString(intSave.isSelected()));
                data.add(Boolean.toString(arcanaCheckBox.isSelected()));
                data.add(Boolean.toString(historyCheckBox.isSelected()));
                data.add(Boolean.toString(investigationCheckBox.isSelected()));
                data.add(Boolean.toString(natureCheckBox.isSelected()));
                data.add(Boolean.toString(religionCheckBox.isSelected()));
                data.add(Boolean.toString(wisSave.isSelected()));
                data.add(Boolean.toString(animalhCheckBox.isSelected()));
                data.add(Boolean.toString(insightCheckBox.isSelected()));
                data.add(Boolean.toString(medicineCheckBox.isSelected()));
                data.add(Boolean.toString(perceptionCheckBox.isSelected()));
                data.add(Boolean.toString(survivalCheckBox.isSelected()));
                data.add(Boolean.toString(chSave.isSelected()));
                data.add(Boolean.toString(deceptionCheckBox.isSelected())); // TODO: See if you can do this better with button groups?
            }
        });
    }
}
