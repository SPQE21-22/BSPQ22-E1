package client.GUI;

import javax.swing.*;
import client.controller.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private final JTextField email;
    private final JPasswordField password;
    //private final UserController userController;

    public LoginDialog(MainWindow mainWindow) {
        super(mainWindow, "Login", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 200));
        this.email = new JTextField();
        this.password = new JPasswordField();
        //this.userController = userController;

        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(actionEvent -> {
            if (!this.getEmail().isEmpty() && this.password.getPassword().length != 0) {
                //boolean loginResult = this.userController.login(this.getEmail(), this.getPassword()));
                boolean loginResult = false;
                if (this.getEmail().equals("paco@gmail.com") && this.getPassword().equals("1234")){
                    loginResult = true;
                }
                if (loginResult) {
                    mainWindow.getTabbedPane().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong email/password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(2,2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.add(submitButton);

        this.setLayout(new BorderLayout());
        formPanel.add(emailLabel);
        formPanel.add(this.email);
        formPanel.add(passwordLabel);
        formPanel.add(this.password);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(loginPanel, BorderLayout.SOUTH);
    }

    public String getEmail() {
        return this.email.getText();
    }

    private String getPassword() {
        String basePassword = String.valueOf(this.password.getPassword());
        return basePassword;
    }
}
