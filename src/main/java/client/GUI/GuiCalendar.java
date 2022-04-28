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

    /* @// TODO: 27/04/2022 Fix sizes */
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

    public void checkBookings(JPanel panel, ArrayList<Room> rArray, String month, String day){
        panel.removeAll();
        panel.updateUI();
        int dayInt = Integer.parseInt(day);
        for (Room r: rArray){
            if (r.getDay() == dayInt && r.getMonth().equals(month)){
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

    public GuiCalendar() {

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
        JButton moveButton = new JButton("MOVE");
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

        gotoComboBox.setMaximumRowCount(12);
        gotoComboBox.setBounds(10, 71, 130, 22);
        gotoPanel.add(gotoComboBox);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookingPanel.removeAll();
                bookingPanel.updateUI();
                String value = gotoComboBox.getItemAt(gotoComboBox.getSelectedIndex());
                months.forEach((k, v) -> {
                    if (v.equals(value)) {
                        monthMoveNum[0] = k;
                        monthMoveLabel.setText(months.get(monthMoveNum[0]));
                    }
                });
            }
        });
        moveButton.setBounds(10, 117, 130, 23);
        gotoPanel.add(moveButton);

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

        /* @// TODO: 27/04/2022 improve  */

        /* DATOS DE PRUEBA */
        Date td = new Date();
        List<Book> tb = new ArrayList<Book>();
        User u = new User("Tyler", "tylerdemier@opendeusto.es", "1234", td, tb);
        Room r0 = new Room(1,"Room1", u, 1, "January", 13, 16, true);
        Room r1 = new Room(2,"Room2", u, 1, "January", 13, 16, true);
        Room r2 = new Room(3,"Room3", u, 1, "March", 13, 16, true);
        Room r3 = new Room(4,"Room4", u, 1, "April", 13, 16, true);
        Room r4 = new Room(5,"Room5", u, 1, "June", 13, 16, true);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(r0);
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);

        Button b = new Button("1");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBookings(bookingPanel, rooms, monthMoveLabel.getText(), b.getLabel());
            }
        });
        GridBagConstraints gbc_b = new GridBagConstraints();
        gbc_b.fill = GridBagConstraints.BOTH;
        gbc_b.insets = new Insets(0, 0, 5, 5);
        gbc_b.gridx = 0;
        gbc_b.gridy = 0;
        calendarPanel.add(b, gbc_b);

        Button b_1 = new Button("2");
        GridBagConstraints gbc_b_1 = new GridBagConstraints();
        gbc_b_1.fill = GridBagConstraints.BOTH;
        gbc_b_1.insets = new Insets(0, 0, 5, 5);
        gbc_b_1.gridx = 1;
        gbc_b_1.gridy = 0;
        calendarPanel.add(b_1, gbc_b_1);

        Button b_2 = new Button("3");
        GridBagConstraints gbc_b_2 = new GridBagConstraints();
        gbc_b_2.fill = GridBagConstraints.BOTH;
        gbc_b_2.insets = new Insets(0, 0, 5, 5);
        gbc_b_2.gridx = 2;
        gbc_b_2.gridy = 0;
        calendarPanel.add(b_2, gbc_b_2);

        Button b_3 = new Button("4");
        GridBagConstraints gbc_b_3 = new GridBagConstraints();
        gbc_b_3.fill = GridBagConstraints.BOTH;
        gbc_b_3.insets = new Insets(0, 0, 5, 5);
        gbc_b_3.gridx = 3;
        gbc_b_3.gridy = 0;
        calendarPanel.add(b_3, gbc_b_3);

        Button b_4 = new Button("5");
        GridBagConstraints gbc_b_4 = new GridBagConstraints();
        gbc_b_4.fill = GridBagConstraints.BOTH;
        gbc_b_4.insets = new Insets(0, 0, 5, 5);
        gbc_b_4.gridx = 4;
        gbc_b_4.gridy = 0;
        calendarPanel.add(b_4, gbc_b_4);

        Button b_5 = new Button("6");
        GridBagConstraints gbc_b_5 = new GridBagConstraints();
        gbc_b_5.fill = GridBagConstraints.BOTH;
        gbc_b_5.insets = new Insets(0, 0, 5, 5);
        gbc_b_5.gridx = 5;
        gbc_b_5.gridy = 0;
        calendarPanel.add(b_5, gbc_b_5);

        Button b_6 = new Button("7");
        GridBagConstraints gbc_b_6 = new GridBagConstraints();
        gbc_b_6.fill = GridBagConstraints.BOTH;
        gbc_b_6.insets = new Insets(0, 0, 5, 0);
        gbc_b_6.gridx = 6;
        gbc_b_6.gridy = 0;
        calendarPanel.add(b_6, gbc_b_6);

        Button b_7 = new Button("8");
        GridBagConstraints gbc_b_7 = new GridBagConstraints();
        gbc_b_7.fill = GridBagConstraints.BOTH;
        gbc_b_7.insets = new Insets(0, 0, 5, 5);
        gbc_b_7.gridx = 0;
        gbc_b_7.gridy = 1;
        calendarPanel.add(b_7, gbc_b_7);

        Button b_8 = new Button("9");
        GridBagConstraints gbc_b_8 = new GridBagConstraints();
        gbc_b_8.fill = GridBagConstraints.BOTH;
        gbc_b_8.insets = new Insets(0, 0, 5, 5);
        gbc_b_8.gridx = 1;
        gbc_b_8.gridy = 1;
        calendarPanel.add(b_8, gbc_b_8);

        Button b_9 = new Button("10");
        GridBagConstraints gbc_b_9 = new GridBagConstraints();
        gbc_b_9.fill = GridBagConstraints.BOTH;
        gbc_b_9.insets = new Insets(0, 0, 5, 5);
        gbc_b_9.gridx = 2;
        gbc_b_9.gridy = 1;
        calendarPanel.add(b_9, gbc_b_9);

        Button b_10 = new Button("11");
        GridBagConstraints gbc_b_10 = new GridBagConstraints();
        gbc_b_10.fill = GridBagConstraints.BOTH;
        gbc_b_10.insets = new Insets(0, 0, 5, 5);
        gbc_b_10.gridx = 3;
        gbc_b_10.gridy = 1;
        calendarPanel.add(b_10, gbc_b_10);

        Button b_11 = new Button("12");
        GridBagConstraints gbc_b_11 = new GridBagConstraints();
        gbc_b_11.fill = GridBagConstraints.BOTH;
        gbc_b_11.insets = new Insets(0, 0, 5, 5);
        gbc_b_11.gridx = 4;
        gbc_b_11.gridy = 1;
        calendarPanel.add(b_11, gbc_b_11);

        Button b_12 = new Button("13");
        GridBagConstraints gbc_b_12 = new GridBagConstraints();
        gbc_b_12.fill = GridBagConstraints.BOTH;
        gbc_b_12.insets = new Insets(0, 0, 5, 5);
        gbc_b_12.gridx = 5;
        gbc_b_12.gridy = 1;
        calendarPanel.add(b_12, gbc_b_12);

        Button b_13 = new Button("14");
        GridBagConstraints gbc_b_13 = new GridBagConstraints();
        gbc_b_13.fill = GridBagConstraints.BOTH;
        gbc_b_13.insets = new Insets(0, 0, 5, 0);
        gbc_b_13.gridx = 6;
        gbc_b_13.gridy = 1;
        calendarPanel.add(b_13, gbc_b_13);

        Button b_14 = new Button("15");
        GridBagConstraints gbc_b_14 = new GridBagConstraints();
        gbc_b_14.fill = GridBagConstraints.BOTH;
        gbc_b_14.insets = new Insets(0, 0, 5, 5);
        gbc_b_14.gridx = 0;
        gbc_b_14.gridy = 2;
        calendarPanel.add(b_14, gbc_b_14);

        Button b_15 = new Button("16");
        GridBagConstraints gbc_b_15 = new GridBagConstraints();
        gbc_b_15.fill = GridBagConstraints.BOTH;
        gbc_b_15.insets = new Insets(0, 0, 5, 5);
        gbc_b_15.gridx = 1;
        gbc_b_15.gridy = 2;
        calendarPanel.add(b_15, gbc_b_15);

        Button b_16 = new Button("17");
        GridBagConstraints gbc_b_16 = new GridBagConstraints();
        gbc_b_16.fill = GridBagConstraints.BOTH;
        gbc_b_16.insets = new Insets(0, 0, 5, 5);
        gbc_b_16.gridx = 2;
        gbc_b_16.gridy = 2;
        calendarPanel.add(b_16, gbc_b_16);

        Button b_17 = new Button("18");
        GridBagConstraints gbc_b_17 = new GridBagConstraints();
        gbc_b_17.fill = GridBagConstraints.BOTH;
        gbc_b_17.insets = new Insets(0, 0, 5, 5);
        gbc_b_17.gridx = 3;
        gbc_b_17.gridy = 2;
        calendarPanel.add(b_17, gbc_b_17);

        Button b_18 = new Button("19");
        GridBagConstraints gbc_b_18 = new GridBagConstraints();
        gbc_b_18.fill = GridBagConstraints.BOTH;
        gbc_b_18.insets = new Insets(0, 0, 5, 5);
        gbc_b_18.gridx = 4;
        gbc_b_18.gridy = 2;
        calendarPanel.add(b_18, gbc_b_18);

        Button b_19 = new Button("20");
        GridBagConstraints gbc_b_19 = new GridBagConstraints();
        gbc_b_19.fill = GridBagConstraints.BOTH;
        gbc_b_19.insets = new Insets(0, 0, 5, 5);
        gbc_b_19.gridx = 5;
        gbc_b_19.gridy = 2;
        calendarPanel.add(b_19, gbc_b_19);

        Button b_20 = new Button("21");
        GridBagConstraints gbc_b_20 = new GridBagConstraints();
        gbc_b_20.fill = GridBagConstraints.BOTH;
        gbc_b_20.insets = new Insets(0, 0, 5, 0);
        gbc_b_20.gridx = 6;
        gbc_b_20.gridy = 2;
        calendarPanel.add(b_20, gbc_b_20);

        Button b_21 = new Button("22");
        GridBagConstraints gbc_b_21 = new GridBagConstraints();
        gbc_b_21.fill = GridBagConstraints.BOTH;
        gbc_b_21.insets = new Insets(0, 0, 5, 5);
        gbc_b_21.gridx = 0;
        gbc_b_21.gridy = 3;
        calendarPanel.add(b_21, gbc_b_21);

        Button b_22 = new Button("23");
        GridBagConstraints gbc_b_22 = new GridBagConstraints();
        gbc_b_22.fill = GridBagConstraints.BOTH;
        gbc_b_22.insets = new Insets(0, 0, 5, 5);
        gbc_b_22.gridx = 1;
        gbc_b_22.gridy = 3;
        calendarPanel.add(b_22, gbc_b_22);

        Button b_23 = new Button("24");
        GridBagConstraints gbc_b_23 = new GridBagConstraints();
        gbc_b_23.fill = GridBagConstraints.BOTH;
        gbc_b_23.insets = new Insets(0, 0, 5, 5);
        gbc_b_23.gridx = 2;
        gbc_b_23.gridy = 3;
        calendarPanel.add(b_23, gbc_b_23);

        Button b_24 = new Button("25");
        GridBagConstraints gbc_b_24 = new GridBagConstraints();
        gbc_b_24.fill = GridBagConstraints.BOTH;
        gbc_b_24.insets = new Insets(0, 0, 5, 5);
        gbc_b_24.gridx = 3;
        gbc_b_24.gridy = 3;
        calendarPanel.add(b_24, gbc_b_24);

        Button b_25 = new Button("26");
        GridBagConstraints gbc_b_25 = new GridBagConstraints();
        gbc_b_25.fill = GridBagConstraints.BOTH;
        gbc_b_25.insets = new Insets(0, 0, 5, 5);
        gbc_b_25.gridx = 4;
        gbc_b_25.gridy = 3;
        calendarPanel.add(b_25, gbc_b_25);

        Button b_26 = new Button("27");
        GridBagConstraints gbc_b_26 = new GridBagConstraints();
        gbc_b_26.fill = GridBagConstraints.BOTH;
        gbc_b_26.insets = new Insets(0, 0, 5, 5);
        gbc_b_26.gridx = 5;
        gbc_b_26.gridy = 3;
        calendarPanel.add(b_26, gbc_b_26);

        Button b_27 = new Button("28");
        GridBagConstraints gbc_b_27 = new GridBagConstraints();
        gbc_b_27.fill = GridBagConstraints.BOTH;
        gbc_b_27.insets = new Insets(0, 0, 5, 0);
        gbc_b_27.gridx = 6;
        gbc_b_27.gridy = 3;
        calendarPanel.add(b_27, gbc_b_27);

        Button b_28 = new Button("29");
        GridBagConstraints gbc_b_28 = new GridBagConstraints();
        gbc_b_28.fill = GridBagConstraints.BOTH;
        gbc_b_28.insets = new Insets(0, 0, 0, 5);
        gbc_b_28.gridx = 0;
        gbc_b_28.gridy = 4;
        calendarPanel.add(b_28, gbc_b_28);

        Button b_29 = new Button("30");
        GridBagConstraints gbc_b_29 = new GridBagConstraints();
        gbc_b_29.fill = GridBagConstraints.BOTH;
        gbc_b_29.insets = new Insets(0, 0, 0, 5);
        gbc_b_29.gridx = 1;
        gbc_b_29.gridy = 4;
        calendarPanel.add(b_29, gbc_b_29);

        Button b_30 = new Button("31");
        GridBagConstraints gbc_b_30 = new GridBagConstraints();
        gbc_b_30.fill = GridBagConstraints.BOTH;
        gbc_b_30.insets = new Insets(0, 0, 0, 5);
        gbc_b_30.gridx = 2;
        gbc_b_30.gridy = 4;
        calendarPanel.add(b_30, gbc_b_30);

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

}
