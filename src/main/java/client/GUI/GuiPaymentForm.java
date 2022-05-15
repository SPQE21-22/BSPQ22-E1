package client.GUI;

import javax.swing.*;
import java.awt.*;

public class GuiPaymentForm {

    private JFrame paymentForm;
    private JTextField textNumber;
    private JTextField textCvv;
    private JTextField textName;

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
        paymentForm.getContentPane().add(textNumber);
        textNumber.setColumns(10);

        JLabel labelCvv = new JLabel("CVV:");
        labelCvv.setFont(new Font("Arial", Font.BOLD, 13));
        labelCvv.setBounds(201, 65, 112, 14);
        paymentForm.getContentPane().add(labelCvv);

        textCvv = new JTextField();
        textCvv.setBounds(201, 90, 159, 20);
        paymentForm.getContentPane().add(textCvv);
        textCvv.setColumns(10);

        JLabel labelName = new JLabel("CARD HOLDER NAME:");
        labelName.setFont(new Font("Arial", Font.BOLD, 13));
        labelName.setBounds(10, 121, 161, 14);
        paymentForm.getContentPane().add(labelName);

        textName = new JTextField();
        textName.setBounds(10, 146, 350, 20);
        paymentForm.getContentPane().add(textName);
        textName.setColumns(10);

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

        JButton buttonPagar = new JButton("PAY");
        buttonPagar.setFont(new Font("Arial", Font.BOLD, 13));
        buttonPagar.setBounds(201, 221, 92, 25);
        paymentForm.getContentPane().add(buttonPagar);

        JButton buttonCancel = new JButton("CANCEL");
        buttonCancel.setFont(new Font("Arial", Font.BOLD, 13));
        buttonCancel.setBounds(99, 221, 92, 25);
        paymentForm.getContentPane().add(buttonCancel);
    }

}
