package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.UI;
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
                UI.init();
            }
        });
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(numField.getText());
                int sides = Integer.parseInt(sideField.getText());
                int buff = Integer.parseInt(buffField.getText());
                resLab.setText(String.valueOf(Dicey.Roll(num,sides,buff)));
            }
        });
    }
}
