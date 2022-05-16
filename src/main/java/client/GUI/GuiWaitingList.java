package client.GUI;

import server.data.domain.Book;
import server.data.domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GuiWaitingList {

    private JFrame guiWaitingList;

    public void viewList(Book b, List list) {
        list.add("prueba");
        list.add("prueba");
        list.add("prueba");
        list.add("prueba");
        list.add("prueba");
        list.add("prueba");
        list.add("prueba");
    }

    public void createBookButtons(ArrayList<Book> lb, List list, JPanel p) {
        for (Book b : lb) {
            Button button = new Button(b.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    list.removeAll();
                    viewList(b, list);
                }
            });
            p.add(button);
            p.updateUI();
        }
    }

    public GuiWaitingList(User u, java.util.List<Book> ab) {
        guiWaitingList = new JFrame();
        guiWaitingList.setBounds(100, 100, 450, 300);
        guiWaitingList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiWaitingList.getContentPane().setLayout(null);
        guiWaitingList.setResizable(false);
        guiWaitingList.setVisible(true);

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

        JPanel panelBooks = new JPanel();
        panelBooks.setBounds(10, 65, 105, 185);
        guiWaitingList.getContentPane().add(panelBooks);

        JPanel panelList = new JPanel();
        panelList.setBounds(125, 65, 299, 185);
        guiWaitingList.getContentPane().add(panelList);
        panelList.setLayout(null);

        List list = new List();
        list.setBounds(10, 10, 279, 165);
        panelList.add(list);

        System.out.println("entro");

        Book b1 = new Book();
        Book b2 = new Book();
        ArrayList<Book> arb = new ArrayList<Book>();
        arb.add(b1);
        arb.add(b2);

        createBookButtons(arb, list, panelBooks);
    }
}
