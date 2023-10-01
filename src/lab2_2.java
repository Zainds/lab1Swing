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
    public static double SummN (double endN, double x)
    {
        double summ = 1, n = 0, currnumb, xb = x, factorialb = 1, fact = 1, Z;
        do {

        }while (n<endN);
        return summ;
    }

    //Функция  с установленной точностью
    public static double SummE (double e, double x)
    {
        double result = 1;
        int k = 1;
        double sum = x;
        while(Math.abs(sum) >= e) {
            result += sum;
            sum *= Math.pow(x-1, k+1) / (k + 1)*Math.pow(x, k+1);
            k++;
        }
        return sum;
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
                    answerLabel.setText(String.valueOf(SummE(e0, x)));
                }catch (NumberFormatException ex){
                    answerLabel.setText("Некорректное e0");
                }

            }
            if(radioButton2.isSelected()){
                try{
                    double N = Double.parseDouble(nValue.getText());
                    if(!isNumeric(nValue.getText()))throw new NumberFormatException("Use numeric format");
                    answerLabel.setText(String.valueOf(SummN(N, x)));
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
