package GUI;

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

    public noteForm(){
        Color background = notePanel.getBackground();
        backgroundArea.setText(MainFrame.background);
        backgroundArea.setBackground(background.brighter());
        notesArea.setText(MainFrame.notes);
        notesArea.setBackground(background.brighter());
        featsTraitsProficiencies.setText(MainFrame.featsntraits);
        featsTraitsProficiencies.setBackground(background.brighter());

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
