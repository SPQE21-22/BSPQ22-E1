package client.GUI;

import client.controller.LibraryController;
import client.controller.UserController;
import server.data.domain.Book;
import server.data.dto.BookDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame {
    //UserController userController;
    //StravaController stravaController;

    private LoginDialog loginDialog;
    private RegisterDialog registerDialog;
    private CreateBookDialog addingDialog;

    private final JTabbedPane tabbedPane;
    private JPanel sessionsPanel;

    private BookModel bookModel;
    private JList<BookDTO> booksJList;    // Contains all user training sessions
    List<BookDTO> bookstoadd = new ArrayList<>();

    BookDTO book1 = new BookDTO();
    BookDTO book2 = new BookDTO();
    BookDTO book3 = new BookDTO();
    List<BookDTO> lista = new ArrayList<BookDTO>();

    public MainWindow(String hostname, String port) {
        ///////////////////// 1. Settings /////////////////////
        generateBooks();
        setTitle("Library");
        setSize(650, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        addJMenu();

        //this.userController = userController;
        //this.stravaController = stravaController;

        ///////////////////// 2. Create components /////////////////////
        prepareBookSection();
        // Main panel
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tabbedPane.addTab("Books", sessionsPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.setVisible(false);

        ///////////////////// 3. Include components /////////////////////
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private void addJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem loginMenuItem = new JMenuItem("Log in");
        JMenuItem registerMenuItem = new JMenuItem("Register");
        JMenuItem logoutMenuItem = new JMenuItem("Log out");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenu booksMenu = new JMenu("Books");
        JMenuItem addMenuItem = new JMenuItem("Add");
        optionsMenu.add(loginMenuItem);
        optionsMenu.add(registerMenuItem);
        optionsMenu.add(logoutMenuItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitMenuItem);
        booksMenu.add(addMenuItem);
        menuBar.add(optionsMenu);
        menuBar.add(booksMenu);

        loginMenuItem.addActionListener(e -> {
            this.loginDialog = new LoginDialog(this);
            this.loginDialog.setLocationRelativeTo(this);
            this.loginDialog.setVisible(true);
        });

        registerMenuItem.addActionListener(e -> {
            this.registerDialog = new RegisterDialog(this);
            this.registerDialog.setLocationRelativeTo(this);
            this.registerDialog.setVisible(true);
        });

        logoutMenuItem.addActionListener(e -> {
            //this.userController.logout();
            this.tabbedPane.setVisible(false);
        });

        exitMenuItem.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        addMenuItem.addActionListener(e -> {
            this.addingDialog = new CreateBookDialog(this);
            this.addingDialog.setLocationRelativeTo(this);
            this.addingDialog.setVisible(true);
        });

        setJMenuBar(menuBar);
    }

    private static class BookModel extends DefaultListModel<BookDTO> {
        private static final long serialVersionUID = 1L;

        public List<BookDTO> getbooks() {
            List<BookDTO> books = new ArrayList<>();

            Enumeration<BookDTO> it = this.elements();
            while (it.hasMoreElements()) {
                books.add(it.nextElement());
            }
            return books;
        }
    }

    private void prepareBookSection() {
        bookModel = new BookModel();
        booksJList = new JList<>(bookModel);
        booksJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        JScrollPane sessionsScrollPane = new JScrollPane(booksJList);

        JPanel sessionsButtonPanel = new JPanel();
        sessionsButtonPanel.setLayout(new FlowLayout());

        sessionsPanel = new JPanel();
        sessionsPanel.setLayout(new BorderLayout());
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.add(sessionsButtonPanel);
        sessionsPanel.add(centeringPanel, BorderLayout.NORTH);
        sessionsPanel.add(sessionsScrollPane, BorderLayout.WEST);

        sessionsPanel.add(booksJList, BorderLayout.WEST);
        generateBooks();
        loadBooks();

        // Contains info about the selected training session
        JPanel sessionsInfoPanel = new JPanel();
        sessionsInfoPanel.setLayout(new GridLayout(5,2, 10, 10));

        JLabel sessionTitlePlaceholder = new JLabel("");
        JLabel sessionAuthorlaceholder = new JLabel("");
        JLabel sessionPublishDatePlaceholder = new JLabel("");
        JButton reservar = new JButton("reservar");
        reservar.addActionListener(e -> {
            BookDTO book = booksJList.getSelectedValue();
            book.setAvailable(false);
            loadBooks();
        });
        sessionsInfoPanel.add(new JLabel("Title"));
        sessionsInfoPanel.add(sessionTitlePlaceholder);
        sessionsInfoPanel.add(new JLabel("Author"));
        sessionsInfoPanel.add(sessionAuthorlaceholder);
        sessionsInfoPanel.add(new JLabel("Publish date"));
        sessionsInfoPanel.add(sessionPublishDatePlaceholder);
        sessionsInfoPanel.add(reservar);
        sessionsInfoPanel.setBorder(BorderFactory.createEmptyBorder(45,45,45,45));

        sessionsPanel.add(sessionsInfoPanel, BorderLayout.CENTER);

        booksJList.addListSelectionListener(e -> {
            if (booksJList.getSelectedIndex() != -1) {
                BookDTO book = booksJList.getSelectedValue();
                sessionTitlePlaceholder.setText(book.getName());
                sessionAuthorlaceholder.setText(book.getAuthor());
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = dateFormat.format(book.getPublishDate());
                sessionPublishDatePlaceholder.setText(strDate);
            }
        });

    }

    protected JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public void generateBooks(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date  = null;
        try {
            date  = format.parse("30/07/2010");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book1 = new BookDTO();
        book1.setName("El Imperio Final");
        book1.setAuthor("Brandon Sanderson");
        book1.setPublishDate(date);
        book1.setAvailable(true);

        Date date2  = null;
        try {
            date2  = format.parse("30/07/2010");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book2 = new BookDTO();
        book2.setName("El Pozo de la ascension");
        book2.setAuthor("Sandon Branderson");
        book2.setPublishDate(date2);
        book2.setAvailable(true);

        Date date3  = null;
        try {
            date3  = format.parse("30/07/2010");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        book3 = new BookDTO();
        book3.setName("El Heroe de las heras");
        book3.setAuthor("Brando Sando");
        book3.setPublishDate(date3);
        book3.setAvailable(true);
        lista.add(book1);lista.add(book2);lista.add(book3);
    }

    public void loadBooks() {
        bookModel.clear();
        //for (BookDTO book : this.libraryController.getBooks()) {
            //challengeModel.addElement(challenge);
        //}
        for (BookDTO b:lista) {
            if (b.getAvailable()){
                bookModel.addElement(b);
            }
        }

        if (bookstoadd.size() > 0){
            bookModel.addElement(bookstoadd.get(0));
        }

    }

}
