package client.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class GuiCreateBook extends JFrame {
    private static JFrame guiCreateBook;
    private JLabel labelTitle;
    private JSeparator separatorTop;
    private JLabel labelName;
    private JTextField textName;
    private JLabel labelAuthor;
    private JTextField textAuthor;
    private JLabel labelDate;
    private JFormattedTextField textDate;
    private JSeparator separatorBottom;
    private JButton buttonCancel;
    private JButton buttonCreate;
    private Font arialBlack13;
    private Font arialBlack30;

    public GuiCreateBook() {
        guiCreateBook = new JFrame();
        labelTitle = new JLabel("CREATE BOOK");
        separatorTop = new JSeparator();
        labelName = new JLabel("NAME");
        textName = new JTextField();
        labelAuthor = new JLabel("AUTHOR");
        textAuthor = new JTextField();
        labelDate = new JLabel("DATE");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        textDate = new JFormattedTextField(df);
        separatorBottom = new JSeparator();
        buttonCancel = new JButton("CANCEL");
        buttonCreate = new JButton("CREATE");

        arialBlack13 = new Font("Arial", 1, 13);
        arialBlack30 = new Font("Arial", 1, 30);

        guiCreateBook.setResizable(false);
        guiCreateBook.setBounds(100, 100, 330, 340);
        guiCreateBook.setDefaultCloseOperation(3);
        guiCreateBook.getContentPane().setLayout(null);
        guiCreateBook.setVisible(true);

        labelTitle.setFont(arialBlack30);
        labelTitle.setBounds(47, 11, 220, 36);

        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(40, 58, 238, 2);

        labelName.setFont(arialBlack13);
        labelName.setBounds(40, 71, 37, 16);

        textName.setBounds(40, 98, 238, 20);

        labelAuthor.setFont(arialBlack13);
        labelAuthor.setBounds(40, 129, 54, 16);

        textAuthor.setBounds(40, 156, 238, 20);

        labelDate.setFont(arialBlack13);
        labelDate.setBounds(40, 187, 44, 16);

        textDate.setBounds(40, 214, 238, 20);

        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(40, 245, 238, 2);

        buttonCancel.setFont(arialBlack13);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiMain();
                GuiCreateBook.guiCreateBook.dispose();
            }
        });
        buttonCancel.setBounds(40, 258, 92, 25);
        buttonCreate.setFont(arialBlack13);
        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GuiMain();
                GuiCreateBook.guiCreateBook.dispose();
            }
        });
        buttonCreate.setBounds(186, 259, 92, 25);
        guiCreateBook.getContentPane().add(labelTitle);
        guiCreateBook.getContentPane().add(separatorTop);
        guiCreateBook.getContentPane().add(labelName);
        guiCreateBook.getContentPane().add(textName);
        guiCreateBook.getContentPane().add(labelAuthor);
        guiCreateBook.getContentPane().add(textAuthor);
        guiCreateBook.getContentPane().add(labelDate);
        guiCreateBook.getContentPane().add(textDate);
        guiCreateBook.getContentPane().add(separatorBottom);
        guiCreateBook.getContentPane().add(buttonCancel);
        guiCreateBook.getContentPane().add(buttonCreate);
    }
}
