package GUI;

import data.dto.BookDTO;
import GUI.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateBookDialog extends JDialog {
    public CreateBookDialog(MainWindow mainWindow) {
        super(mainWindow, "Add book", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 250));

        JTextField titleField = new JTextField();
        titleField.setColumns(10);
        JTextField authorField = new JTextField();
        authorField.setColumns(10);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        JFormattedTextField publishDateField = new JFormattedTextField(df);
        publishDateField.setColumns(10);
        JButton submitField = new JButton("Submit");
        submitField.addActionListener(e -> {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date3  = null;
            try {
                date3  = format.parse(publishDateField.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            BookDTO book0 = new BookDTO();
            book0.setName(titleField.getText());
            book0.setAuthor(authorField.getText());
            book0.setPublishDate(date3);
            book0.setAvailable(true);
            mainWindow.lista.add(book0);
                    /*
            stravaWindow.getStravaController().createTrainingSession(
                    titleField.getText(),
                    runRB.isSelected() ? "Running" : "Cycling",
                    Float.parseFloat(distanceField.getText()),
                    Integer.parseInt(durationField.getText()),
                    stravaWindow.getUserController().getToken()
                     );
                     */
            this.dispose();
            mainWindow.loadBooks();
        });

        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        JPanel p5 = new JPanel(new FlowLayout());

        p1.add(new JLabel("Title: "));
        p1.add(titleField);
        p2.add(new JLabel("Author: "));
        p2.add(authorField);
        p3.add(new JLabel("Publishing date: "));
        p3.add(publishDateField);
        p5.add(submitField);

        this.setLayout(new GridLayout(4,1));
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p5);
    }
}