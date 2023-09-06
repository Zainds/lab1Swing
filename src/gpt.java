import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gpt extends JFrame {
    private JLabel sideALabel, sideBLabel, sideCLabel, resultLabel;
    private JTextField sideATextField, sideBTextField, sideCTextField;
    private JButton calculateButton;

    public gpt() {
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

        calculateButton = new JButton("Вычислить");
        resultLabel = new JLabel();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double a = Double.parseDouble(sideATextField.getText());
                    double b = Double.parseDouble(sideBTextField.getText());
                    double c = Double.parseDouble(sideCTextField.getText());

                    double s = calculateTriangleArea(a, b, c);
                    resultLabel.setText("Площадь треугольника: " + s);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Ошибка: введите числовые значения для сторон треугольника.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(sideALabel);
        panel.add(sideATextField);
        panel.add(sideBLabel);
        panel.add(sideBTextField);
        panel.add(sideCLabel);
        panel.add(sideCTextField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        setContentPane(panel);
        setVisible(true);
    }

    private double calculateTriangleArea(double a, double b, double c) {
        double p = (a + b + c) / 2; // полупериметр
        return Math.sqrt(p * (p - a) * (p - b) * (p - c)); // формула Герона
    }

    public static void main(String[] args) {
        new gpt();
    }
}