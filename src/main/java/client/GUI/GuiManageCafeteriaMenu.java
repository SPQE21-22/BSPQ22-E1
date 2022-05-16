package client.GUI;

import server.data.domain.*;
import server.data.domain.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiManageCafeteriaMenu {

    private JFrame guiManageCafeteriaMenu;

    public void createCbF(JComboBox<Food> cb, ArrayList<Food> arf) {
        for (Food f : arf) {
            cb.addItem(f);
        }
    }

    public void createCbD(JComboBox<Drink> cb, ArrayList<Drink> ard) {
        for (Drink d : ard) {
            cb.addItem(d);
        }
    }

    public GuiManageCafeteriaMenu(User u, List<Book> ab, String hostname, String port) {

        ArrayList<Food> arfStarter = new ArrayList<Food>();
        ArrayList<Food> arfMain = new ArrayList<Food>();
        ArrayList<Food> arfPastry= new ArrayList<Food>();
        ArrayList<Drink> ard = new ArrayList<Drink>();

        guiManageCafeteriaMenu = new JFrame();
        guiManageCafeteriaMenu.setTitle("ADMIN");
        guiManageCafeteriaMenu.setResizable(false);
        guiManageCafeteriaMenu.setBounds(100, 100, 290, 315);
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

        JComboBox<Food> cbStarter = new JComboBox<Food>();
        cbStarter.setBounds(106, 65, 152, 22);
        createCbF(cbStarter, arfStarter);
        guiManageCafeteriaMenu.getContentPane().add(cbStarter);

        JLabel labelMain = new JLabel("Main course:");
        labelMain.setFont(new Font("Arial", Font.BOLD, 13));
        labelMain.setBounds(10, 98, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelMain);

        JComboBox<Food> cbMain = new JComboBox<Food>();
        cbMain.setBounds(106, 98, 152, 22);
        createCbF(cbStarter, arfMain);
        guiManageCafeteriaMenu.getContentPane().add(cbMain);

        JLabel labelPastry = new JLabel("Pastry");
        labelPastry.setFont(new Font("Arial", Font.BOLD, 13));
        labelPastry.setBounds(10, 131, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelPastry);

        JComboBox<Food> cbPastry = new JComboBox<Food>();
        cbPastry.setBounds(106, 131, 152, 22);
        createCbF(cbStarter, arfPastry);
        guiManageCafeteriaMenu.getContentPane().add(cbPastry);

        JLabel labelDrink = new JLabel("Starter:");
        labelDrink.setFont(new Font("Arial", Font.BOLD, 13));
        labelDrink.setBounds(10, 164, 112, 14);
        guiManageCafeteriaMenu.getContentPane().add(labelDrink);

        JComboBox<Drink> cbDrink = new JComboBox<Drink>();
        cbDrink.setBounds(106, 164, 152, 22);
        createCbD(cbDrink, ard);
        guiManageCafeteriaMenu.getContentPane().add(cbDrink);

        JSeparator separatorBott = new JSeparator();
        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(10, 197, 248, 2);
        guiManageCafeteriaMenu.getContentPane().add(separatorBott);

        JButton buttonUpdate = new JButton("UPDATE");
        buttonUpdate.setFont(new Font("Arial", Font.BOLD, 13));
        buttonUpdate.setBounds(88, 210, 92, 25);
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu u = new Menu();
                u.setStarter((Food) cbStarter.getSelectedItem());
                u.setMain((Food) cbMain.getSelectedItem());
                u.setPastry((Food) cbPastry.getSelectedItem());
                u.setDrink((Drink) cbDrink.getSelectedItem());
            }
        });
        guiManageCafeteriaMenu.getContentPane().add(buttonUpdate);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setBounds(45, 245, 190, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiAdminManagement(u, ab, hostname, port);
                guiManageCafeteriaMenu.dispose();
            }
        });
        guiManageCafeteriaMenu.getContentPane().add(backButton);
    }
}
