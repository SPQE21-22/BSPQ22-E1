package GUI;
import javax.swing.*;
import controller.*;
import data.domain.Book;
import data.domain.User;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RegisterDialog extends JDialog {
    private final JTextField name;
    private final JTextField email;
    private final JPasswordField password;
    private final JFormattedTextField birthdate;
    //private final UserController userController;

    public RegisterDialog(MainWindow mainWindow) {
        super(mainWindow, "Register", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(350, 500));

        JLabel nameLabel = new JLabel("Name");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel birthdateLabel = new JLabel("Birthdate");

        this.name = new JTextField();
        this.email = new JTextField();
        this.password = new JPasswordField();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.birthdate = new JFormattedTextField(df);

        JButton submitButton = new JButton("Register");

        submitButton.addActionListener(actionEvent -> {
            if (this.validForm()) {
                //boolean registerResult = userController.register(this.getName(), this.getEmail(), this.getPassword(), this.getBirthDate(), new ArrayList<Book>());
                boolean registerResult = false;
                try {
                    User reguser = new User(this.getName(), this.getEmail(), this.getPassword(), this.getBirthDate(), new ArrayList<Book>());
                    registerResult = true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (registerResult) {
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error carrying out the register process", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel submitPanel = new JPanel(new FlowLayout());
        submitPanel.add(submitButton);

        this.setLayout(new BorderLayout());
        formPanel.add(nameLabel);
        formPanel.add(this.name);
        formPanel.add(emailLabel);
        formPanel.add(this.email);
        formPanel.add(passwordLabel);
        formPanel.add(this.password);
        formPanel.add(birthdateLabel);
        formPanel.add(birthdate);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);
    }

    public String getName() {
        return this.name.getText();
    }

    public String getEmail() {
        return this.email.getText();
    }

    public String getPassword() {
        String basePassword = String.valueOf(this.password.getPassword());
        return basePassword;
    }

    public Date getBirthDate() throws ParseException {
        String dateStr = this.birthdate.getText();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format.format( new Date());
        Date date  = format.parse(dateStr);
        return date;
    }



    private boolean validForm() {
        return  !this.getName().isEmpty() &&
                !this.getEmail().isEmpty() &&
                !this.getPassword().isEmpty() &&
                !this.birthdate.getText().isEmpty();
    }
}
