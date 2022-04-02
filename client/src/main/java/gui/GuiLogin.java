package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiLogin extends JFrame {

    private static JFrame guiLogin;
    private JLabel labelTittle;
    private JLabel labelEmail;
    private JTextField textEmail;
    private JLabel labelPassword;
    private JPasswordField textPasword;
    private JSeparator separatorTop;
    private JSeparator separatorBottom;
    private JButton buttonLogin;
    private JLabel labelAccount;
    private JButton buttonSignUp;

    private Font arialBlack13;
    private Font arialBlack30;
    private Font arial13;

    public GuiLogin(){
        guiLogin = new JFrame();
        labelTittle = new JLabel("LOGIN");
        labelEmail = new JLabel("E-MAIL");
        textEmail = new JTextField();
        labelPassword = new JLabel("PASSWORD");
        textPasword = new JPasswordField();
        separatorTop = new JSeparator();
        separatorBottom = new JSeparator();
        buttonLogin = new JButton("LOGIN");
        labelAccount = new JLabel("Don't have an account?");
        buttonSignUp = new JButton("SIGN UP");

        arialBlack13 = new Font("Arial", Font.BOLD, 13);
        arialBlack30 = new Font("Arial", Font.BOLD, 30);
        arial13 = new Font("Arial",Font.PLAIN, 13);

        /* Parametrizaciones */
        guiLogin.setTitle("Login");
        guiLogin.setResizable(false);
        guiLogin.getContentPane().setLayout(null);

        labelTittle.setFont(arialBlack30);
        labelTittle.setBounds(110, 11, 94, 36);

        labelEmail.setFont(arialBlack13);
        labelEmail.setBounds(40, 80, 44, 16);

        textEmail.setBounds(40, 107, 234, 20);

        labelPassword.setFont(arialBlack13);
        labelPassword.setBounds(40, 138, 77, 16);

        textPasword.setBounds(40, 165, 234, 20);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(40, 58, 238, 2);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(40, 259, 238, 2);

        buttonLogin.setFont(arialBlack13);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonLogin.setBounds(110, 210, 89, 23);

        labelAccount.setFont(arial13);
        labelAccount.setBounds(40, 272, 134, 16);

        buttonSignUp.setFont(arialBlack13);
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiSignUp su = new GuiSignUp();
            }
        });
        buttonSignUp.setBounds(40, 297, 89, 23);

        /* Añadir los componentes a la ventana */
        guiLogin.getContentPane().add(labelTittle);
        guiLogin.getContentPane().add(labelEmail);
        guiLogin.getContentPane().add(textEmail);
        guiLogin.getContentPane().add(labelPassword);
        guiLogin.getContentPane().add(textPasword);
        guiLogin.getContentPane().add(separatorTop);
        guiLogin.getContentPane().add(separatorBottom);
        guiLogin.getContentPane().add(buttonLogin);
        guiLogin.getContentPane().add(labelAccount);
        guiLogin.getContentPane().add(buttonSignUp);

        guiLogin.setBounds(100, 100, 330, 400);
        guiLogin.setVisible(true);
        guiLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
