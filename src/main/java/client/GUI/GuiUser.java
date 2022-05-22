package client.GUI;

import server.data.domain.Book;
import server.data.domain.Fine;
import server.data.domain.User;
import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private JScrollPane booksScroll;
    private JLabel labelFines;
    private JList<String> listFines;
    private JScrollPane finesScroll;
    private JLabel labelMenu;
    private JList<String> listMenu;
    private JScrollPane menuScroll;
    private JLabel labelExpenses;
    private JList<String> listExpenses;
    private JScrollPane expensesScroll;
    private JSeparator separatorBottom;
    private JButton buttonBack;
    private JButton buttonUpdate;
    Client client;
    WebTarget webTarget;
    private Font arialBlack13;
    private Font arial13;
    private Font arialBlack30;

    public GuiUser(User u, List<Book> ab, String hostname, String port){
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        guiUser = new JFrame();
        labelTitle = new JLabel("USER INFORMATION");
        separatorTop = new JSeparator();
        labelName = new JLabel("NAME");
        textName = new JTextField();
        labelEmail = new JLabel("E-MAIL");
        textEmail = new JTextField();
        labelBooks = new JLabel("BOOKS");
        listBooks = new JList<String>(modelBooks(u));
        booksScroll = new JScrollPane(listBooks);
        labelFines = new JLabel("FINES:");
        listFines = new JList<String>(modelFines(u));
        finesScroll = new JScrollPane(listFines);
        labelMenu = new JLabel("MENU:");
        listMenu = new JList<String>();
        menuScroll = new JScrollPane(listMenu);
        labelExpenses = new JLabel("EXPENSES:");
        listExpenses = new JList<String>();
        expensesScroll = new JScrollPane(listExpenses);
        separatorBottom = new JSeparator();
        buttonBack = new JButton("BACK");
        buttonUpdate = new JButton("UPDATE");

        arialBlack13 = new Font("Arial", Font.BOLD, 13);
        arialBlack30 = new Font("Arial", Font.BOLD, 30);
        arial13 = new Font("Arial",Font.PLAIN, 13);

        /* Parametrizaciones */
        guiUser.setTitle("User information");
        guiUser.setResizable(false);
        guiUser.setBounds(100, 100, 450, 625);
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
        listBooks.setBounds(69, 163, 302, 100);

        booksScroll.setBounds(69, 163, 302, 100);

        labelFines.setFont(arialBlack13);
        labelFines.setBounds(66, 274, 47, 16);

        listFines.setFont(arial13);
        listFines.setEnabled(false);
        listFines.setBounds(66, 296, 302, 100);

        finesScroll.setBounds(66, 296, 302, 100);

        labelMenu.setFont(arialBlack13);
        labelMenu.setBounds(66, 407, 47, 16);

        listMenu.setFont(arial13);
        listMenu.setEnabled(false);
        listMenu.setBounds(66, 429, 145, 100);

        menuScroll.setBounds(66, 429, 145, 100);

        labelExpenses.setFont(arialBlack13);
        labelExpenses.setBounds(223, 407, 89, 16);

        listExpenses.setFont(arial13);
        listExpenses.setEnabled(false);
        listExpenses.setBounds(223, 429, 145, 100);

        expensesScroll.setBounds(223, 429, 145, 100);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(55, 540, 324, 2);

        buttonBack.setFont(arialBlack13);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMain(u, ab, hostname, port);
                guiUser.dispose();
            }
        });
        buttonBack.setBounds(66, 553, 70, 25);

        buttonUpdate.setFont(arialBlack13);
        buttonUpdate.setBounds(279, 554, 89, 23);

        /* Add the components to the GUI */
        guiUser.getContentPane().add(labelTitle);
        guiUser.getContentPane().add(separatorTop);
        guiUser.getContentPane().add(labelName);
        guiUser.getContentPane().add(textName);
        guiUser.getContentPane().add(labelEmail);
        guiUser.getContentPane().add(textEmail);
        guiUser.getContentPane().add(labelBooks);
        guiUser.getContentPane().add(listBooks);
        guiUser.getContentPane().add(booksScroll);
        guiUser.getContentPane().add(labelFines);
        guiUser.getContentPane().add(listFines);
        guiUser.getContentPane().add(finesScroll);
        guiUser.getContentPane().add(labelMenu);
        guiUser.getContentPane().add(listMenu);
        guiUser.getContentPane().add(menuScroll);
        guiUser.getContentPane().add(labelExpenses);
        guiUser.getContentPane().add(listExpenses);
        guiUser.getContentPane().add(listExpenses);
        guiUser.getContentPane().add(separatorBottom);
        guiUser.getContentPane().add(buttonBack);
    }

    public DefaultListModel<String> modelBooks(User u){
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < u.getBooks().size(); i++){
            model.add(i, u.getBooks().get(i).toString());
        }
        return model;
    }

    public DefaultListModel<String> modelFines(User u){
        Fine fine = new Fine(u, 30);
        WebTarget bookWebTarget = webTarget.path("users/fines");
        Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            User fines = response.readEntity(User.class);
            u.setFines(fines.getFines());
        }

        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < u.getFines().size(); i++){
            model.add(i, u.getFines().get(i).toString());
        }
        return model;
    }
}
