package client.GUI;

import javax.swing.*;
import java.awt.*;

public class GuiCalendar {

    /* @// TODO: 27/04/2022 Fix sizes */
    JFrame guiCalendar;

    public GuiCalendar(){

        guiCalendar = new JFrame();
        guiCalendar.setResizable(false);
        guiCalendar.setTitle("CALENDAR");
        guiCalendar.setBounds(100, 100, 517, 410);
        guiCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiCalendar.getContentPane().setLayout(null);
        guiCalendar.setVisible(true);

        JPanel todayPanel = new JPanel();
        todayPanel.setBounds(0, 0, 501, 30);
        guiCalendar.getContentPane().add(todayPanel);
        todayPanel.setLayout(new BorderLayout(0, 0));

        /* @// TODO: 27/04/2022 todays date  */
        JLabel todayLabel = new JLabel("New label");
        todayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        todayPanel.add(todayLabel);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(0, 30, 501, 2);
        guiCalendar.getContentPane().add(separatorTop);

        JPanel monthMovePanel = new JPanel();
        monthMovePanel.setBounds(0, 32, 501, 30);
        guiCalendar.getContentPane().add(monthMovePanel);
        monthMovePanel.setLayout(new BorderLayout(0, 0));

        JButton leftButton = new JButton("<");
        monthMovePanel.add(leftButton, BorderLayout.WEST);

        JButton rightButton = new JButton(">");
        monthMovePanel.add(rightButton, BorderLayout.EAST);

        JSeparator separatorMid = new JSeparator();
        separatorMid.setForeground(Color.BLACK);
        separatorMid.setBackground(Color.BLACK);
        separatorMid.setBounds(0, 62, 150, 2);
        guiCalendar.getContentPane().add(separatorMid);

        JPanel gotoPanel = new JPanel();
        gotoPanel.setBounds(0, 64, 150, 151);
        guiCalendar.getContentPane().add(gotoPanel);
        gotoPanel.setLayout(null);

        JLabel gotoLabel = new JLabel("GO TO");
        gotoLabel.setBounds(10, 5, 130, 30);
        gotoLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gotoPanel.add(gotoLabel);

        JLabel selectLabel = new JLabel("Choose the month:");
        selectLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectLabel.setBounds(10, 46, 130, 14);
        gotoPanel.add(selectLabel);
        
        /* @// TODO: 27/04/2022 Add elements in JComboBox  */
        JComboBox gotoComboBox = new JComboBox();
        gotoComboBox.setMaximumRowCount(12);
        gotoComboBox.setBounds(10, 71, 130, 22);
        gotoPanel.add(gotoComboBox);

        JButton moveButton = new JButton("MOVE");
        moveButton.setBounds(10, 117, 130, 23);
        gotoPanel.add(moveButton);

        JSeparator separatorBott = new JSeparator();
        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(0, 215, 150, 2);
        guiCalendar.getContentPane().add(separatorBott);

        JPanel bookingPanel = new JPanel();
        bookingPanel.setBounds(0, 217, 150, 154);
        guiCalendar.getContentPane().add(bookingPanel);

        JPanel calendarPanel = new JPanel();
        calendarPanel.setBounds(150, 62, 350, 305);
        guiCalendar.getContentPane().add(calendarPanel);
        GridBagLayout gbl_calendarPanel = new GridBagLayout();
        gbl_calendarPanel.columnWidths = new int[]{50, 50, 50, 50, 50, 50, 50, 0};
        gbl_calendarPanel.rowHeights = new int[]{63, 62, 62, 62, 62, 0};
        gbl_calendarPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_calendarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        calendarPanel.setLayout(gbl_calendarPanel);
        
        /* @// TODO: 27/04/2022 improve  */
        Button b = new Button("1");
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

    }

}
