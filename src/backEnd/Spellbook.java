package backEnd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Spellbook {

    private ArrayList<Spell> spells = new ArrayList<>();
    private String sep = "@";

    public Spellbook(JSONObject charStats){

        JSONArray textBook = charStats.getJSONArray("spellbook");
        int length = textBook.length();
        if (!textBook.isEmpty()) {
            for (int i = 0; i < length; i++) {
                addSpell(textBook.getString(i));
            }
        }

    }

    public Spellbook(){

    }

    // @param infoLine must come in the form "name, level, castingTime, range, duration, damageDice, description"
    public void addSpell(String infoLine){
        String[] stringData = infoLine.split(sep);
        spells.add(new Spell(stringData[0], stringData[1], stringData[2], stringData[3], stringData[4], stringData[5], stringData[6]));
    }


    public String toString(){
        String rhet = "[\n";
        for(Spell spell : spells){
            rhet += "\t\"" + spell.toString() + "\",\n";
        }
        rhet = rhet.substring(0, rhet.length() - 2);
        rhet += "\n],";
        return rhet;
    }

    public ArrayList<Spell> getSpells(){return spells;}
}
