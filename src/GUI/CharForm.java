package GUI;

import backEnd.RPGCharacter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static GUI.MainFrame.actor;
import java.util.ArrayList;

//import static GUI.MainFrame.actor;

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
                JSONObject data = new JSONObject();
                data.put("player name",playerF.getText());
                data.put("char name", charF.getText());
                data.put("class", classF.getText());
                data.put("race", raceF.getText());
                data.put("level", levelF.getText());
                data.put("alignment", alignmentF.getText());
                data.put("xp", xpF.getText());
                data.put("inspiration", Boolean.toString(inspirationCheckBox.isSelected()));
                data.put("ac", acField.getText());
                data.put("speed", speedField.getText());
                data.put("max HP", maxHPField.getText());
                data.put("current HP", currentHPField.getText());
                data.put("hit dice size", hitDiceSizeF.getText());
                data.put("current hit dice", currentHitDiceField.getText());
                data.put("strength", strengthF.getText());
                data.put("dexterity", dexterityF.getText());
                data.put("constitution", constitutionF.getText());
                data.put("intelligence", intelligenceF.getText());
                data.put("wisdom", wisdomF.getText());
                data.put("charisma", charismaF.getText());
                JSONArray skillBonuses = new JSONArray();
                for(JCheckBox checkBox : checkGroup){
                    skillBonuses.put(Boolean.toString(checkBox.isSelected()));
                }
                data.put("skill bonuses", skillBonuses);

                data.put("inventory",new JSONObject(MainFrame.inventory.toString()));

                RPGCharacter.writeCharJSON(data);
                //TODO: Add inventory to JSON data
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

    public void updateFormData(JSONObject charStats){
        int counter = 0;
        playerF.setText(charStats.getString("player name")); counter++;
        charF.setText(charStats.getString("char name")); counter++;
        classF.setText(charStats.getString("class")); counter++;
        raceF.setText(charStats.getString("race")); counter++;
        levelF.setText(charStats.getString("level")); counter++;
        alignmentF.setText(charStats.getString("alignment")); counter++;
        xpF.setText(charStats.getString("xp")); counter++;
        inspirationCheckBox.setSelected(charStats.getBoolean("inspiration")); counter++;
        acField.setText(charStats.getString("ac")); counter++;
        speedField.setText(charStats.getString("speed")); counter++;
        maxHPField.setText(charStats.getString("max HP")); counter++;
        currentHPField.setText(charStats.getString("current HP")); counter++;
        hitDiceSizeF.setText(charStats.getString("hit dice size")); counter++;
        currentHitDiceField.setText(charStats.getString("current hit dice")); counter++;
        strengthF.setText(charStats.getString("strength")); counter++;
        dexterityF.setText(charStats.getString("dexterity")); counter++;
        constitutionF.setText(charStats.getString("constitution")); counter++;
        intelligenceF.setText(charStats.getString("intelligence")); counter++;
        wisdomF.setText(charStats.getString("wisdom")); counter++;
        charismaF.setText(charStats.getString("charisma")); counter++;
        SB.setText("SB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(strengthF.getText())));
        DB.setText("DB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(dexterityF.getText())));
        CB.setText("CB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(constitutionF.getText())));
        IB.setText("IB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(intelligenceF.getText())));
        WB.setText("WB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(wisdomF.getText())));
        ChB.setText("ChB: "+RPGCharacter.calculateActionBonus(Integer.parseInt(charismaF.getText())));
        int profBonus = RPGCharacter.calculateProficiencyBonus(Integer.parseInt(levelF.getText()));
        boolean[][] skills = {new boolean[2],
                new boolean[4],
                new boolean[1],
                new boolean[6],
                new boolean[6],
                new boolean[5]};
        int k = 0;
        for (int i = 0; i < skills.length; i++) {
            for (int j = 0; j < skills[i].length; j++) {
                skills[i][j] = charStats.getJSONArray("skill bonuses").getBoolean(k);
                k++;
            }
        }
        String append = "";
        if(profBonus >= 0)
            append += "+";
        append += String.valueOf(profBonus);
        proficiency.setText("Proficiency Bonus:  "+append);
        maxHitDiceAmount.setText(String.valueOf(levelF.getText()));

        int groupCounter = 0;
        for(int i = 0; i<skills.length; i++){
            for(int j=0;j<skills[i].length;j++){
                JCheckBox currentBox = checkGroup[groupCounter];
                String currentString = currentBox.getText();
                if(Character.isDigit(currentString.charAt(currentString.length()-1))&&Character.isDigit(currentString.charAt(currentString.length()-2)))  //Checks for double digit bonus(rare but could happen)
                    currentString = currentString.substring(0,currentString.length()-5);
                else
                    currentString = currentString.substring(0,currentString.length()-4);  // 4 so the "  +0" (or any integer) at the end of the string is replaced.
                currentBox.setSelected(skills[i][j]);
                int bonus;
                String bString;
                switch(i) {
                    case 0:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(strengthF.getText()));
                        break;
                    case 1:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(dexterityF.getText()));
                        break;
                    case 2:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(constitutionF.getText()));
                        break;
                    case 3:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(intelligenceF.getText()));
                        break;
                    case 4:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(wisdomF.getText()));
                        break;
                    case 5:
                        bonus = RPGCharacter.calculateActionBonus(Integer.parseInt(charismaF.getText()));
                        break;
                    default:
                        bonus = 0;
                        break;
                }
                if(currentBox.isSelected())
                    bonus += profBonus;
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
