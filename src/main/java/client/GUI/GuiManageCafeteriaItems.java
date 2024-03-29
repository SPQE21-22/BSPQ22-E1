package client.GUI;

import server.data.domain.Book;
import server.data.domain.Supply;
import server.data.domain.User;

import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiManageCafeteriaItems {

    private JFrame guiCafeteriaItems;
    private JTextField textNameD;
    private JTextField textNameF;
    Client client;
    WebTarget webTarget;
    public boolean isValid(JTextField n){
        return n.getText() != null;
    }

    public GuiManageCafeteriaItems(User u, List<Book> ab, String hostname, String port){
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        guiCafeteriaItems = new JFrame();
        guiCafeteriaItems.setResizable(false);
        guiCafeteriaItems.setBounds(100, 100, 450, 400);
        guiCafeteriaItems.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiCafeteriaItems.getContentPane().setLayout(null);
        guiCafeteriaItems.setVisible(true);

        JLabel labelTittle = new JLabel("CAFETERIA ITEMS");
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTittle.setFont(new Font("Arial", Font.BOLD, 25));
        labelTittle.setBounds(10, 11, 414, 30);
        guiCafeteriaItems.getContentPane().add(labelTittle);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 414, 2);
        guiCafeteriaItems.getContentPane().add(separatorTop);

        JLabel labelDrinks = new JLabel("DRINKS");
        labelDrinks.setHorizontalAlignment(SwingConstants.CENTER);
        labelDrinks.setFont(new Font("Arial", Font.BOLD, 20));
        labelDrinks.setBounds(10, 65, 195, 30);
        guiCafeteriaItems.getContentPane().add(labelDrinks);

        JSeparator separatorMiddle = new JSeparator();
        separatorMiddle.setOrientation(SwingConstants.VERTICAL);
        separatorMiddle.setForeground(Color.BLACK);
        separatorMiddle.setBackground(Color.BLACK);
        separatorMiddle.setBounds(215, 65, 2, 180);
        guiCafeteriaItems.getContentPane().add(separatorMiddle);

        JLabel labelFood = new JLabel("FOOD");
        labelFood.setHorizontalAlignment(SwingConstants.CENTER);
        labelFood.setFont(new Font("Arial", Font.BOLD, 20));
        labelFood.setBounds(229, 65, 195, 30);
        guiCafeteriaItems.getContentPane().add(labelFood);

        JLabel labelNameD = new JLabel("Name:");
        labelNameD.setFont(new Font("Arial", Font.BOLD, 13));
        labelNameD.setBounds(10, 106, 112, 14);
        guiCafeteriaItems.getContentPane().add(labelNameD);

        JLabel labelNameF = new JLabel("Name:");
        labelNameF.setFont(new Font("Arial", Font.BOLD, 13));
        labelNameF.setBounds(227, 106, 112, 14);
        guiCafeteriaItems.getContentPane().add(labelNameF);

        textNameD = new JTextField();
        textNameD.setBounds(10, 131, 195, 20);
        guiCafeteriaItems.getContentPane().add(textNameD);

        textNameF = new JTextField();
        textNameF.setBounds(227, 131, 197, 20);
        guiCafeteriaItems.getContentPane().add(textNameF);

        JLabel labelPriceD = new JLabel("Price:");
        labelPriceD.setFont(new Font("Arial", Font.BOLD, 13));
        labelPriceD.setBounds(10, 162, 60, 14);
        guiCafeteriaItems.getContentPane().add(labelPriceD);

        JLabel labelPriceF = new JLabel("Price:");
        labelPriceF.setFont(new Font("Arial", Font.BOLD, 13));
        labelPriceF.setBounds(227, 162, 60, 14);
        guiCafeteriaItems.getContentPane().add(labelPriceF);

        SpinnerNumberModel snmD = new SpinnerNumberModel(0.01, 0.01, null, 0.01);
        SpinnerNumberModel snmF = new SpinnerNumberModel(0.01, 0.01, null, 0.01);

        JSpinner spinnerPriceD = new JSpinner();
        spinnerPriceD.setBounds(80, 162, 125, 20);
        spinnerPriceD.setModel(snmD);
        guiCafeteriaItems.getContentPane().add(spinnerPriceD);

        JSpinner spinnerPriceF = new JSpinner();
        spinnerPriceF.setBounds(299, 162, 125, 20);
        spinnerPriceF.setModel(snmF);
        guiCafeteriaItems.getContentPane().add(spinnerPriceF);

        JLabel labelTypeD = new JLabel("Type:");
        labelTypeD.setFont(new Font("Arial", Font.BOLD, 13));
        labelTypeD.setBounds(10, 193, 60, 14);
        guiCafeteriaItems.getContentPane().add(labelTypeD);

        JLabel labelTypeF= new JLabel("Type:");
        labelTypeF.setFont(new Font("Arial", Font.BOLD, 13));
        labelTypeF.setBounds(227, 193, 60, 14);
        guiCafeteriaItems.getContentPane().add(labelTypeF);

        JComboBox<String> cTypeD = new JComboBox<String>();
        cTypeD.setModel(new DefaultComboBoxModel<String>(new String[] {"Soda", "Coffee", "Tea", "Water based", "Juice"}));
        cTypeD.setBounds(80, 193, 125, 22);
        guiCafeteriaItems.getContentPane().add(cTypeD);

        JComboBox<String> cTypeF = new JComboBox<String>();
        cTypeF.setModel(new DefaultComboBoxModel<String>(new String[] {"Starter", "Main course", "Pastry"}));
        cTypeF.setBounds(299, 193, 125, 22);
        guiCafeteriaItems.getContentPane().add(cTypeF);

        JButton buttonAddD = new JButton("ADD");
        buttonAddD.setFont(new Font("Arial", Font.BOLD, 13));
        buttonAddD.setBounds(60, 261, 92, 25);
        buttonAddD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValid(textNameD)){
                    Supply s = new Supply(textNameD.getText(), (Double) spinnerPriceD.getValue(),String.valueOf(cTypeD.getSelectedItem()));
                    WebTarget bookWebTarget = webTarget.path("users/addSupply");
                    Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                    Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
                    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                        JOptionPane.showMessageDialog(null, "The Drink has been added.", "Management", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Make sure all the data is filled in correctly.", "Management", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        guiCafeteriaItems.getContentPane().add(buttonAddD);

        JButton buttonAddF = new JButton("ADD");
        buttonAddF.setFont(new Font("Arial", Font.BOLD, 13));
        buttonAddF.setBounds(282, 261, 92, 25);
        buttonAddF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                if (isValid(textNameF)){
                    Supply s = new Supply(textNameF.getText(), (Double) spinnerPriceF.getValue(),String.valueOf(cTypeF.getSelectedItem()));
                    WebTarget bookWebTarget = webTarget.path("users/addSupply");
                    Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                    Response response = invocationBuilder.post(Entity.entity(s, MediaType.APPLICATION_JSON));
                    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                        JOptionPane.showMessageDialog(null, "The Food has been added.", "Management", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Make sure all the data is filled in correctly.", "Management", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        guiCafeteriaItems.getContentPane().add(buttonAddF);

        JSeparator separatorBottom = new JSeparator();
        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(10, 290, 414, 2);
        guiCafeteriaItems.getContentPane().add(separatorBottom);

        JButton buttonBack = new JButton("BACK");
        buttonBack.setFont(new Font("Arial", Font.BOLD, 13));
        buttonBack.setBounds(110, 300, 192, 25);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                new GuiAdminManagement(u, ab, hostname, port);
                guiCafeteriaItems.dispose();
            }
        });
        guiCafeteriaItems.getContentPane().add(buttonBack);


    }

}
