package GUI;

import backEnd.Spell;
import backEnd.Spellbook;

import javax.swing.*;
import java.awt.*;
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
    private JTextArea spellListArea;
    private JPanel buttonPanel;
    private JPanel abilityPanel;
    private JRadioButton strButton;
    private JRadioButton consButton;
    private JRadioButton dexterityRadioButton;
    private JRadioButton intelligenceRadioButton;
    private JRadioButton wisdomRadioButton;
    private JRadioButton charismaRadioButton;
    private JLabel attackBonusLabel;
    private JLabel spellDCLabel;
    private String sep;
    private CharForm parentCharForm;
    private int buttonPanelWidth = 275;
    private Dimension spellPanelDim = new Dimension(buttonPanelWidth, 48);
    private ButtonGroup spellCastingAbilityGroup = new ButtonGroup();
    private JRadioButton[] spellCastingRadioButtons = {strButton,consButton,dexterityRadioButton,intelligenceRadioButton,wisdomRadioButton,charismaRadioButton};
    private final Spellbook spellbook = MainFrame.spellBook;

    public SpellForm(CharForm c) {
        sep = MainFrame.sep;
        parentCharForm = c;

        Color background = spellPane.getBackground().brighter();

        spellListArea.setFont(spellListArea.getFont().deriveFont(20f));
        spellListArea.setBackground(background);
        spellsListPane.setBackground(background);


        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        Dimension d = buttonPanel.getMinimumSize();
        d.width = buttonPanelWidth;
        buttonPanel.setMinimumSize(d);
        buttonPanel.setBackground(background);

        updateFormData(MainFrame.spellBook);

        for(JRadioButton button : spellCastingRadioButtons)
            button.addActionListener(new spellCastingAbilityListener());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {nameField, levelField, castingTimeField, rangeField, durationField, damageDiceField, descriptionField};
                String infoLine = "";
                for (JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "false";
                MainFrame.spellBook.addSpell(infoLine);
                updateFormData(MainFrame.spellBook);
            }
        });
    }

    private void updateFormData(Spellbook spellbook) {
        ArrayList<Spell> spells = spellbook.getSpells();

        updateBonusNDC();

        switch(spellbook.getSpellAbility()){
            case 1:
                strButton.setSelected(true);
                break;
            case 2:
                consButton.setSelected(true);
                break;
            case 3:
                dexterityRadioButton.setSelected(true);
                break;
            case 4:
                intelligenceRadioButton.setSelected(true);
                break;
            case 5:
                wisdomRadioButton.setSelected(true);
                break;
            case 6:
                charismaRadioButton.setSelected(true);
                break;
        }

        buttonPanel.removeAll();

        spellListArea.setText("");

        for (int count = 0; count < spells.size(); count++) {
            Spell spell = spells.get(count);

            spellListArea.append("\n" + spell.getName() + "\n");

            addButtons(count, spells);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void addButtons(int count, ArrayList<Spell> spells) {
        int rigidSpace = 10;

        Spell spell = spells.get(count);

        JButton delButton = new JButton("Delete");
        JButton detailButton = new JButton("Details");
        final JButton prepareButton = new JButton("Prepare");

        if (spell.isPrepared())
            prepareButton.setText("Unprepare");

        Font font = new Font("STLiti", Font.BOLD, 14);
        delButton.setFont(font);
        detailButton.setFont(font);
        prepareButton.setFont(font);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
        itemPanel.add(prepareButton);
        itemPanel.add(Box.createRigidArea(new Dimension(rigidSpace, 5)));
        itemPanel.add(detailButton);
        itemPanel.add(Box.createRigidArea(new Dimension(rigidSpace, 5)));
        itemPanel.add(delButton);
        itemPanel.setMaximumSize(spellPanelDim);
        itemPanel.setBackground(spellPane.getBackground().brighter());
        buttonPanel.add(itemPanel);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spells.remove(count);
                updateFormData(MainFrame.spellBook);
            }
        });

        prepareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (spell.isPrepared()) {
                    spell.setPrepared(false);
                    prepareButton.setText("Prepare");
                } else {
                    spell.setPrepared(true);
                    prepareButton.setText("Unprepare");
                }
            }
        });

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame detailFrame = new JFrame("Details");
                detailFrame.setContentPane(new spellDetailForm(spell).spellDetailPanel);
                detailFrame.setPreferredSize(new Dimension(450, 300));
                detailFrame.pack();
                detailFrame.setVisible(true);
                JFrame frame = parentCharForm.getFrame();
                detailFrame.setLocation(frame.getLocation().x+frame.getWidth(),frame.getLocation().y+parentCharForm.getInvHeight());
            }
        });
    }

    private class spellCastingAbilityListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(strButton.isSelected())
                spellbook.setAttackBonusNDC(1);
            else if(consButton.isSelected())
                spellbook.setAttackBonusNDC(2);
            else if(dexterityRadioButton.isSelected())
                spellbook.setAttackBonusNDC(3);
            else if(intelligenceRadioButton.isSelected())
                spellbook.setAttackBonusNDC(4);
            else if(wisdomRadioButton.isSelected())
                spellbook.setAttackBonusNDC(5);
            else if(charismaRadioButton.isSelected())
                spellbook.setAttackBonusNDC(6);


            updateBonusNDC();
        }
    }

    private void updateBonusNDC(){
        spellDCLabel.setText("Spell Save DC: "+spellbook.getSpellDC()+" ");

        if(spellbook.getAttackBonus() >= 0)
            attackBonusLabel.setText("Attack Bonus: +"+spellbook.getAttackBonus()+" ");
        else
            attackBonusLabel.setText("Attack Bonus: "+spellbook.getAttackBonus()+" ");
    }

}