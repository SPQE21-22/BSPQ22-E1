package client.GUI;

import server.data.domain.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAddRoom {
    JFrame addRoom;

    public Boolean valid(int day, String month){
        boolean result = false;

        if (month.matches("January|March|May|July|August|October|December") && day <= 31) {
            result = true;
        } else if (month.matches("April|June|September|November") && day <= 30) {
            result = true;
        } else if (month.equals("February") && day <= 25) {
            result = true;
        }

        return result;
    }

    public GuiAddRoom(){
        addRoom = new JFrame();
        addRoom.setBounds(100, 100, 380, 284);
        addRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addRoom.getContentPane().setLayout(null);
        addRoom.setVisible(true);

        JLabel titleLabel = new JLabel("ROOM MANAGEMENT");
        JSeparator separatorTop = new JSeparator();
        JLabel nameLabel = new JLabel("NAME:");
        JTextField nameText = new JTextField();
        JLabel dateLabel = new JLabel("DATE:");
        JSpinner daySpinner = new JSpinner();
        JComboBox<String> monthCombo = new JComboBox<String>();
        monthCombo.addItem("January");
        monthCombo.addItem("February");
        monthCombo.addItem("March");
        monthCombo.addItem("April");
        monthCombo.addItem("May");
        monthCombo.addItem("June");
        monthCombo.addItem("July");
        monthCombo.addItem("September");
        monthCombo.addItem("October");
        monthCombo.addItem("November");
        monthCombo.addItem("December");
        JLabel hourBegText = new JLabel("STARTING HOUR");
        JSpinner spinner = new JSpinner();
        JLabel shSpinner = new JLabel("FINISHING HOUR");
        JSpinner fhSpinner = new JSpinner();
        JSeparator separatorBott = new JSeparator();
        JButton deleteButton = new JButton("DELETE");
        JButton addButton = new JButton("ADD");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(10, 11, 344, 30);
        addRoom.getContentPane().add(titleLabel);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 344, 2);
        addRoom.getContentPane().add(separatorTop);

        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setBounds(30, 75, 50, 14);
        addRoom.getContentPane().add(nameLabel);

        nameText.setBounds(90, 71, 242, 20);
        addRoom.getContentPane().add(nameText);
        nameText.setColumns(10);

        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateLabel.setBounds(30, 106, 50, 14);
        addRoom.getContentPane().add(dateLabel);

        daySpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
        daySpinner.setBounds(90, 103, 58, 20);
        addRoom.getContentPane().add(daySpinner);

        monthCombo.setBounds(187, 102, 145, 22);
        addRoom.getContentPane().add(monthCombo);

        hourBegText.setFont(new Font("Arial", Font.BOLD, 13));
        hourBegText.setBounds(30, 138, 118, 14);
        addRoom.getContentPane().add(hourBegText);

        spinner.setModel(new SpinnerNumberModel(10, 10, 18, 1));
        spinner.setBounds(58, 163, 58, 20);
        addRoom.getContentPane().add(spinner);

        shSpinner.setHorizontalAlignment(SwingConstants.RIGHT);
        shSpinner.setFont(new Font("Arial", Font.BOLD, 13));
        shSpinner.setBounds(214, 138, 118, 14);
        addRoom.getContentPane().add(shSpinner);

        fhSpinner.setModel(new SpinnerNumberModel(12, 12, 20, 1));
        fhSpinner.setBounds(251, 163, 58, 20);
        addRoom.getContentPane().add(fhSpinner);

        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(10, 194, 344, 2);
        addRoom.getContentPane().add(separatorBott);

        deleteButton.setBounds(243, 207, 89, 23);
        addRoom.getContentPane().add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (valid((Integer)daySpinner.getValue(), monthCombo.getItemAt(monthCombo.getSelectedIndex()))){
                    Room r = new Room();
                    r.setDay((Integer)daySpinner.getValue());
                    r.setMonth(monthCombo.getItemAt(monthCombo.getSelectedIndex()));
                    r.setHourBeg((Integer)spinner.getValue());
                    r.setHourEnd((Integer)fhSpinner.getValue());
                    r.setBooked(false);
                    JOptionPane.showMessageDialog(null, "The room has been created.", "Management", JOptionPane.INFORMATION_MESSAGE);
                    GuiCalendar gc = new GuiCalendar();
                } else {
                    JOptionPane.showMessageDialog(null, "Please make sure the selected day corresponds to the selected month.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addButton.setBounds(30, 207, 89, 23);
        addRoom.getContentPane().add(addButton);
    }

}
