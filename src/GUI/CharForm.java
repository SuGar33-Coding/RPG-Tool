package GUI;

import backEnd.RPGCharacter;

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
    private JCheckBox intimidationCheckBox;
    private JCheckBox medicineCheckBox;
    private JCheckBox perceptionCheckBox;
    private JCheckBox persuasionCheckBox;
    private JCheckBox sleightOfHandCheckBox;
    private JCheckBox survivalCheckBox;
    private JCheckBox strSave;
    private JCheckBox historyCheckBox;
    private JButton updateSaveButton;
    private JButton backButton;
    private JCheckBox[] checkGroup =
            {strSave,athleticsCheckBox,dexSave,acrobaticsCheckBox,sleightOfHandCheckBox,stealthCheckBox,conSave,intSave,arcanaCheckBox,historyCheckBox,investigationCheckBox,
                    natureCheckBox,religionCheckBox,wisSave,animalhCheckBox,insightCheckBox,medicineCheckBox,perceptionCheckBox,survivalCheckBox,chSave,deceptionCheckBox,
                    intimidationCheckBox,performanceCheckBox,persuasionCheckBox}; // Just a list of all checkboxes/skills

    public CharForm() {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.init();  // Return to main menu
            }
        });
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
                for(JCheckBox checkBox : checkGroup){
                    data.add(Boolean.toString(checkBox.isSelected()));
                }
                RPGCharacter.updateCharFiles(data);
                RPGCharacter actor = new RPGCharacter(charF.getText());
                updateData(actor);
            }
        });

    }

    public void updateData(RPGCharacter actor){
        playerF.setText(actor.getPlayerName());
        charF.setText(actor.getName());
        classF.setText(actor.getCharacterClass());
        raceF.setText(actor.getRace());
        levelF.setText(String.valueOf(actor.getLevel()));
        alignmentF.setText(actor.getAlignment());
        xpF.setText(String.valueOf(actor.getXp()));
        strengthF.setText(String.valueOf(actor.getRawStrength()));
        dexterityF.setText(String.valueOf(actor.getRawDexterity()));
        constitutionF.setText(String.valueOf(actor.getRawConstitution()));
        intelligenceF.setText(String.valueOf(actor.getRawIntelligence()));
        wisdomF.setText(String.valueOf(actor.getRawWisdom()));
        charismaF.setText(String.valueOf(actor.getRawCharisma()));
        SB.setText("SB: "+actor.getStrength());
        DB.setText("DB: "+actor.getDexterity());
        CB.setText("CB: "+actor.getConstitution());
        IB.setText("IB: "+actor.getIntelligence());
        WB.setText("WB: "+actor.getWisdom());
        ChB.setText("ChB: "+actor.getCharisma());
        int groupCounter = 0;
        for(int i = 0; i<actor.skills.length; i++){
            for(int j=0;j<actor.skills[i].length;j++){
                checkGroup[groupCounter].setSelected(actor.skills[i][j]);
                groupCounter++;
            }
        }
        /*strSave.setSelected(actor.skills[0][0]);
        athleticsCheckBox.setSelected(actor.skills[0][1]);
        dexSave.setSelected(actor.skills[1][0]);
        acrobaticsCheckBox.setSelected(actor.skills[1][2]);
        sleightOfHandCheckBox.setSelected(actor.skills[1][3]);
        stealthCheckBox.setSelected(actor.skills[2][0]);
        conSave.setSelected(actor.skills[3][0]);
        intSave.setSelected(actor.skills[3][1]);
        arcanaCheckBox.setSelected(actor.skills[3][2]);
        historyCheckBox.setSelected(actor.skills[3][3]);
        investigationCheckBox.setSelected(actor.skills[3][4]);
        natureCheckBox.setSelected(actor.skills[3][5]);
        religionCheckBox.setSelected(actor.skills[4][0]);
        wisSave.setSelected(actor.skills[4][1]);
        animalhCheckBox.setSelected(actor.skills[4][2]);
        insightCheckBox.setSelected(actor.skills[4][3]);
        medicineCheckBox.setSelected(actor.skills[4][4]);
        perceptionCheckBox.setSelected(actor.skills[4][5]);
        survivalCheckBox.setSelected(actor.skills[5][0]);
        chSave.setSelected(actor.skills[5][1]);
        deceptionCheckBox.setSelected(actor.skills[5][2]);
        performanceCheckBox.setSelected(actor.skills[5][3]);
        persuasionCheckBox.setSelected(actor.skills[5][4])*/;
    }
}
