package client.GUI;

import server.data.domain.Book;
import server.data.domain.Reserv;
import server.data.domain.Room;
import server.data.domain.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GuiMain extends JFrame {
    private static JFrame guiMain;
    private JMenuBar menuBar;
    private JMenu menuUser;
    private JMenuItem menuItemUserInfo;
    private JMenuItem menuItemLogOut;
    private JSeparator separatorMenu;
    private JMenuItem menuItemExit;
    private JPanel panel;
    private JMenu menuAdmin;
    private JMenuItem menuItemAddBook;
    private JMenuItem menuCalendar;

    Client client;
    WebTarget webTarget;
    private Font arialBlack13;
    private Font arial13;

    public GuiMain(User u, List<Book> ab, String hostname, String port) {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));

        guiMain = new JFrame();
        menuBar = new JMenuBar();
        menuUser = new JMenu("USER");
        menuItemUserInfo = new JMenuItem("User Information");
        menuItemLogOut = new JMenuItem("Log Out");
        separatorMenu = new JSeparator();
        menuItemExit = new JMenuItem("Exit");
        menuAdmin = new JMenu("ADMIN");
        menuItemAddBook = new JMenuItem("Add Book");
        menuCalendar = new JMenuItem("Calendar");
        panel = new JPanel();
        panel.setVisible(true);

        arialBlack13 = new Font("Arial", 1, 13);
        arial13 = new Font("Arial", 0, 13);

        guiMain.setTitle("Library");
        guiMain.setResizable(false);
        guiMain.setBounds(100, 100, 450, 300);
        guiMain.setDefaultCloseOperation(3);
        guiMain.getContentPane().setLayout(null);
        guiMain.setVisible(true);

        menuBar.setBounds(0, 0, 434, 22);

        menuUser.setFont(arialBlack13);
        menuBar.add(menuUser);

        menuItemUserInfo.setFont(arial13);
        menuItemUserInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiUser(u, ab, hostname, port);
                guiMain.dispose();
            }
        });
        menuUser.add(menuItemUserInfo);

        menuItemLogOut.setFont(arial13);
        menuItemLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiLogin(hostname, port);
                guiMain.dispose();
            }
        });
        menuUser.add(menuItemLogOut);

        separatorMenu.setForeground(Color.BLACK);
        separatorMenu.setBackground(Color.BLACK);
        menuUser.add(separatorMenu);

        menuItemExit.setFont(arial13);
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiMain.dispose();
            }
        });
        menuUser.add(menuItemExit);

        menuAdmin.setFont(arialBlack13);
        menuBar.add(menuAdmin);

        menuItemAddBook.setFont(arial13);
        menuItemAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiCreateBook(u, ab, hostname, port);
                guiMain.dispose();
            }
        });
        menuAdmin.add(menuItemAddBook);

        menuCalendar.setFont(arial13);
        menuCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client = ClientBuilder.newClient();
                webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
                WebTarget bookWebTarget = webTarget.path("users/rooms");
                Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();
                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    Reserv reserv = response.readEntity(Reserv.class);
                    new GuiCalendar(u, reserv.getReservs(), hostname, port);
                }


            }
        });
        menuAdmin.add(menuCalendar);


        panel.setBounds(0, 22, 434, 238);
        panel.setLayout(new GridLayout(10, 10, 0, 0));

        guiMain.getContentPane().add(menuBar);
        guiMain.getContentPane().add(loadBooks(panel, ab, u));
    }

    private JPanel loadBooks(JPanel p2, List<Book> ab2, User us){
        p2.removeAll();

        for (int i = 0; i < ab2.size(); i++){
            if(ab2.get(i).getAvailable()){
                JButton button = new JButton(ab2.get(i).getName());
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < ab2.size(); i++) {
                            if(ab2.get(i).getName().equals(button.getText())){
                                ab2.get(i).setAvailable(false);
                                us.getBooks().add(ab2.get(i));
                                WebTarget bookWebTarget = webTarget.path("users/updateBook");
                                Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                                Response response = invocationBuilder.put(Entity.entity(ab2.get(i), MediaType.APPLICATION_JSON));
                                if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                                    System.out.println("Error while updating");
                                }
                            }
                        }
                    }
                });
                p2.add(button);
            }
        }
        return p2;
    }
}
