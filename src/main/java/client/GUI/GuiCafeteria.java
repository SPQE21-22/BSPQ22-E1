package client.GUI;

import server.data.domain.*;
import server.data.domain.Menu;
import java.util.Random;
import javax.swing.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiCafeteria {

    private JFrame guiCafeteria;
    Client client;
    WebTarget webTarget;
    public GuiCafeteria(User u, List<Book> ab, String hostname, String port) {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        guiCafeteria = new JFrame();
        guiCafeteria.setResizable(false);
        guiCafeteria.setBounds(100, 100, 620, 400);
        guiCafeteria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiCafeteria.getContentPane().setLayout(null);
        guiCafeteria.setVisible(true);

        JLabel labelTittle = new JLabel("CAFETERIA");
        labelTittle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTittle.setFont(new Font("Arial", Font.BOLD, 25));
        labelTittle.setBounds(10, 11, 564, 30);
        guiCafeteria.getContentPane().add(labelTittle);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 564, 2);
        guiCafeteria.getContentPane().add(separatorTop);

        JPanel panelLeft = new JPanel();
        panelLeft.setBounds(10, 65, 200, 230);
        guiCafeteria.getContentPane().add(panelLeft);
        panelLeft.setLayout(new BorderLayout(0, 0));

        JLabel labelDrinks = new JLabel("DRINKS");
        labelDrinks.setHorizontalAlignment(SwingConstants.CENTER);
        labelDrinks.setFont(new Font("Arial", Font.BOLD, 20));
        panelLeft.add(labelDrinks, BorderLayout.NORTH);

        JPanel panelLeftInside = new JPanel();
        panelLeft.add(panelLeftInside, BorderLayout.CENTER);
        GridBagLayout gbl_panelLeftInside = new GridBagLayout();
        gbl_panelLeftInside.columnWidths = new int[]{66, 66, 66, 0};
        gbl_panelLeftInside.rowHeights = new int[]{41, 41, 41, 41, 41, 0};
        gbl_panelLeftInside.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelLeftInside.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelLeftInside.setLayout(gbl_panelLeftInside);

        Menu menu = new Menu();
        WebTarget bookWebTarget = webTarget.path("users/supplies");
        Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            menu = response.readEntity(Menu.class);
        }
        ArrayList<Supply> drinks = menu.getDrink();

        Menu daily = new Menu();
        bookWebTarget = webTarget.path("users/menu");
        invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            daily = response.readEntity(Menu.class);
            System.out.println(daily);
        }

        int yL = 0;
        int xL = 0;
        for (Supply d : drinks) {
            JLabel labelDName = new JLabel(d.getName());
            GridBagConstraints gbc_labelDName = new GridBagConstraints();
            gbc_labelDName.fill = GridBagConstraints.BOTH;
            gbc_labelDName.insets = new Insets(0, 0, 5, 5);
            gbc_labelDName.gridx = xL;
            gbc_labelDName.gridy = yL;
            panelLeftInside.add(labelDName, gbc_labelDName);

            String price = String.valueOf(d.getPrice());
            JLabel labelDPrice = new JLabel(price);
            GridBagConstraints gbc_labelDPrice = new GridBagConstraints();
            gbc_labelDPrice.fill = GridBagConstraints.BOTH;
            gbc_labelDPrice.insets = new Insets(0, 0, 5, 5);
            gbc_labelDPrice.gridx = xL + 1;
            gbc_labelDPrice.gridy = yL;
            panelLeftInside.add(labelDPrice, gbc_labelDPrice);

            JSpinner spinnerD = new JSpinner();
            GridBagConstraints gbc_spinnerD = new GridBagConstraints();
            gbc_spinnerD.fill = GridBagConstraints.BOTH;
            gbc_spinnerD.insets = new Insets(0, 0, 5, 0);
            gbc_spinnerD.gridx = xL + 2;
            gbc_spinnerD.gridy = yL;
            spinnerD.setModel(new SpinnerNumberModel(0, 0, null, 1));
            panelLeftInside.add(spinnerD, gbc_spinnerD);

            yL++;
        }

        JPanel panelRight = new JPanel();
        panelRight.setBounds(430, 65, 144, 230);
        guiCafeteria.getContentPane().add(panelRight);
        panelRight.setLayout(null);

        Random random = new Random();
        ArrayList<Supply> dailymenu = new ArrayList<>();
        if (daily.getStarter().size() > 0){
            dailymenu.add(daily.getStarter().get(0));
            dailymenu.add(daily.getMain().get(0));
            dailymenu.add(daily.getPastry().get(0));
            dailymenu.add(daily.getDrink().get(0));
        } else {
            dailymenu.add(menu.getStarter().get(random.nextInt(menu.getStarter().size())));
            dailymenu.add(menu.getMain().get(random.nextInt(menu.getMain().size())));
            dailymenu.add(menu.getPastry().get(random.nextInt(menu.getPastry().size())));
            dailymenu.add(menu.getDrink().get(random.nextInt(menu.getDrink().size())));
        }

        JLabel labelMenu = new JLabel("MENU");
        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
        labelMenu.setFont(new Font("Arial", Font.BOLD, 20));
        labelMenu.setBounds(0, 0, 144, 30);
        panelRight.add(labelMenu);

        JLabel labelStarter = new JLabel(dailymenu.get(0).getName());
        labelStarter.setFont(new Font("Arial", Font.BOLD, 13));
        labelStarter.setBounds(15, 41, 112, 14);
        panelRight.add(labelStarter);

        JLabel labelMain = new JLabel(dailymenu.get(1).getName());
        labelMain.setFont(new Font("Arial", Font.BOLD, 13));
        labelMain.setBounds(15, 66, 112, 14);
        panelRight.add(labelMain);

        JLabel labelPastry = new JLabel(dailymenu.get(2).getName());
        labelPastry.setFont(new Font("Arial", Font.BOLD, 13));
        labelPastry.setBounds(15, 91, 112, 14);
        panelRight.add(labelPastry);

        JLabel labelDrink = new JLabel(dailymenu.get(3).getName());
        labelDrink.setFont(new Font("Arial", Font.BOLD, 13));
        labelDrink.setBounds(15, 116, 112, 14);
        panelRight.add(labelDrink);

        JLabel labelPrice = new JLabel("PRICE = 15 €");
        labelPrice.setFont(new Font("Arial", Font.BOLD, 13));
        labelPrice.setBounds(15, 141, 112, 14);
        panelRight.add(labelPrice);

        JSpinner spinnerMenu = new JSpinner();
        spinnerMenu.setBounds(10, 166, 112, 20);
        spinnerMenu.setModel(new SpinnerNumberModel(0, 0, null, 1));
        panelRight.add(spinnerMenu);

        JPanel panelCentre = new JPanel();
        panelCentre.setBounds(220, 65, 200, 230);
        guiCafeteria.getContentPane().add(panelCentre);
        panelCentre.setLayout(new BorderLayout(0, 0));

        JLabel labelFood = new JLabel("FOOD");
        labelFood.setHorizontalAlignment(SwingConstants.CENTER);
        labelFood.setFont(new Font("Arial", Font.BOLD, 20));
        panelCentre.add(labelFood, BorderLayout.NORTH);

        JPanel panelCentreInside = new JPanel();
        panelCentre.add(panelCentreInside, BorderLayout.CENTER);
        GridBagLayout gbl_panelCentreInside = new GridBagLayout();
        gbl_panelCentreInside.columnWidths = new int[]{66, 66, 66, 0};
        gbl_panelCentreInside.rowHeights = new int[]{41, 41, 41, 41, 41, 0};
        gbl_panelCentreInside.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelCentreInside.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panelCentreInside.setLayout(gbl_panelCentreInside);

        int yF = 0;
        int xF = 0;
        for (Supply f : dailymenu) {
            JLabel labelFName = new JLabel(f.getName());
            GridBagConstraints gbc_labelFName = new GridBagConstraints();
            gbc_labelFName.fill = GridBagConstraints.BOTH;
            gbc_labelFName.insets = new Insets(0, 0, 5, 5);
            gbc_labelFName.gridx = xF;
            gbc_labelFName.gridy = yF;
            panelCentreInside.add(labelFName, gbc_labelFName);

            String price = String.valueOf(f.getPrice());
            JLabel labelFPrice = new JLabel(price);
            GridBagConstraints gbc_labelFPrice = new GridBagConstraints();
            gbc_labelFPrice.fill = GridBagConstraints.BOTH;
            gbc_labelFPrice.insets = new Insets(0, 0, 5, 5);
            gbc_labelFPrice.gridx = xF + 1;
            gbc_labelFPrice.gridy = yF;
            panelCentreInside.add(labelFPrice, gbc_labelFPrice);

            JSpinner spinnerF = new JSpinner();
            GridBagConstraints gbc_spinnerF = new GridBagConstraints();
            gbc_spinnerF.fill = GridBagConstraints.BOTH;
            gbc_spinnerF.insets = new Insets(0, 0, 5, 0);
            gbc_spinnerF.gridx = xF + 2;
            gbc_spinnerF.gridy = yF;
            spinnerF.setModel(new SpinnerNumberModel(0, 0, null, 1));
            panelCentreInside.add(spinnerF, gbc_spinnerF);

            yF++;
        }

        JSeparator separatorBott = new JSeparator();
        separatorBott.setForeground(Color.BLACK);
        separatorBott.setBackground(Color.BLACK);
        separatorBott.setBounds(10, 306, 564, 2);
        guiCafeteria.getContentPane().add(separatorBott);

        JButton buttonPay = new JButton("PAYMENT");
        buttonPay.setFont(new Font("Arial", Font.BOLD, 13));
        buttonPay.setBounds(364, 319, 110, 25);
        buttonPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiPaymentForm(u, ab, hostname, port);
                guiCafeteria.dispose();
            }
        });
        guiCafeteria.getContentPane().add(buttonPay);

        JButton buttonBack = new JButton("BACK");
        buttonBack.setFont(new Font("Arial", Font.BOLD, 13));
        buttonBack.setBounds(240, 319, 110, 25);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiMain(u, ab, hostname, port);
                guiCafeteria.dispose();
            }
        });
        guiCafeteria.getContentPane().add(buttonBack);
    }


}
