package backEnd;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Spellbook {

    private ArrayList<Spell> spells;
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

    public void addSpell(String infoLine){

    }


    public String toString(){
        return "[]";
    }
}
