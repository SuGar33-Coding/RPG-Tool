package GUI;

import backEnd.RPGCharacter;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;

public class noteForm {
    public JPanel notePanel;
    private JTextArea featsTraitsProficiencies;
    private JScrollPane backgroundScrollPane;
    private JScrollPane notesScrollPane;
    private JTextArea backgroundArea;
    private JTextArea notesArea;

    private RPGCharacter actor;

    public noteForm(RPGCharacter character){
        this.actor = character;

        Color background = notePanel.getBackground();
        backgroundArea.setText(actor.background);
        backgroundArea.setBackground(background.brighter());
        notesArea.setText(actor.notes);
        notesArea.setBackground(background.brighter());
        featsTraitsProficiencies.setText(actor.featsntraits);
        featsTraitsProficiencies.setBackground(background.brighter());

        backgroundArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                actor.background = backgroundArea.getText();
            }
        });

        notesArea
                .addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                actor.notes = notesArea.getText();
            }
        });

        featsTraitsProficiencies.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                actor.featsntraits = featsTraitsProficiencies.getText();
            }
        });
    }


    private void createUIComponents() {

    }
}
