package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Calculadora;
import models.Calculadora.OPERACAO;

public class GUI extends JFrame implements ActionListener {
    public static JFrame frame;
    public static JTextField textField;

    Calculadora calculadora = new Calculadora();

    public boolean isFirstNumber = true;

    public JButton button0;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton button6;
    public JButton button7;
    public JButton button8;
    public JButton button9;
    public JButton buttonEquals;
    public JButton buttonSum;
    public JButton buttonSubtraction;
    public JButton buttonDivision;
    public JButton buttonMultiply;
    public JButton buttonReset;

    public String firstNumerString = "";
    public String secondNumberString = "";
    public String resultString = "";

    public GUI() {
        this.frame = new JFrame("calculator");

        this.textField = new JTextField(16);
        this.textField.setEditable(false);

        JPanel panel = new JPanel();

        this.button0 = new JButton("0");
        this.button1 = new JButton("1");
        this.button2 = new JButton("2");
        this.button3 = new JButton("3");
        this.button4 = new JButton("4");
        this.button5 = new JButton("5");
        this.button6 = new JButton("6");
        this.button7 = new JButton("7");
        this.button8 = new JButton("8");
        this.button9 = new JButton("9");
        this.buttonEquals = new JButton("=");
        this.buttonSum = new JButton("+");
        this.buttonSubtraction = new JButton("-");
        this.buttonDivision = new JButton("/");
        this.buttonMultiply = new JButton("*");
        this.buttonReset = new JButton("C");

        buttonSum.addActionListener(this);
        buttonSubtraction.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonDivision.addActionListener(this);

        button9.addActionListener(this);
        button8.addActionListener(this);
        button7.addActionListener(this);
        button6.addActionListener(this);
        button5.addActionListener(this);
        button4.addActionListener(this);
        button3.addActionListener(this);
        button2.addActionListener(this);
        button1.addActionListener(this);
        button0.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonEquals.addActionListener(this);

        panel.add(textField);
        panel.add(buttonSum);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(buttonSubtraction);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(buttonMultiply);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(buttonDivision);
        panel.add(button0);
        panel.add(buttonReset);
        panel.add(buttonEquals);

        panel.setBackground(Color.BLACK);
        frame.add(panel);

        frame.setSize(200, 220);
        frame.show();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String selected = event.getActionCommand();

        if (selected.equals("+") && isFirstNumber) {
            defineOperation(OPERACAO.ADICAO);
        } else if (selected.equals("-") && isFirstNumber) {
            defineOperation(OPERACAO.SUBTRACAO);
        } else if (selected.equals("*") && isFirstNumber) {
            defineOperation(OPERACAO.MULTIPLICACAO);
        } else if (selected.equals("/") && isFirstNumber) {
            defineOperation(OPERACAO.DIVISAO);
        }

        if (isFirstNumber && selectedIsNumber(selected)) {
            firstNumerString += selected;
            textField.setText(firstNumerString);
            this.calculadora.setOperador1(new Double(firstNumerString));
        } else if (!isFirstNumber && selectedIsNumber(selected)) {
            this.secondNumberString += selected;
            textField.setText(secondNumberString);
            this.calculadora.setOperador2(new Double(secondNumberString));
        }

        if (selected.equals("=")) {
            this.resultString = String.valueOf(calculadora.calcular());
            textField.setText(resultString);

            this.firstNumerString = resultString;
            calculadora.setOperador1(calculadora.calcular());

            this.secondNumberString = "";
            calculadora.setOperador2(0);

            this.isFirstNumber = true;
        } else if (selected.equals("C")) {
            textField.setText("");
            this.firstNumerString = "";
            this.secondNumberString = "";
            this.resultString = "";
        }
    }

    private void defineOperation(OPERACAO operation) {
        this.calculadora.setOperacao(operation);
        this.isFirstNumber = !isFirstNumber;
        textField.setText("");
    }

    private boolean selectedIsNumber(String selected) {
        return !(selected.equals("+") || selected.equals("-") || selected.equals("*") || selected.equals("/") || selected.equals("=") || selected.equals("C"));
    }
}
