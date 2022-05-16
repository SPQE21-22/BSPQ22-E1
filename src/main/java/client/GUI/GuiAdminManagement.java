package client.GUI;

import server.data.domain.Book;
import server.data.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiAdminManagement {

    private JFrame guiAdminManagement;

    public GuiAdminManagement(User u, List<Book> ab, String hostname, String port) {
        guiAdminManagement = new JFrame();
        guiAdminManagement.setTitle("Admin");
        guiAdminManagement.setBounds(100, 100, 400, 300);
        guiAdminManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiAdminManagement.getContentPane().setLayout(null);
        guiAdminManagement.setVisible(true);

        JLabel labelTittle = new JLabel("CAFETERIA MANAGEMENT");
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTittle.setFont(new Font("Arial", Font.BOLD, 25));
        labelTittle.setBounds(10, 11, 350, 30);
        guiAdminManagement.getContentPane().add(labelTittle);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 320, 2);
        guiAdminManagement.getContentPane().add(separatorTop);

        JButton buttonManageCafeteriaItems = new JButton("MANAGE CAFETERIA ITEMS");
        buttonManageCafeteriaItems.setFont(new Font("Arial", Font.BOLD, 13));
        buttonManageCafeteriaItems.setBounds(30, 101, 285, 25);
        buttonManageCafeteriaItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiManageCafeteriaItems(u, ab, hostname, port);
                guiAdminManagement.dispose();
            }
        });
        guiAdminManagement.getContentPane().add(buttonManageCafeteriaItems);

        JButton buttonManageCafeteriaMenu = new JButton("MANAGE CAFETERIA MENU");
        buttonManageCafeteriaMenu.setFont(new Font("Arial", Font.BOLD, 13));
        buttonManageCafeteriaMenu.setBounds(30, 137, 285, 25);
        buttonManageCafeteriaMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiManageCafeteriaMenu(u, ab, hostname, port);
                guiAdminManagement.dispose();
            }
        });
        guiAdminManagement.getContentPane().add(buttonManageCafeteriaMenu);

        JButton buttonBack = new JButton("BACK");
        buttonBack.setFont(new Font("Arial", Font.BOLD, 13));
        buttonBack.setBounds(30, 65, 285, 25);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                new GuiMain(u, ab, hostname, port);
                guiAdminManagement.dispose();
            }
        });
        guiAdminManagement.getContentPane().add(buttonBack);
    }
}
