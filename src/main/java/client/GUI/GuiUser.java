package client.GUI;

import server.data.domain.Book;
import server.data.domain.User;
import server.data.dto.BookDTO;
import server.data.dto.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiUser extends JFrame {

    private static JFrame guiUser;
    private JLabel labelTitle;
    private JSeparator separatorTop;
    private JLabel labelName;
    private JTextField textName;
    private JLabel labelEmail;
    private JTextField textEmail;
    private JLabel labelBooks;
    private JList<String> listBooks;
    private JSeparator separatorBottom;
    private JButton buttonBack;
    private JButton buttonUpdate;

    private Font arialBlack13;
    private Font arial13;
    private Font arialBlack30;

    public GuiUser(UserDTO u, ArrayList<BookDTO> ab, String hostname, String port){
        guiUser = new JFrame();
        labelTitle = new JLabel("USER INFORMATION");
        separatorTop = new JSeparator();
        labelName = new JLabel("NAME");
        textName = new JTextField();
        labelEmail = new JLabel("E-MAIL");
        textEmail = new JTextField();
        labelBooks = new JLabel("BOOKS");
        listBooks = new JList<String>(modelBooks(u));
        separatorBottom = new JSeparator();
        buttonBack = new JButton("BACK");
        buttonUpdate = new JButton("UPDATE");

        arialBlack13 = new Font("Arial", Font.BOLD, 13);
        arialBlack30 = new Font("Arial", Font.BOLD, 30);
        arial13 = new Font("Arial",Font.PLAIN, 13);

        /* Parametrizaciones */
        guiUser.setTitle("Sign up");
        guiUser.setResizable(false);
        guiUser.setBounds(100, 100, 450, 430);
        guiUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiUser.getContentPane().setLayout(null);
        guiUser.setVisible(true);

        labelTitle.setFont(arialBlack30);
        labelTitle.setBounds(66, 11, 302, 36);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(55, 58, 324, 2);

        labelName.setFont(arialBlack13);
        labelName.setBounds(66, 75, 37, 16);

        textName.setBackground(Color.WHITE);
        textName.setText(u.getName());
        textName.setFont(arial13);
        textName.setEditable(false);
        textName.setBounds(153, 71, 215, 20);

        labelEmail.setFont(arialBlack13);
        labelEmail.setBounds(66, 108, 46, 14);

        textEmail.setBackground(Color.WHITE);
        textEmail.setFont(arial13);
        textEmail.setText(u.getEmail());
        textEmail.setEditable(false);
        textEmail.setBounds(153, 102, 215, 20);

        labelBooks.setFont(arialBlack13);
        labelBooks.setBounds(66, 141, 47, 16);

        listBooks.setFont(arial13);
        listBooks.setEnabled(false);
        listBooks.setBounds(69, 163, 299, 130);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(55, 308, 324, 2);

        buttonBack.setFont(arialBlack13);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMain(u, ab, hostname, port);
                guiUser.dispose();
            }
        });
        buttonBack.setBounds(66, 324, 70, 25);

        buttonUpdate.setFont(arialBlack13);
        buttonUpdate.setBounds(279, 325, 89, 23);

        /* Add the components to the GUI */
        guiUser.getContentPane().add(labelTitle);
        guiUser.getContentPane().add(separatorTop);
        guiUser.getContentPane().add(labelName);
        guiUser.getContentPane().add(textName);
        guiUser.getContentPane().add(labelEmail);
        guiUser.getContentPane().add(textEmail);
        guiUser.getContentPane().add(labelBooks);
        guiUser.getContentPane().add(listBooks);
        guiUser.getContentPane().add(separatorBottom);
        guiUser.getContentPane().add(buttonBack);
    }

    public DefaultListModel<String> modelBooks(UserDTO u){
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < u.getBooks().size(); i++){
            model.add(i, u.getBooks().get(i).toString());
        }
        return model;
    }
}
