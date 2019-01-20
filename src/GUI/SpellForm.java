package GUI;

import backEnd.Spell;
import backEnd.Spellbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SpellForm {
    private JPanel addSpellPanel;
    private JTextField nameField;
    private JTextField levelField;
    private JTextField castingTimeField;
    private JTextField rangeField;
    private JTextField durationField;
    private JTextField damageDiceField;
    private JTextField descriptionField;
    private JButton addButton;
    public JPanel spellPane;
    private JPanel spellsListPane;
    private String sep;
    private CharForm parentCharForm;

    public SpellForm(CharForm c){
        sep = MainFrame.sep;
        parentCharForm = c;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {nameField,levelField,castingTimeField,rangeField,durationField,damageDiceField,descriptionField};
                String infoLine = "";
                for(JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "false";
                MainFrame.spellBook.addSpell(infoLine);
                updateFormData(MainFrame.spellBook);
            }
        });
    }

    private void updateFormData(Spellbook spellbook){
        ArrayList<Spell> spells = spellbook.getSpells();
        for(int count = 0; count < spells.size(); count++){

        }
    }


}
