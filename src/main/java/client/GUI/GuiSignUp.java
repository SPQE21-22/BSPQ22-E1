//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class GuiSignUp extends JFrame {
    private static JFrame guiSignUp;
    private JLabel labelTitle;
    private JSeparator separatorTop;
    private JLabel labelName;
    private JTextField textName;
    private JLabel labelEmail;
    private JTextField textEmail;
    private JLabel labelPassword;
    private JTextField textPassword;
    private JLabel labelDob;
    private JSpinner spinnerDay;
    private JSpinner spinnerMonth;
    private JSpinner spinnerYear;
    private JSeparator separatorBottom;
    private JButton buttonSignUp;
    private Font arialBlack13;
    private Font arialBlack30;

    public GuiSignUp() {
        guiSignUp = new JFrame();
        separatorTop = new JSeparator();
        labelTitle = new JLabel("SIGN UP");
        labelName = new JLabel("NAME");
        textName = new JTextField();
        labelEmail = new JLabel("E-MAIL");
        textEmail = new JTextField();
        labelPassword = new JLabel("PASSWORD");
        textPassword = new JTextField();
        labelDob = new JLabel("DATE OF BIRTH (day, month, year)");
        spinnerDay = new JSpinner();
        spinnerMonth = new JSpinner();
        spinnerYear = new JSpinner();
        separatorBottom = new JSeparator();
        buttonSignUp = new JButton("SIGN UP");

        arialBlack13 = new Font("Arial", 1, 13);
        arialBlack30 = new Font("Arial", 1, 30);

        guiSignUp.setTitle("Sign up");
        guiSignUp.setResizable(false);
        guiSignUp.setBounds(100, 100, 330, 430);
        guiSignUp.setDefaultCloseOperation(3);
        guiSignUp.getContentPane().setLayout(null);
        guiSignUp.setVisible(true);

        labelTitle.setFont(arialBlack30);
        labelTitle.setBounds(95, 11, 123, 36);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(40, 58, 238, 2);

        labelName.setFont(arialBlack13);
        labelName.setBounds(40, 71, 37, 16);
        textName.setBounds(40, 98, 238, 20);

        labelEmail.setFont(arialBlack13);
        labelEmail.setBounds(40, 129, 44, 16);
        textEmail.setBounds(40, 156, 238, 20);

        labelPassword.setFont(arialBlack13);
        labelPassword.setBounds(40, 187, 77, 16);
        textPassword.setBounds(40, 214, 238, 20);

        labelDob.setFont(arialBlack13);
        labelDob.setBounds(40, 245, 218, 16);
        spinnerDay.setModel(new SpinnerNumberModel(0, 0, 31, 1));

        spinnerDay.setBounds(40, 272, 39, 21);
        spinnerMonth.setModel(new SpinnerNumberModel(0, 0, 12, 1));
        spinnerMonth.setBounds(78, 272, 39, 21);
        spinnerYear.setModel(new SpinnerNumberModel(2000, 1922, 2022, 1));
        spinnerYear.setBounds(112, 272, 63, 21);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(40, 311, 238, 2);

        buttonSignUp.setFont(arialBlack13);
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonSignUp.setBounds(112, 324, 89, 25);

        guiSignUp.getContentPane().add(labelTitle);
        guiSignUp.getContentPane().add(separatorTop);
        guiSignUp.getContentPane().add(labelName);
        guiSignUp.getContentPane().add(textName);
        guiSignUp.getContentPane().add(labelEmail);
        guiSignUp.getContentPane().add(textEmail);
        guiSignUp.getContentPane().add(labelPassword);
        guiSignUp.getContentPane().add(textPassword);
        guiSignUp.getContentPane().add(labelDob);
        guiSignUp.getContentPane().add(spinnerDay);
        guiSignUp.getContentPane().add(spinnerMonth);
        guiSignUp.getContentPane().add(spinnerYear);
        guiSignUp.getContentPane().add(separatorBottom);
        guiSignUp.getContentPane().add(buttonSignUp);
    }
}
