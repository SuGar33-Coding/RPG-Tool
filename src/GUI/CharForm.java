package GUI;

import backEnd.IO;
import backEnd.RPGCharacter;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton rollButton;
    private JButton invButton;
    private JButton spellButton;
    private JButton noteButton;
    private JCheckBox[] checkGroup =
            {strSave,athleticsCheckBox,dexSave,acrobaticsCheckBox,sleightOfHandCheckBox,stealthCheckBox,conSave,intSave,arcanaCheckBox,historyCheckBox,investigationCheckBox,
                    natureCheckBox,religionCheckBox,wisSave,animalhCheckBox,insightCheckBox,medicineCheckBox,perceptionCheckBox,survivalCheckBox,chSave,deceptionCheckBox,
                    intimidationCheckBox,performanceCheckBox,persuasionCheckBox}; // Just a list of all checkboxes/skills

    /* reference to the active character */
    public RPGCharacter actor;

    private JFrame frame;
    private final int diceyWidth = 500;
    private final int diceyHeight = 300;
    private final int invWidth = 750;
    private final int invHeight = 500;

    private int profBonus = 0;

    /**
     * Only intended constructor
     * @param actor the active character instance
     * @param frame
     */
    public CharForm(RPGCharacter actor, JFrame frame) {
        this.actor = actor;
        this.frame = frame;
        addListeners();
    }

    private void addListeners(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.init();  // Return to main menu
            }
        });
        updateSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToActor(actor);

                JSONObject data = characterToJSON(actor);

                IO.writeCharJSON(data,MainFrame.getFilepath());

                updateFormData(actor);
            }
        });


        levelF.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                maxHitDiceAmount.setText(levelF.getText()+" ");
                try{
                    int lvl = Integer.parseInt(levelF.getText());
                    int bonus = RPGCharacter.calculateProficiencyBonus(lvl);
                    String append = "";
                    if(bonus >= 0)
                        append += "+";
                    append += String.valueOf(bonus)+"  ";
                    proficiency.setText("Proficiency Bonus:  "+append);
                } catch (NumberFormatException ex){}

            }
        });

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame diceFrame = new JFrame("Dicey");
                diceFrame.setContentPane(new DI(actor).DicePanel);
                diceFrame.setPreferredSize(new Dimension(diceyWidth, diceyHeight));
                diceFrame.pack();
                diceFrame.setLocation(frame.getLocation().x-diceyWidth,frame.getLocation().y);
                diceFrame.setVisible(true);

            }
        });

        invButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame invFrame = new JFrame("Inventory");
                invFrame.setContentPane(new InvForm(CharForm.this).invPanel);
                invFrame.setPreferredSize(new Dimension(invWidth, invHeight));
                invFrame.pack();
                invFrame.setLocation(frame.getLocation().x+frame.getWidth(),frame.getLocation().y);
                invFrame.setVisible(true);

            }
        });

        noteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noteWidth = 500;
                JFrame noteFrame = new JFrame("Notes");
                noteFrame.setContentPane(new noteForm(actor).notePanel);
                noteFrame.setPreferredSize(new Dimension(noteWidth, 700));
                noteFrame.pack();
                noteFrame.setLocation(frame.getLocation().x-noteWidth,frame.getLocation().y+diceyHeight);
                noteFrame.setVisible(true);

            }
        });

        spellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame spellFrame = new JFrame("Spellbook");
                spellFrame.setContentPane(new SpellForm(CharForm.this).spellPane);
                spellFrame.setPreferredSize(new Dimension(700, 500));
                spellFrame.pack();
                spellFrame.setLocation(frame.getLocation().x+frame.getWidth(),frame.getLocation().y+invHeight);
                spellFrame.setVisible(true);

            }
        });
    }

    private void saveToActor(RPGCharacter character) {
        character.setPlayerName(playerF.getText());
        character.setCharName(charF.getText());
        character.setCharClass(classF.getText());
        character.setRace(raceF.getText());
        character.setLevel(levelF.getText());
        character.setAlignment(alignmentF.getText());
        character.setXp(xpF.getText());
        character.setInspiration(inspirationCheckBox.isSelected());
        character.setAc(acField.getText());
        character.setSpeed(speedField.getText());
        character.setMaxHP(maxHPField.getText());
        character.setCurrentHP(currentHPField.getText());
        character.setHitDiceSize(hitDiceSizeF.getText());
        character.setCurrentHitDice(currentHitDiceField.getText());
        character.setStrength(strengthF.getText());
        character.setDexterity(dexterityF.getText());
        character.setConstitution(constitutionF.getText());
        character.setIntelligence(intelligenceF.getText());
        character.setWisdom(wisdomF.getText());
        character.setCharisma(charismaF.getText());
        boolean[] skills = new boolean[checkGroup.length];
        int i = 0;
        for(JCheckBox checkBox : checkGroup){
            skills[i] = checkBox.isSelected();
            i++;
        }
        character.setSkills1D(skills);
    }

    private JSONObject characterToJSON(RPGCharacter character) {
        JSONObject data = new JSONObject();
        data.put("player name",character.getPlayerName());
        data.put("char name", character.getCharName());
        data.put("class", character.getCharClass());
        data.put("race", character.getRace());
        data.put("level", character.getLevel());
        data.put("alignment", character.getAlignment());
        data.put("xp", character.getXp());
        data.put("inspiration", Boolean.toString(character.isInspiration()));
        data.put("ac", character.getAc());
        data.put("speed", character.getSpeed());
        data.put("max HP", character.getMaxHP());
        data.put("current HP", character.getCurrentHP());
        data.put("hit dice size", character.getHitDiceSize());
        data.put("current hit dice", character.getCurrentHitDice());
        data.put("strength", character.getStrength());
        data.put("dexterity", character.getDexterity());
        data.put("constitution", character.getConstitution());
        data.put("intelligence", character.getIntelligence());
        data.put("wisdom", character.getWisdom());
        data.put("charisma", character.getCharisma());
        JSONArray skillBonuses = new JSONArray();
        for(JCheckBox checkBox : checkGroup){
            skillBonuses.put(Boolean.toString(checkBox.isSelected()));
        }
        data.put("skill bonuses", skillBonuses);
        data.put("inventory",new JSONObject(character.inventory.toString()));
        data.put("background",character.background);
        data.put("notes",character.notes);
        data.put("featuresntraits",character.featsntraits);
        data.put("spellbook",new JSONArray(character.spellBook.toString()));
        data.put("spell ability",character.spellBook.getSpellAbility());

        return data;
    }

    public void updateFormData(RPGCharacter character){
        playerF.setText(character.getPlayerName());
        charF.setText(character.getCharName());
        classF.setText(character.getCharClass());
        raceF.setText(character.getRace());
        levelF.setText(character.getLevel());
        alignmentF.setText(character.getAlignment());
        xpF.setText(character.getXp());
        inspirationCheckBox.setSelected(character.isInspiration());
        acField.setText(character.getAc());
        speedField.setText(character.getSpeed());
        maxHPField.setText(character.getMaxHP());
        currentHPField.setText(character.getCurrentHP());
        hitDiceSizeF.setText(character.getHitDiceSize());
        currentHitDiceField.setText(character.getCurrentHitDice());
        strengthF.setText(character.getStrength());
        dexterityF.setText(character.getDexterity());
        constitutionF.setText(character.getConstitution());
        intelligenceF.setText(character.getIntelligence());
        wisdomF.setText(character.getWisdom());
        charismaF.setText(character.getCharisma());
        try {
            SB.setText("SB: " + RPGCharacter.calculateActionBonus(strengthF.getText())+ " ");
            DB.setText("DB: " + RPGCharacter.calculateActionBonus(dexterityF.getText())+ " ");
            CB.setText("CB: " + RPGCharacter.calculateActionBonus(constitutionF.getText())+ " ");
            IB.setText("IB: " + RPGCharacter.calculateActionBonus(intelligenceF.getText())+ " ");
            WB.setText("WB: " + RPGCharacter.calculateActionBonus(wisdomF.getText())+ " ");
            ChB.setText("ChB: " + RPGCharacter.calculateActionBonus(charismaF.getText())+ " ");
        } catch (NumberFormatException ex){
            SB.setText("0 ");
            DB.setText("0 ");
            CB.setText("0 ");
            IB.setText("0 ");
            WB.setText("0 ");
            ChB.setText("0 ");
        }
        profBonus = Integer.parseInt(actor.getProficiency());

        boolean[][] skills = actor.getSkills();

        String append = "";
        if(profBonus >= 0)
            append += "+";
        append += String.valueOf(profBonus);
        proficiency.setText("Proficiency Bonus:  "+append);
        maxHitDiceAmount.setText(String.valueOf(actor.getLevel()));

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
                        bonus = RPGCharacter.calculateActionBonus(strengthF.getText());
                        break;
                    case 1:
                        bonus = RPGCharacter.calculateActionBonus(dexterityF.getText());
                        break;
                    case 2:
                        bonus = RPGCharacter.calculateActionBonus(constitutionF.getText());
                        break;
                    case 3:
                        bonus = RPGCharacter.calculateActionBonus(intelligenceF.getText());
                        break;
                    case 4:
                        bonus = RPGCharacter.calculateActionBonus(wisdomF.getText());
                        break;
                    case 5:
                        bonus = RPGCharacter.calculateActionBonus(charismaF.getText());
                        break;
                    default:
                        bonus = 0;
                        break;
                }
                if(currentBox.isSelected())
                    bonus += profBonus;
                if(bonus<0)
                    bString = "  " + bonus;
                else
                    bString = "  +" + bonus;
                currentBox.setText(currentString+bString);

                groupCounter++;
            }
        }
    }

    public void setAC(int n){acField.setText(Integer.toString(n));}

    public int getAC(){return Integer.parseInt(acField.getText());}

    public JFrame getFrame(){return frame;}

    public int getSB(){return Integer.parseInt(SB.getText().substring(4,SB.getText().length()-1));}

    public int getDB(){return Integer.parseInt(DB.getText().substring(4,DB.getText().length()-1));}

    public int getIB(){return Integer.parseInt(IB.getText().substring(4,IB.getText().length()-1));}

    public int getCB(){return Integer.parseInt(CB.getText().substring(4,CB.getText().length()-1));}

    public int getWB(){return Integer.parseInt(WB.getText().substring(4,WB.getText().length()-1));}

    public int getChB(){return Integer.parseInt(ChB.getText().substring(5,ChB.getText().length()-1));}

    public int getProfBonus(){return profBonus;}

    public int getInvHeight(){return invHeight;}

    public RPGCharacter getActor() {
        return actor;
    }
}
