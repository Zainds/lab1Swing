import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.InputMismatchException;

public class DM {
    public static class UserInterface {
        public void createWindow() {
            JLabel placeholder = new JLabel();

            JFrame frame = new JFrame("Калькулятор натурального логарифма");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // text fields
            JTextField inputX = new JTextField();
            JTextField variable = new JTextField();
            variable.setBounds(150, 50, 100, 25);
            inputX.setBounds(150, 20, 100, 25);
            // text fields tips
            JLabel xLabel = new JLabel("x:");
            JLabel variableLabel = new JLabel("e0 или N:  ");
            xLabel.setBounds(100, 20, 100, 25);
            variableLabel.setBounds(57, 50, 100, 25);

            // radio buttons
            JRadioButton precisionRadio = new JRadioButton("Точность (e0)");
            JRadioButton termsRadio = new JRadioButton("Количество членов ряда (N)");
            precisionRadio.setBounds(30, 100, 150, 20);
            termsRadio.setBounds(180, 100, 250, 20);

            // button
            JButton calculateButton = new JButton("Рассчитать");
            calculateButton.setBounds(130, 195, 110, 20);

            // result labels
            JLabel instructionLabel = new JLabel("Результат вычисления:");
            JLabel resultLabel = new JLabel("");
            instructionLabel.setBounds(50, 150, 200, 20);
            resultLabel.setBounds(200, 150, 200, 20);

            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result = calculate(inputX, variable, precisionRadio, termsRadio);
                    resultLabel.setText(String.valueOf(result));
                }
            });
            precisionRadio.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (precisionRadio.isSelected()) {
                        termsRadio.setSelected(false);
                    }
                }
            });
            termsRadio.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (termsRadio.isSelected()) {
                        precisionRadio.setSelected(false);
                    }
                }
            });


            frame.add(variable);
            frame.add(inputX);
            frame.add(xLabel);
            frame.add(variableLabel);
            frame.add(precisionRadio);
            frame.add(termsRadio);
            frame.add(calculateButton);
            frame.add(instructionLabel);
            frame.add(resultLabel);
            frame.add(placeholder);

            frame.setSize(400, 280);
            frame.setVisible(true);
            frame.setLayout(null);
        }
        private String calculate(JTextField inputX, JTextField variable, JRadioButton precisionRadio, JRadioButton termsRadio) {
            try {
                double x = Double.parseDouble(inputX.getText());
                double result = 0;
                if (precisionRadio.isSelected()) {
                    double e0 = Double.parseDouble(variable.getText());
                    double prevResult;
                    int n = 0;
                    do {
                        prevResult = result;
                        result += Math.pow(x-1, n+1) / (  (n+1)* Math.pow(x, n+1))  ;
                        n++;
                    } while (Math.abs(prevResult - result) > e0);
                } else if (termsRadio.isSelected()) {
                    double N = Double.parseDouble(variable.getText());
                    double prevResult;
                    int n = 0;
                    for (int i = 0; i < N; i++) {
                        prevResult = result;
                        result += Math.pow(x-1, n+1) / (  (n+1)* Math.pow(x, n+1));
                        n++;
                    }
                }
                else throw new InputMismatchException("Выберите метод вычисления");
                return String.valueOf(result);
            } catch (InputMismatchException | NumberFormatException e) {
                if (e.getClass() == InputMismatchException.class){
                    return ((InputMismatchException) e).getMessage();
                }
                return "Неверный формат ввода";
            }
        }
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        UserInterface UI = new UserInterface();
        UI.createWindow();
    }
}