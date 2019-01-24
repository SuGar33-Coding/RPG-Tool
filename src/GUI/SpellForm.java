package GUI;

import backEnd.RPGCharacter;
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
    private RPGCharacter actor;

    public SpellForm(CharForm form) {
        sep = MainFrame.sep;
        parentCharForm = form;
        this.actor = parentCharForm.actor;

        Color background = spellPane.getBackground().brighter();

        spellListArea.setFont(spellListArea.getFont().deriveFont(20f));
        spellListArea.setBackground(background);
        spellsListPane.setBackground(background);


        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        Dimension d = buttonPanel.getMinimumSize();
        d.width = buttonPanelWidth;
        buttonPanel.setMinimumSize(d);
        buttonPanel.setBackground(background);

        updateFormData(actor.spellBook);

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
                actor.spellBook.addSpell(infoLine);
                updateFormData(actor.spellBook);
            }
        });
    }

    private void updateFormData(Spellbook spellbook) {
        ArrayList<Spell> spells = spellbook.getSpells();

        updateBonusNDC();

        switch(spellbook.getSpellAbility()){
            case "strength":
                strButton.setSelected(true);
                break;
            case "constitution":
                consButton.setSelected(true);
                break;
            case "dexterity":
                dexterityRadioButton.setSelected(true);
                break;
            case "intelligence":
                intelligenceRadioButton.setSelected(true);
                break;
            case "wisdom":
                wisdomRadioButton.setSelected(true);
                break;
            case "charisma":
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
                updateFormData(actor.spellBook);
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
                actor.spellBook.setAttackBonusNDC("strength");
            else if(consButton.isSelected())
                actor.spellBook.setAttackBonusNDC("constitution");
            else if(dexterityRadioButton.isSelected())
                actor.spellBook.setAttackBonusNDC("dexterity");
            else if(intelligenceRadioButton.isSelected())
                actor.spellBook.setAttackBonusNDC("intelligence");
            else if(wisdomRadioButton.isSelected())
                actor.spellBook.setAttackBonusNDC("wisdom");
            else if(charismaRadioButton.isSelected())
                actor.spellBook.setAttackBonusNDC("charisma");


            updateBonusNDC();
        }
    }

    private void updateBonusNDC(){
        spellDCLabel.setText("Spell Save DC: "+actor.spellBook.getSpellDC()+" ");

        if(actor.spellBook.getAttackBonus() >= 0)
            attackBonusLabel.setText("Attack Bonus: +"+actor.spellBook.getAttackBonus()+" ");
        else
            attackBonusLabel.setText("Attack Bonus: "+actor.spellBook.getAttackBonus()+" ");
    }

}