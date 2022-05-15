package client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAdminManagement {

    private JFrame guiAdminManagement;

    public GuiAdminManagement(){
        guiAdminManagement = new JFrame();
        guiAdminManagement.setTitle("Admin");
        guiAdminManagement.setBounds(100, 100, 361, 300);
        guiAdminManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiAdminManagement.getContentPane().setLayout(null);
        guiAdminManagement.setVisible(true);

        JLabel labelTittle = new JLabel("CAFETERIA ITEMS");
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTittle.setFont(new Font("Arial", Font.BOLD, 25));
        labelTittle.setBounds(10, 11, 320, 30);
        guiAdminManagement.getContentPane().add(labelTittle);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 320, 2);
        guiAdminManagement.getContentPane().add(separatorTop);

        JButton buttonWaitingList = new JButton("VIEW WAITING LIST");
        buttonWaitingList.setFont(new Font("Arial", Font.BOLD, 13));
        buttonWaitingList.setBounds(30, 65, 285, 25);
        buttonWaitingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                new GuiWaitingList();
            }
        });
        guiAdminManagement.getContentPane().add(buttonWaitingList);

        JButton buttonManageCafeteriaItems = new JButton("MANAGE CAFETERIA ITEMS");
        buttonManageCafeteriaItems.setFont(new Font("Arial", Font.BOLD, 13));
        buttonManageCafeteriaItems.setBounds(30, 101, 285, 25);
        buttonManageCafeteriaItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiManageCafeteriaItems();
            }
        });
        guiAdminManagement.getContentPane().add(buttonManageCafeteriaItems);

        JButton buttonManageCafeteriaMenu = new JButton("MANAGE CAFETERIA MENU");
        buttonManageCafeteriaMenu.setFont(new Font("Arial", Font.BOLD, 13));
        buttonManageCafeteriaMenu.setBounds(30, 137, 285, 25);
        buttonManageCafeteriaMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiManageCafeteriaMenu();
                guiAdminManagement.dispose();
            }
        });
        guiAdminManagement.getContentPane().add(buttonManageCafeteriaMenu);

        JButton buttonRooms = new JButton("CREATE AND DELETE ROOMS");
        buttonRooms.setFont(new Font("Arial", Font.BOLD, 13));
        buttonRooms.setBounds(30, 173, 285, 25);
        guiAdminManagement.getContentPane().add(buttonRooms);

        JButton buttonManageBooks = new JButton("MANAGE BOOKS");
        buttonManageBooks.setFont(new Font("Arial", Font.BOLD, 13));
        buttonManageBooks.setBounds(30, 209, 285, 25);
        buttonManageBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        guiAdminManagement.getContentPane().add(buttonManageBooks);
    }
}
