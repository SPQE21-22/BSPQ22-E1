package client.GUI;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.data.domain.*;
import server.sql.DB;

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
    private JMenuItem menuItemWaitingList;
    private JMenuItem menuCalendar;
    private JMenuItem menuItemCafeteria;
    private JMenuItem menuItemReturn;
    private JSeparator separatorMenu;
    private JMenuItem menuItemExit;
    private JPanel panel;
    private JMenu menuAdmin;
    private JMenuItem menuItemAddBook;
    private JMenuItem menuItemfineuser;
    private JMenuItem menuAdministrator;
    private Font arialBlack13;
    private Font arial13;
    private static final Logger logger = LogManager.getLogger(GuiMain.class);
    Client client;
    WebTarget webTarget;

    public GuiMain(User u, List<Book> ab, String hostname, String port) {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));

        guiMain = new JFrame();
        menuBar = new JMenuBar();
        menuUser = new JMenu("USER");
        menuItemUserInfo = new JMenuItem("User Information");
        menuItemWaitingList = new JMenuItem("Waiting List");
        menuCalendar = new JMenuItem("Calendar");
        menuItemCafeteria = new JMenuItem("Cafeteria");
        menuItemReturn = new JMenuItem("Make a return");
        menuItemLogOut = new JMenuItem("Log Out");
        separatorMenu = new JSeparator();
        menuItemExit = new JMenuItem("Exit");
        menuAdmin = new JMenu("ADMIN");
        menuItemAddBook = new JMenuItem("Add Book");
        menuItemfineuser = new JMenuItem("Fine a user");
        menuAdministrator = new JMenuItem("Administration menu");
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

        menuItemWaitingList.setFont(arial13);
        menuItemWaitingList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiWaitingList(u, ab, hostname, port);
            }
        });
        menuUser.add(menuItemWaitingList);

        menuCalendar.setFont(arial13);
        menuCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebTarget bookWebTarget = webTarget.path("users/rooms");
                Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();
                if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                    Reserv reserv = response.readEntity(Reserv.class);
                    new GuiCalendar(u, reserv.getReservs(), hostname, port);
                }


            }
        });
        menuUser.add(menuCalendar);

        menuItemCafeteria.setFont(arial13);
        menuItemCafeteria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiCafeteria(u, ab, hostname, port);
            }
        });
        menuUser.add(menuItemCafeteria);

        menuItemReturn.setFont(arial13);
        menuItemReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ret = JOptionPane.showInputDialog(null, "What book do you want to return?", "Management", JOptionPane.INFORMATION_MESSAGE);
                if (ret != null) {
                    for (Book b : ab) {
                        if (b.getName().equals(ret)){
                            b.setAvailable(true);
                            u.getBooks().remove(b);
                            guiMain.getContentPane().add(loadBooks(panel, ab, u));
                            WebTarget bookWebTarget = webTarget.path("users/updateBook");
                            Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                            Response response = invocationBuilder.put(Entity.entity(b, MediaType.APPLICATION_JSON));
                            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                                logger.error("Error while updating");
                            }
                        }
                    }
                }
            }
        });
        menuUser.add(menuItemReturn);

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

        menuItemfineuser.setFont(arial13);
        menuItemfineuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String finedus = JOptionPane.showInputDialog(null, "User to be fined: ", "Management", JOptionPane.INFORMATION_MESSAGE);
                if (finedus != null) {
                    Fine fine = new Fine(u, 30);
                    WebTarget bookWebTarget = webTarget.path("users/fine");
                    Invocation.Builder invocationBuilder = bookWebTarget.request(MediaType.APPLICATION_JSON);
                    Response response = invocationBuilder.post(Entity.entity(fine, MediaType.APPLICATION_JSON));
                    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                        JOptionPane.showMessageDialog(null, "User fined correctly ", "Management", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        menuAdmin.add(menuItemfineuser);

        menuAdministrator.setFont(arial13);
        menuAdministrator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiAdminManagement(u, ab, hostname, port);
                guiMain.dispose();
            }
        });
        menuAdmin.add(menuAdministrator);

        panel.setBounds(0, 22, 434, 238);
        panel.setLayout(new GridLayout(10, 10, 0, 0));

        guiMain.getContentPane().add(menuBar);
        guiMain.getContentPane().add(loadBooks(panel, ab, u));
    }

    private JPanel loadBooks(JPanel p2, List<Book> ab2, User us){
        p2.removeAll();

        for (Book b : ab2){
            if(b.getAvailable()){
                JButton button = new JButton(b.getName());
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
                                    logger.error("Error while updating");
                                }
                            }
                        }
                    }
                });
                p2.add(button);
            }
            p2.updateUI();
        }
        return p2;
    }
}
