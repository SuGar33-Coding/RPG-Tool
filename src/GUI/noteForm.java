package GUI;

import backEnd.RPGCharacter;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class noteForm {
    public JPanel notePanel;
    private JTextArea featsTraitsProficiencies;
    private JScrollPane backgroundScrollPane;
    private JScrollPane notesScrollPane;
    private JTextArea backgroundArea;
    private JTextArea notesArea;

    public noteForm(){
        backgroundArea.setText(MainFrame.background);
        notesArea.setText(MainFrame.notes);
        featsTraitsProficiencies.setText(MainFrame.featsntraits);

        backgroundArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                MainFrame.background = backgroundArea.getText();
            }
        });

        notesArea
                .addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                MainFrame.notes = notesArea.getText();
            }
        });

        featsTraitsProficiencies.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                MainFrame.featsntraits = featsTraitsProficiencies.getText();
            }
        });
    }


    private void createUIComponents() {

    }
}
