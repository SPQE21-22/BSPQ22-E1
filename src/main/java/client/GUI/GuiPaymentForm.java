package client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GuiPaymentForm {

    private JFrame paymentForm;
    private JTextField textNumber;
    private JTextField textCvv;
    private JTextField textName;

    public boolean isValid(JTextField cn, JTextField c, JTextField chn, JSpinner m, JSpinner y){
        boolean result = false;

        Date tYear = Calendar.getInstance().getTime();
        DateFormat tYearFormat = new SimpleDateFormat("y");
        String yearString = tYearFormat.format(tYear);

        Date tMonth = Calendar.getInstance().getTime();
        DateFormat tMonthFormat = new SimpleDateFormat("M");
        String monthString = tMonthFormat.format((tMonth));

        boolean spinnerGood = false;
        int spinnerYear = (Integer) y.getValue();
        int spinnerMonth = (Integer) m.getValue();
        int yearInt = Integer.parseInt(yearString);
        int monthInt = Integer.parseInt(monthString);

        if (spinnerYear >= yearInt){
            if (spinnerMonth >= monthInt){
                spinnerGood = true;
            }
        }

        if (cn.getText()!= null && cn.getText().length() == 16 && c.getText() != null && c.getText().length() == 3 && chn.getText() != null && spinnerGood){
            result = true;
        }
        return result;
    }

    public GuiPaymentForm(){
        paymentForm = new JFrame();
        paymentForm.setTitle("PAYMENT");
        paymentForm.setResizable(false);
        paymentForm.setBounds(100, 100, 391, 298);
        paymentForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentForm.getContentPane().setLayout(null);
        paymentForm.setVisible(true);

        JLabel labelPaymentForm = new JLabel("PAYMENT FORM");
        labelPaymentForm.setHorizontalAlignment(SwingConstants.CENTER);
        labelPaymentForm.setFont(new Font("Arial", Font.BOLD, 25));
        labelPaymentForm.setBounds(10, 11, 350, 30);
        paymentForm.getContentPane().add(labelPaymentForm);

        JSeparator separatorTop = new JSeparator();
        separatorTop.setForeground(Color.BLACK);
        separatorTop.setBackground(Color.BLACK);
        separatorTop.setBounds(10, 52, 350, 2);
        paymentForm.getContentPane().add(separatorTop);

        JLabel labelNumber = new JLabel("CARD NUMBER:");
        labelNumber.setFont(new Font("Arial", Font.BOLD, 13));
        labelNumber.setBounds(10, 65, 112, 14);
        paymentForm.getContentPane().add(labelNumber);

        textNumber = new JTextField();
        textNumber.setBounds(10, 90, 161, 20);
        textNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(textNumber.getText().length() < 16){
                    if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        });
        paymentForm.getContentPane().add(textNumber);

        JLabel labelCvv = new JLabel("CVV:");
        labelCvv.setFont(new Font("Arial", Font.BOLD, 13));
        labelCvv.setBounds(201, 65, 112, 14);
        paymentForm.getContentPane().add(labelCvv);

        textCvv = new JTextField();
        textCvv.setBounds(201, 90, 159, 20);
        textCvv.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(textCvv.getText().length() < 3){
                    if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)){
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        });
        paymentForm.getContentPane().add(textCvv);

        JLabel labelName = new JLabel("CARD HOLDER NAME:");
        labelName.setFont(new Font("Arial", Font.BOLD, 13));
        labelName.setBounds(10, 121, 161, 14);
        paymentForm.getContentPane().add(labelName);

        textName = new JTextField();
        textName.setBounds(10, 146, 350, 20);
        paymentForm.getContentPane().add(textName);

        JLabel labelExpDate = new JLabel("EXPIRATION DATE:");
        labelExpDate.setFont(new Font("Arial", Font.BOLD, 13));
        labelExpDate.setBounds(10, 177, 130, 14);
        paymentForm.getContentPane().add(labelExpDate);

        JSpinner spinnerYear = new JSpinner();
        spinnerYear.setModel(new SpinnerNumberModel(2022, 2022, null, 1));
        spinnerYear.setBounds(270, 177, 90, 20);
        paymentForm.getContentPane().add(spinnerYear);

        JSpinner spinnerMonth = new JSpinner();
        spinnerMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        spinnerMonth.setBounds(150, 177, 90, 20);
        paymentForm.getContentPane().add(spinnerMonth);

        JSeparator separatorBottom = new JSeparator();
        separatorBottom.setForeground(Color.BLACK);
        separatorBottom.setBackground(Color.BLACK);
        separatorBottom.setBounds(10, 208, 350, 2);
        paymentForm.getContentPane().add(separatorBottom);

        JButton buttonPay = new JButton("PAY");
        buttonPay.setFont(new Font("Arial", Font.BOLD, 13));
        buttonPay.setBounds(201, 221, 92, 25);
        buttonPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValid(textNumber, textCvv, textName, spinnerMonth, spinnerYear)){
                    new GuiCafeteria();
                    paymentForm.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Make sure all the data is filled in correctly.", "Management", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        paymentForm.getContentPane().add(buttonPay);

        JButton buttonCancel = new JButton("CANCEL");
        buttonCancel.setFont(new Font("Arial", Font.BOLD, 13));
        buttonCancel.setBounds(99, 221, 92, 25);
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiCafeteria();
                paymentForm.dispose();
            }
        });
        paymentForm.getContentPane().add(buttonCancel);
    }

}
