package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import backEnd.Dicey;

public class DI {
    public JPanel DicePanel;
    private JTextField numField;
    private JTextField sideField;
    private JTextField buffField;
    private JLabel d;
    private JLabel bufLab;
    private JLabel resLab;
    private JButton backButton;
    private JButton rollButton;

    public DI() {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.init();  // Return to main menu
            }
        });
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numf = numField.getText();
                String sidef = sideField.getText();
                String bufff = buffField.getText();
                if(!(numf.isEmpty()) && !(sidef.isEmpty())){  //If fields left empty, do nothing
                    int buff;
                    int num;
                    int sides;
                    try {buff = Integer.parseInt(bufff);} catch (NumberFormatException ex) {buff = 0;}
                    try {num = Integer.parseInt(numf);} catch (NumberFormatException ex) {num = 0;}
                    try {sides = Integer.parseInt(sidef);} catch (NumberFormatException ex) {sides = 0;}

                    int rolls[] = Dicey.Roll(num,sides,buff);
                    String rollString = String.valueOf(rolls[0]);
                    for(int i=1; i<rolls.length-2;i++){
                        rollString += " + " + String.valueOf(rolls[i]);
                    }
                    rollString += " = " + String.valueOf(rolls[rolls.length-2]);
                    if(buff != 0) {
                        if (buff > 0)
                            rollString += "+";
                        rollString += buff + " = " + String.valueOf(rolls[rolls.length - 1]);
                    }
                    resLab.setText("Result: " + rollString);
                }
            }
        });
    }
}
