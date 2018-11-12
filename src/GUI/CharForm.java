package GUI;

import backEnd.RPGCharacter;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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
    private JPanel backPanel;
    private JPanel healthPanel;
    private JTextField acField;
    private JLabel AC;
    private JTextField speedField;
    private JLabel speed;
    private JLabel HP;
    private JTextField maxHPField;
    private JTextField currentHPField;
    private JLabel maxHP;
    private JLabel currentHP;
    private JLabel hitDice;
    private JPanel HPPanel;
    private JLabel maxHitDice;
    private JLabel currentHitDice;
    private JTextField hitDiceSizeF;
    private JTextField currentHitDiceField;
    private JPanel hitDicePanel;
    private JCheckBox inspirationCheckBox;
    private JLabel maxHitDiceAmount;
    private JLabel proficiency;
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
                data.add(Boolean.toString(inspirationCheckBox.isSelected()));
                data.add(acField.getText());
                data.add(speedField.getText());
                data.add(maxHPField.getText());
                data.add(currentHPField.getText());
                data.add(hitDiceSizeF.getText());
                data.add(levelF.getText()); // Max Hit dice
                data.add(currentHitDiceField.getText());
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


        levelF.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                maxHitDiceAmount.setText(levelF.getText());
                try{
                    int lvl = Integer.parseInt(levelF.getText());
                    int bonus = RPGCharacter.calculateProficiencyBonus(lvl);
                    String append = "";
                    if(bonus >= 0)
                        append += "+";
                    append += String.valueOf(bonus);
                    proficiency.setText("Proficiency Bonus:  "+append);
                } catch (NumberFormatException ex){}

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
        inspirationCheckBox.setSelected(actor.isInspired());
        acField.setText(String.valueOf(actor.getAc()));
        speedField.setText(String.valueOf(actor.getSpeed()));
        maxHPField.setText(String.valueOf(actor.getMaxHP()));
        currentHPField.setText(String.valueOf(actor.getCurrentHP()));
        hitDiceSizeF.setText(String.valueOf(actor.getHitDiceSides()));
        currentHitDiceField.setText(String.valueOf(actor.getCurrentHitDiceAmount()));
        int profBonus = actor.getProficiencyBonus();
        String append = "";
        if(profBonus >= 0)
            append += "+";
        append += String.valueOf(profBonus);
        proficiency.setText("Proficiency Bonus:  "+append);
        maxHitDiceAmount.setText(String.valueOf(actor.getLevel()));

        int groupCounter = 0;
        for(int i = 0; i<actor.skills.length; i++){
            for(int j=0;j<actor.skills[i].length;j++){
                JCheckBox currentBox = checkGroup[groupCounter];
                String currentString = currentBox.getText();
                if(Character.isDigit(currentString.charAt(currentString.length()-1))&&Character.isDigit(currentString.charAt(currentString.length()-2)))  //Checks for double digit bonus(rare but could happen)
                    currentString = currentString.substring(0,currentString.length()-5);
                else
                    currentString = currentString.substring(0,currentString.length()-4);  // 4 so the "  +0" (or any integer) at the end of the string is replaced.
                currentBox.setSelected(actor.skills[i][j]);
                int bonus;
                String bString;
                switch(i) {
                    case 0:
                        bonus = actor.getStrength();
                        break;
                    case 1:
                        bonus = actor.getDexterity();
                        break;
                    case 2:
                        bonus = actor.getConstitution();
                        break;
                    case 3:
                        bonus = actor.getIntelligence();
                        break;
                    case 4:
                        bonus = actor.getWisdom();
                        break;
                    case 5:
                        bonus = actor.getCharisma();
                        break;
                    default:
                        bonus = 0;
                        break;
                }
                if(currentBox.isSelected())
                    bonus += actor.getProficiencyBonus();
                if(bonus<0)
                    bString = "  " + String.valueOf(bonus);
                else
                    bString = "  +" + String.valueOf(bonus);
                currentBox.setText(currentString+bString);

                groupCounter++;
            }
        }
    }
}
