package backEnd;

import GUI.MainFrame;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Spellbook {

    private ArrayList<Spell> spells = new ArrayList<>();
    private String sep = "@";

    private int attackBonus;
    private int spellDC;
    private int spellAbility = 1; // must be 1-6, 1:str, 2:con, 3:dex, 4:int, 5:wis, 6:cha

    JSONObject charStats;

    public Spellbook(JSONObject c){
        charStats = c;

        JSONArray textBook = charStats.getJSONArray("spellbook");
        int length = textBook.length();
        if (!textBook.isEmpty()) {
            for (int i = 0; i < length; i++) {
                addSpell(textBook.getString(i));
            }
        }


        setAttackBonusNDC(charStats.getInt("spell ability"));
    }

    public Spellbook(){

    }

    // @param infoLine must come in the form "name, level, castingTime, range, duration, damageDice, description, isPrepared"
    public void addSpell(String infoLine){
        String[] stringData = infoLine.split(sep);
        spells.add(new Spell(stringData[0], stringData[1], stringData[2], stringData[3], stringData[4], stringData[5], stringData[6], Boolean.parseBoolean(stringData[7])));
    }


    public String toString(){
        String rhet = "[\n";
        for(Spell spell : spells){
            rhet += "\t\"" + spell.toString() + "\",\n";
        }
        if(!spells.isEmpty())
            rhet = rhet.substring(0, rhet.length() - 2);
        rhet += "\n],";
        return rhet;
    }

    public ArrayList<Spell> getSpells(){return spells;}

    public int getAttackBonus(){return attackBonus;}

    public int getSpellDC(){return spellDC;}

    public void setAttackBonusNDC(int bonus){
        switch(bonus){
            case 1:
                attackBonus = MainFrame.getCharFrame().getSB();
                break;
            case 2:
                attackBonus = MainFrame.getCharFrame().getCB();
                break;
            case 3:
                attackBonus = MainFrame.getCharFrame().getDB();
                break;
            case 4:
                attackBonus = MainFrame.getCharFrame().getIB();
                break;
            case 5:
                attackBonus = MainFrame.getCharFrame().getWB();
                break;
            case 6:
                attackBonus = MainFrame.getCharFrame().getChB();
                break;
            default:
                attackBonus = 0;
                break;
        }
        attackBonus += RPGCharacter.calculateProficiencyBonus(Integer.parseInt(charStats.getString("level")));
        spellDC = attackBonus+8;
        spellAbility = bonus;
    }

    public int getSpellAbility(){return spellAbility;}
}
