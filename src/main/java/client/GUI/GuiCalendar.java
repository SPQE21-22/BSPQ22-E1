package client.GUI;

import server.data.domain.Book;
import server.data.domain.Booking;
import server.data.domain.Room;
import server.data.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class GuiCalendar {

    JFrame guiCalendar;
    String monthMove;
    HashMap<Integer, String> months;

    public HashMap<Integer, String> createHashMonths() {
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");

        return months;
    }

    public void checkBookings(JPanel panel, List<Room> rArray, String month, String day) {
        panel.removeAll();
        panel.updateUI();
        int dayInt = Integer.parseInt(day);
        for (Room r : rArray) {
            if (r.getDay() == dayInt && r.getMonth().equals(month)) {
                JButton b = new JButton(r.getName());
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GuiRoom gr = new GuiRoom(r.getUser(), r);
                    }
                });
                panel.add(b);
                panel.updateUI();
            }
        }
    }

    public void createButtons(String month, JPanel calendarPanel, List<Room> r, JPanel bookingPanel, int x, int y, int count) {
        Button b = new Button(Integer.toString(count));
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBookings(bookingPanel, r, month, b.getLabel());
            }
        });
        GridBagConstraints gbc_b = new GridBagConstraints();
        gbc_b.fill = GridBagConstraints.BOTH;
        gbc_b.insets = new Insets(0, 0, 5, 5);
        gbc_b.gridx = x;
        gbc_b.gridy = y;
        b.validate();
        calendarPanel.add(b, gbc_b);
    }

    public void createDays(String month, JPanel calendarPanel, List<Room> r, JPanel bookingPanel) {
        calendarPanel.removeAll();
        int count = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 7; x++) {
                count++;
                if (month.matches("January|March|May|July|August|October|December")) {
                    if (count <= 31) {
                        createButtons(month, calendarPanel, r, bookingPanel, x, y, count);
                        calendarPanel.updateUI();
                    }
                } else if (month.matches("April|June|September|November")) {
                    if (count <= 30) {
                        createButtons(month, calendarPanel, r, bookingPanel, x, y, count);
                        calendarPanel.updateUI();
                    }
                } else if (month.equals("February")) {
                    if (count <= 25) {
                        createButtons(month, calendarPanel, r, bookingPanel, x, y, count);
                        calendarPanel.updateUI();
                    }
                }
            }
        }

        JButton exitButton = new JButton("X");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        GridBagConstraints gbc_exitButton = new GridBagConstraints();
        gbc_exitButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_exitButton.gridx = 6;
        gbc_exitButton.gridy = 4;
        calendarPanel.add(exitButton, gbc_exitButton);
    }

    public GuiCalendar(User u, List<Room> reservations) {

        /* @// TODO: 29/04/2022 : IMPLEMENT DATABASE  */
        /* DATOS DE PRUEBA */
        Date td = new Date();
        List<Book> tb = new ArrayList<Book>();
        User us = new User("Tyler", "tylerdemier@opendeusto.es", "1234", td, tb);
/*        Room r0 = new Room(1, "SPQ meeting", us, 1, "May", 13, 16, false);
        Room r1 = new Room(2, "DB teamwork", us, 1, "May", 13, 16, true);
        Room r2 = new Room(3, "UI tracj review", us, 2, "May", 13, 16, true);
        Room r3 = new Room(4, "Reunion", us, 3, "May", 13, 16, true);
        Room r4 = new Room(5, "Algebra studying", us, 4, "May", 13, 16, true);
        reservations.add(r0);
        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        reservations.add(r4);*/

        months = new HashMap<>();
        months = createHashMonths();

        Date tmonth = Calendar.getInstance().getTime();
        DateFormat tmonthFormat = new SimpleDateFormat("M");
        monthMove = tmonthFormat.format(tmonth);
        final int[] monthMoveNum = {Integer.parseInt(monthMove)};

        Date tday = Calendar.getInstance().getTime();
        DateFormat tdayFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String tdayString = tdayFormat.format(tday);

        guiCalendar = new JFrame();
        guiCalendar.setResizable(false);
        guiCalendar.setTitle("CALENDAR");
        guiCalendar.setBounds(100, 100, 517, 410);
        guiCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiCalendar.getContentPane().setLayout(null);
        guiCalendar.setVisible(true);

        JPanel todayPanel = new JPanel();
        JButton addButton = new JButton("MANAGE");
        JLabel todayLabel = new JLabel(tdayString);
        JSeparator separatorTop = new JSeparator();
        JPanel monthMovePanel = new JPanel();
        JButton leftButton = new JButton("<");
        JLabel monthMoveLabel = new JLabel(months.get(monthMoveNum[0]));
        JButton rightButton = new JButton(">");
        JSeparator separatorMid = new JSeparator();
        JPanel gotoPanel = new JPanel();
        JLabel gotoLabel = new JLabel("GO TO");
        JLabel selectLabel = new JLabel("Choose the month:");
        JComboBox<String> gotoComboBox = new JComboBox<String>();
        gotoComboBox.addItem("January");
        gotoComboBox.addItem("February");
        gotoComboBox.addItem("March");
        gotoComboBox.addItem("April");
        gotoComboBox.addItem("May");
        gotoComboBox.addItem("June");
        gotoComboBox.addItem("July");
        gotoComboBox.addItem("September");
        gotoComboBox.addItem("October");
        gotoComboBox.addItem("November");
        gotoComboBox.addItem("December");
        JSeparator separatorBott = new JSeparator();
        JPanel bookingPanel = new JPanel();
        JPanel calendarPanel = new JPanel();

        todayPanel.setBounds(0, 0, 501, 30);
        guiCalendar.getContentPane().add(todayPanel);
        todayPanel.setLayout(new BorderLayout(0, 0));

        todayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        todayPanel.add(todayLabel);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(0, 30, 501, 2);
        guiCalendar.getContentPane().add(separatorTop);

        monthMovePanel.setBounds(0, 32, 501, 30);
        guiCalendar.getContentPane().add(monthMovePanel);
        monthMovePanel.setLayout(new BorderLayout(0, 0));

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monthMoveNum[0] > 1 && monthMoveNum[0] < 13) {
                    bookingPanel.removeAll();
                    bookingPanel.updateUI();
                    monthMoveNum[0] = monthMoveNum[0] - 1;
                    monthMoveLabel.setText(months.get(monthMoveNum[0]));
                    createDays(monthMoveLabel.getText(), calendarPanel, reservations, bookingPanel);
                }
            }
        });
        monthMovePanel.add(leftButton, BorderLayout.WEST);

        monthMoveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthMovePanel.add(monthMoveLabel, BorderLayout.CENTER);

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monthMoveNum[0] > 0 && monthMoveNum[0] < 12) {
                    bookingPanel.removeAll();
                    bookingPanel.updateUI();
                    monthMoveNum[0] = monthMoveNum[0] + 1;
                    monthMoveLabel.setText(months.get(monthMoveNum[0]));
                    createDays(monthMoveLabel.getText(), calendarPanel, reservations, bookingPanel);
                }
            }
        });
        monthMovePanel.add(rightButton, BorderLayout.EAST);

        separatorMid.setForeground(Color.BLACK);
        separatorMid.setBackground(Color.BLACK);
        separatorMid.setBounds(0, 62, 150, 2);
        guiCalendar.getContentPane().add(separatorMid);

        gotoPanel.setBounds(0, 64, 150, 151);
        guiCalendar.getContentPane().add(gotoPanel);
        gotoPanel.setLayout(null);

        gotoLabel.setBounds(10, 5, 130, 30);
        gotoLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gotoPanel.add(gotoLabel);

        selectLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectLabel.setBounds(10, 46, 130, 14);
        gotoPanel.add(selectLabel);

        gotoComboBox.setSelectedItem(months.get(monthMoveNum[0]));
        gotoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingPanel.removeAll();
                bookingPanel.updateUI();
                String value = gotoComboBox.getItemAt(gotoComboBox.getSelectedIndex());
                months.forEach((k, v) -> {
                    if (v.equals(value)) {
                        monthMoveNum[0] = k;
                        monthMoveLabel.setText(months.get(monthMoveNum[0]));
                        createDays(monthMoveLabel.getText(), calendarPanel, reservations, bookingPanel);
                    }
                });
            }
        });
        gotoComboBox.setMaximumRowCount(12);
        gotoComboBox.setBounds(10, 71, 130, 22);
        gotoPanel.add(gotoComboBox);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiAddRoom gar = new GuiAddRoom(u, reservations);
                guiCalendar.dispose();
            }
        });
        addButton.setBounds(10, 117, 130, 23);
        gotoPanel.add(addButton);

        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(0, 215, 150, 2);
        guiCalendar.getContentPane().add(separatorBott);

        bookingPanel.setBounds(0, 217, 150, 154);
        guiCalendar.getContentPane().add(bookingPanel);

        calendarPanel.setBounds(150, 62, 350, 305);
        guiCalendar.getContentPane().add(calendarPanel);
        GridBagLayout gbl_calendarPanel = new GridBagLayout();
        gbl_calendarPanel.columnWidths = new int[]{50, 50, 50, 50, 50, 50, 50, 0};
        gbl_calendarPanel.rowHeights = new int[]{63, 62, 62, 62, 62, 0};
        gbl_calendarPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_calendarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        calendarPanel.setLayout(gbl_calendarPanel);

        createDays(monthMoveLabel.getText(), calendarPanel, reservations, bookingPanel);
    }
}
