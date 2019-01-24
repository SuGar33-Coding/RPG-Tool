package GUI;

import backEnd.Spell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class spellDetailForm {
    public JPanel spellDetailPanel;
    private JTextField levelTextField;
    private JTextField castingTimeTextField;
    private JTextField rangeTextField;
    private JTextField diceTextField;
    private JTextField durationTextField;
    private JTextArea notesTextArea;
    private JButton editButton;
    private JLabel nameLabel;

    public spellDetailForm(Spell spell){
        notesTextArea.setLineWrap(true);
        notesTextArea.setWrapStyleWord(true);
        notesTextArea.setBackground(spellDetailPanel.getBackground().brighter());


        nameLabel.setText(spell.getName()+" ");
        levelTextField.setText(spell.getLevel());
        castingTimeTextField.setText(spell.getCastingTime());
        rangeTextField.setText(spell.getRange());
        diceTextField.setText(spell.getDamageDice());
        durationTextField.setText(spell.getDuration());
        notesTextArea.setText(spell.getDescription());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spell.setName(nameLabel.getText());
                spell.setLevel(levelTextField.getText());
                spell.setCastingTime(castingTimeTextField.getText());
                spell.setRange(rangeTextField.getText());
                spell.setDamageDice(diceTextField.getText());
                spell.setDuration(durationTextField.getText());
                spell.setDescription(notesTextArea.getText());
            }
        });
    }
}
