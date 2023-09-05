import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static boolean triangle(Double a, Double b, Double c) {
        if (a + b > c && a + c > b && b + c > a)
            return  true;
        else
            return false;
    }

    public static void correctData(String a, String b, String c, JLabel textview) {
        try{
            double a1 = Double.parseDouble(a);
            double b1 = Double.parseDouble(b);
            double c1 = Double.parseDouble(c);
            double P1 = (a1 + b1 + c1)/2;
            if (triangle(a1, b1, c1)){
                textview.setText(Perimetr(a1, b1, c1, P1));
            }
            else{
                textview.setText("Треугольник не существует");
            }
        }catch (NumberFormatException ex) {
            //handle exception here
            System.out.println(ex);
            textview.setText("Некорректные данные");
        }
    }


    public static String Perimetr(double a, double b, double c, double P){
        return String.valueOf(  Math.sqrt( P * (P-a) * (P-b) * (P-c) )   );
    }
    public static void main(String[] args) {


        JFrame frame = new JFrame("Калькулятор площади треугольника");
        frame.setSize(400,300);

        JTextField edittxt1 = new JTextField("a");
        edittxt1.setBounds(50,100,50,30);

        JTextField edittxt2 = new JTextField("b");
        edittxt2.setBounds(130,100,50,30);

        JTextField edittxt3 = new JTextField("c");
        edittxt3.setBounds(210,100,50,30);

        JLabel textview = new JLabel("Введите стороны треугольника и полумериметр");
        textview.setBounds(20,40,350,20);
        textview.setFont(new Font("Serif", Font.PLAIN, 17));

        JButton button = new JButton("Рассчитать");
        button.setBounds(130,150,120,20);

        JLabel textview2 = new JLabel("S = ");
        textview2.setBounds(40,200,50,20);
        textview2.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel textview3 = new JLabel("");
        textview3.setBounds(80,200,300,20);
        textview3.setFont(new Font("Serif", Font.PLAIN, 20));


        frame.add(button);
        frame.add(textview);
        frame.add(edittxt1);
        frame.add(edittxt2);
        frame.add(edittxt3);
        frame.add(textview2);
        frame.add(textview3);
        frame.setLayout(null);
        frame.setVisible(true);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String a = edittxt1.getText();
                String b = edittxt2.getText();
                String c = edittxt3.getText();

                correctData(a, b, c, textview3);

            }
        });

    }
}