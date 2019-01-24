package GUI;

import backEnd.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class detailForm {
    private JPanel namePane;
    private JLabel nameLabel;
    private JLabel bonusLabel;
    private JTextField bonusField;
    private JTextArea notesArea;
    private JTextField damageTextField;
    public JPanel detailPanel;
    private JPanel statsPane;
    private JPanel damagePane;
    private JLabel damageLabel;
    private JButton editButton;

    public detailForm(Item item){
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        notesArea.setBackground(detailPanel.getBackground().brighter());

        if(item.isWeapon())
            weaponInit(item);
        else
            armorInit(item);


        nameLabel.setText(item.getName()+" ");
        int bonus = item.getBonus();
        if(bonus >= 0)
            bonusField.setText("+"+Integer.toString(bonus));
        else
            bonusField.setText(Integer.toString(bonus));
        notesArea.setText(item.getDescription());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(item.isWeapon())
                    item.setDamageDice(damageTextField.getText());
                item.setDescription(notesArea.getText());
                try{item.setBonus(Integer.parseInt(bonusField.getText()));} catch (NumberFormatException ex){}
            }
        });

    }

    private void weaponInit(Item item){
        damageTextField.setText(item.getDamageDice());
        bonusLabel.setText("Attack Bonus:");
    }

    private void armorInit(Item item){
        damagePane.remove(damageTextField);
        damagePane.remove(damageLabel);
        damagePane.revalidate();
        damagePane.repaint();
        bonusLabel.setText("AC Bonus:");
    }
}
