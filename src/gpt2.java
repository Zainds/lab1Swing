import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class gpt2 extends JFrame {
    private JLabel sideALabel, sideBLabel, sideCLabel, resultLabel;
    private JTextField sideATextField, sideBTextField, sideCTextField;

    public gpt2() {
        setTitle("Вычисление площади треугольника");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        sideALabel = new JLabel("Сторона a:");
        sideBLabel = new JLabel("Сторона b:");
        sideCLabel = new JLabel("Сторона c:");

        sideATextField = new JTextField(10);
        sideBTextField = new JTextField(10);
        sideCTextField = new JTextField(10);

        resultLabel = new JLabel();

        // Добавляем слушателя фокуса для каждого текстового поля
        sideATextField.addFocusListener(new InputValidationListener());
        sideBTextField.addFocusListener(new InputValidationListener());
        sideCTextField.addFocusListener(new InputValidationListener());

        JPanel panel = new JPanel();
        panel.add(sideALabel);
        panel.add(sideATextField);
        panel.add(sideBLabel);
        panel.add(sideBTextField);
        panel.add(sideCLabel);
        panel.add(sideCTextField);
        panel.add(resultLabel);

        setContentPane(panel);
        setVisible(true);
    }

    private double calculateTriangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2; // полупериметр
        return Math.sqrt(p * (p - a) * (p - b) * (p - c)); // формула Герона
    }

    private class InputValidationListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            // Ничего не делаем при получении фокуса
        }

        @Override
        public void focusLost(FocusEvent e) {
            double a= -999999, b=-99999, c=-999999;

            try {
                if(!(sideATextField.getText().isEmpty())){
                    a = Double.parseDouble(sideATextField.getText());
                }
                if(!(sideBTextField.getText().isEmpty())){
                    b = Double.parseDouble(sideBTextField.getText());
                }
                if(!(sideCTextField.getText().isEmpty())){
                    c = Double.parseDouble(sideCTextField.getText());
                }
                
                double s = calculateTriangleArea(a, b, c);
                resultLabel.setText("Площадь треугольника: " + s);
            } catch (NumberFormatException ex) {
                JTextField source = (JTextField) e.getSource();
                source.requestFocus(); // Устанавливаем фокус обратно на поле с ошибочным вводом
                resultLabel.setText("Ошибка: введите числовые значения для сторон треугольника.");
            }
        }
    }

    public static void main(String[] args) {
        new gpt2();
    }
}