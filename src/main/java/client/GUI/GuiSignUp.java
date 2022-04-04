//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.GUI;

import server.data.domain.Book;
import server.data.domain.User;


import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GuiSignUp extends JFrame {
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget;
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
    private JFormattedTextField textDob;
    private JSeparator separatorBottom;
    private JButton buttonSignUp;
    private Font arialBlack13;
    private Font arialBlack30;

    public GuiSignUp(String hostname, String port) {
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        textDob = new JFormattedTextField(df);
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
        textDob.setBounds(40, 272, 238, 20);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(40, 311, 238, 2);

        buttonSignUp.setFont(arialBlack13);
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (valid()){
                    User u0 = new User();
                    u0.setName(textName.getText());
                    u0.setEmail(textEmail.getText());
                    u0.setPassword(textPassword.getText());
                    u0.setBirthDate(dateDob());
                    u0.setBooks(new ArrayList<Book>());

                    WebTarget bookWebTarget = webTarget.path("users/createUser");
                    Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                    Response response = invocationBuilder.post(Entity.entity(u0, MediaType.APPLICATION_JSON));
                    if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                        System.out.println("Error");
                    }
                    Book b0 = new Book();
                    b0.setName("El imperio final");
                    b0.setAuthor("Brandon Sanderson");
                    b0.setPublishDate(new Date());
                    b0.setAvailable(true);
                    Book b1 = new Book();
                    b1.setName("El pozo de la ascension");
                    b1.setAuthor("Brandon Sanderson");
                    b1.setPublishDate(new Date());
                    b1.setAvailable(true);
                    ArrayList<Book> prueba = new ArrayList<Book>();
                    prueba.add(b1);
                    prueba.add(b0);

                    List<Book> ab = new ArrayList<Book>();
                    ab.add(b0);
                    ab.add(b1);

                    new GuiMain(u0, ab, hostname, port);
                    guiSignUp.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Please, fill in all the data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

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
        guiSignUp.getContentPane().add(textDob);
        guiSignUp.getContentPane().add(separatorBottom);
        guiSignUp.getContentPane().add(buttonSignUp);
    }

    private Date dateDob(){
        String dobStr = textDob.getText();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date dobD = new Date();
        try {
            f.parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dobD;
    }

    private boolean valid(){
        return !textName.getText().isEmpty() && !textEmail.getText().isEmpty() && !textPassword.getText().isEmpty() && !textDob.getText().isEmpty();
    }
}
