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
    private String spellAbility; // must be 1-6, 1:str, 2:con, 3:dex, 4:int, 5:wis, 6:cha

    RPGCharacter actor;

    /**
     * Default constructor
     * Spellbook is always dependent on an actor instance
     */
    public Spellbook(RPGCharacter character) {
        this.actor = character;
        this.spellAbility = "intelligence";
        //setAttackBonusNDC(spellAbility);
    }

    public Spellbook(RPGCharacter character, JSONObject charJSON){
        this.actor = character;

        JSONArray textBook = charJSON.getJSONArray("spellbook");
        int length = textBook.length();
        if (!textBook.isEmpty()) {
            for (int i = 0; i < length; i++) {
                addSpell(textBook.getString(i));
            }
        }


        setAttackBonusNDC(charJSON.getString("spell ability"));
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

    public void setAttackBonusNDC(String bonus){
        switch(bonus){
            case "strength":
                attackBonus = Integer.parseInt(actor.getSB());
                break;
            case "constitution":
                attackBonus = Integer.parseInt(actor.getCB());
                break;
            case "dexterity":
                attackBonus = Integer.parseInt(actor.getDB());
                break;
            case "intelligence":
                attackBonus = Integer.parseInt(actor.getIB());
                break;
            case "wisdom":
                attackBonus = Integer.parseInt(actor.getWB());
                break;
            case "charisma":
                attackBonus = Integer.parseInt(actor.getChB());
                break;
            default:
                attackBonus = 0;
                break;
        }
        attackBonus += Integer.parseInt(actor.getLevel());
        spellDC = attackBonus+8;
        spellAbility = bonus;
    }

    public String getSpellAbility(){return spellAbility;}
}
