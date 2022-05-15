package client.GUI;

import javax.swing.*;
import java.awt.*;

public class GuiManageCafeteriaMenu {

    private JFrame guiManageCafeteriaMenu;

    public GuiManageCafeteriaMenu(){
        guiManageCafeteriaMenu = new JFrame();
        guiManageCafeteriaMenu.setTitle("ADMIN");
        guiManageCafeteriaMenu.setResizable(false);
        guiManageCafeteriaMenu.setBounds(100, 100, 290, 285);
        guiManageCafeteriaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiManageCafeteriaMenu.setVisible(true);
        guiManageCafeteriaMenu.getContentPane().setLayout(null);

        JLabel labelTittle = new JLabel("CAFETERIA MENU");
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTittle.setFont(new Font("Arial", Font.BOLD, 25));
        labelTittle.setBounds(10, 11, 248, 30);
        guiManageCafeteriaMenu.getContentPane().add(labelTittle);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 248, 2);
        guiManageCafeteriaMenu.getContentPane().add(separatorTop);

        JLabel labelStarter = new JLabel("Starter:");
        labelStarter.setFont(new Font("Arial", Font.BOLD, 13));
        labelStarter.setBounds(10, 65, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelStarter);

        JComboBox cbStarter = new JComboBox();
        cbStarter.setBounds(106, 65, 152, 22);
        guiManageCafeteriaMenu.getContentPane().add(cbStarter);

        JLabel labelMain = new JLabel("Main course:");
        labelMain.setFont(new Font("Arial", Font.BOLD, 13));
        labelMain.setBounds(10, 98, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelMain);

        JComboBox cbMain = new JComboBox();
        cbMain.setBounds(106, 98, 152, 22);
        guiManageCafeteriaMenu.getContentPane().add(cbMain);

        JLabel labelPastry = new JLabel("Pastry");
        labelPastry.setFont(new Font("Arial", Font.BOLD, 13));
        labelPastry.setBounds(10, 131, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelPastry);

        JComboBox cbPastry = new JComboBox();
        cbPastry.setBounds(106, 131, 152, 22);
        guiManageCafeteriaMenu.getContentPane().add(cbPastry);

        JLabel labelDrink = new JLabel("Starter:");
        labelDrink.setFont(new Font("Arial", Font.BOLD, 13));
        labelDrink.setBounds(10, 164, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelDrink);

        JComboBox cbDrink = new JComboBox();
        cbDrink.setBounds(106, 164, 152, 22);
        guiManageCafeteriaMenu.getContentPane().add(cbDrink);

        JSeparator separatorBott = new JSeparator();
        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(10, 197, 248, 2);
        guiManageCafeteriaMenu.getContentPane().add(separatorBott);

        JButton buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setFont(new Font("Arial", Font.BOLD, 13));
        buttonUpdate.setBounds(88, 210, 92, 25);
        guiManageCafeteriaMenu.getContentPane().add(buttonUpdate);
    }
}
