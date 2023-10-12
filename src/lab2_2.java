import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab2_2 extends JFrame {
    private JPanel mainPanel;
    private JTextField inputx;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField e0Value;
    private JTextField nValue;
    private JButton Button;
    private JLabel myhint;
    private JLabel answerLabel;

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
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
                    result += (Math.pow(x, n) / factorial(n));
                    n++;
                } while (Math.abs(prevResult - result) > e0);


            } else if (termsRadio.isSelected()) {
                double N = Double.parseDouble(variable.getText());
                for (int i = 0; i < N; i++) {
                    result += (Math.pow(x, i) / factorial(i));
                }
            }

            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "Неверный формат ввода";
        }
    }

    private int factorial(int n) {
        int result = 1;
        if (n == 0) {
            return 1;
        }
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public void Calculate(){
        try{
            String input = inputx.getText();
            double x = Double.parseDouble(input);
            if(!isNumeric(input))throw new NumberFormatException("Use numeric format");
            if(radioButton1.isSelected()){
                try{
                    double e0 = Double.parseDouble(e0Value.getText());
                    if(!isNumeric(e0Value.getText()))throw new NumberFormatException("Use numeric format");
                    //answerLabel.setText(String.valueOf(SummE(e0, x)));
                }catch (NumberFormatException ex){
                    answerLabel.setText("Некорректное e0");
                }

            }
            if(radioButton2.isSelected()){
                try{
                    double N = Double.parseDouble(nValue.getText());
                    if(!isNumeric(nValue.getText()))throw new NumberFormatException("Use numeric format");
                    //answerLabel.setText(String.valueOf(SummN(N, x)));
                }catch (NumberFormatException ex){
                    answerLabel.setText("Некорректное N");
                }

            }
            if(!radioButton1.isSelected() && !radioButton2.isSelected()){
                answerLabel.setText("Выберите метод");
            }
        }
        catch (NumberFormatException ex){
            System.out.println(ex);
            answerLabel.setText("Некорректные данные");
        }

    }

    public lab2_2(){
        setContentPane(mainPanel);
        setTitle("DD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculate();
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        new lab2_2();
    }
}
