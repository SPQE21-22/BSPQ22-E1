package client.GUI;

import server.data.domain.Room;
import server.data.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRoom {

    public JFrame room;

    public GuiRoom(User u, Room r) {
        room = new JFrame();
        room.setBounds(100, 100, 450, 323);
        room.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        room.getContentPane().setLayout(null);
        room.setVisible(true);

        JLabel titleLabel = new JLabel("ROOM INFORMATION");
        JSeparator separatorTop = new JSeparator();
        JLabel userLabel = new JLabel("User who booked:");
        JLabel nameLabel = new JLabel(r.getUser().getName());
        JLabel emailLabel = new JLabel(r.getUser().getEmail());
        JLabel bhLabel = new JLabel("Starting time:");
        JLabel bhtLabel = new JLabel(Integer.toString(r.getHourBeg()));
        JLabel bfLabel = new JLabel("Finishing time:");
        JLabel bftLabel = new JLabel(Integer.toString(r.getHourEnd()));
        JSeparator separatorBott = new JSeparator();
        JButton backButton = new JButton("BACK");

        titleLabel.setBounds(0, 13, 434, 36);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        room.getContentPane().add(titleLabel);

        separatorTop.setBounds(44, 47, 350, 2);
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        room.getContentPane().add(separatorTop);

        userLabel.setFont(new Font("Arial", Font.BOLD, 13));
        userLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userLabel.setBounds(44, 60, 130, 14);
        room.getContentPane().add(userLabel);

        nameLabel.setBounds(184, 60, 210, 14);
        room.getContentPane().add(nameLabel);

        emailLabel.setBounds(184, 85, 210, 14);
        room.getContentPane().add(emailLabel);

        bhLabel.setFont(new Font("Arial", Font.BOLD, 13));
        bhLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bhLabel.setBounds(44, 135, 130, 14);
        room.getContentPane().add(bhLabel);

        bhtLabel.setBounds(184, 135, 210, 14);
        room.getContentPane().add(bhtLabel);

        bfLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bfLabel.setFont(new Font("Arial", Font.BOLD, 13));
        bfLabel.setBounds(44, 160, 130, 14);
        room.getContentPane().add(bfLabel);

        bftLabel.setBounds(184, 160, 210, 14);
        room.getContentPane().add(bftLabel);

        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(44, 205, 350, 2);
        room.getContentPane().add(separatorBott);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.dispose();
            }
        });
        backButton.setBounds(305, 225, 89, 23);
        room.getContentPane().add(backButton);
    }
}
