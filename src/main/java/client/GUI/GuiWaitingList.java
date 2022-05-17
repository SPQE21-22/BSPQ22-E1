package client.GUI;

import server.data.domain.Book;
import server.data.domain.User;

import javax.swing.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GuiWaitingList {

    private JFrame guiWaitingList;
    private JPanel panel;


    public JPanel createBookButtons(java.util.List<Book> lb, JPanel p) {
        p.removeAll();
        for (Book b : lb) {
            if (!b.getAvailable()){
                JButton button = new JButton(b.getName());
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Successfully added to waiting list", "Management", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                p.add(button);
                p.updateUI();
            }
        }
        return p;
    }

    public GuiWaitingList(User u, java.util.List<Book> ab, String hostname, String port) {
        guiWaitingList = new JFrame();
        guiWaitingList.setBounds(100, 100, 450, 350);
        guiWaitingList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiWaitingList.getContentPane().setLayout(null);
        guiWaitingList.setResizable(false);
        guiWaitingList.setVisible(true);
        panel = new JPanel();
        panel.setVisible(true);

        JLabel lblWaitingList = new JLabel("WAITING LIST");
        lblWaitingList.setHorizontalAlignment(SwingConstants.CENTER);
        lblWaitingList.setFont(new Font("Arial", Font.BOLD, 25));
        lblWaitingList.setBounds(10, 11, 414, 30);
        guiWaitingList.getContentPane().add(lblWaitingList);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 414, 2);
        guiWaitingList.getContentPane().add(separatorTop);

        panel.setBounds(0, 60, 434, 200);
        panel.setLayout(new GridLayout(10, 10, 0, 0));
        guiWaitingList.getContentPane().add(createBookButtons(ab, panel));

        JButton buttonBack = new JButton("BACK");
        buttonBack.setFont(new Font("Arial", Font.BOLD, 13));
        buttonBack.setBounds(100, 270, 110, 25);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMain(u, ab, hostname, port);
                guiWaitingList.dispose();
            }
        });
        guiWaitingList.getContentPane().add(buttonBack);
    }
}
