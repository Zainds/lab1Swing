import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.WebSocket;


public class Main2 {
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

        JLabel textview = new JLabel("Введите стороны треугольника");
        textview.setBounds(50,40,350,20);
        textview.setFont(new Font("Serif", Font.PLAIN, 17));

        JButton button = new JButton("Выход");
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

        //make edittext 1 input listener
        KeyListener myListener = new KeyListener() {
            public void getStrings(){
                String a = edittxt1.getText();
                String b = edittxt2.getText();
                String c = edittxt3.getText();

                correctData(a, b, c, textview3);
            }
            @Override
            public void keyTyped(KeyEvent e) {
                getStrings();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                getStrings();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                getStrings();
            }
        };
        edittxt1.addKeyListener(new KeyListener() {
            private void getS(){
                String a = edittxt1.getText();
                try {
                    double a1 = Double.parseDouble(a);
                    edittxt2.setEnabled(true);
                    edittxt3.setEnabled(true);
                }catch (NumberFormatException ex){
                    edittxt2.setEnabled(false);
                    edittxt3.setEnabled(false);
                }

                String b = edittxt2.getText();
                String c = edittxt3.getText();

                correctData(a, b, c, textview3);
            }
            @Override
            public void keyTyped(KeyEvent e) {
                getS();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                getS();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                getS();
            }
        });
        edittxt2.addKeyListener(new KeyListener() {
            private void getS(){
                String b = edittxt2.getText();
                try {
                    double b1 = Double.parseDouble(b);
                    edittxt1.setEnabled(true);
                    edittxt3.setEnabled(true);
                }catch (NumberFormatException ex){
                    edittxt1.setEnabled(false);
                    edittxt3.setEnabled(false);
                }
                String a = edittxt1.getText();
                String c = edittxt3.getText();

                correctData(a, b, c, textview3);
            }
            @Override
            public void keyTyped(KeyEvent e) {
                getS();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                getS();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                getS();
            }
        });
        edittxt3.addKeyListener(new KeyListener() {
            private void getS(){
                String c = edittxt3.getText();
                try {
                    double c1 = Double.parseDouble(c);
                    edittxt1.setEnabled(true);
                    edittxt2.setEnabled(true);
                }catch (NumberFormatException ex){
                    edittxt1.setEnabled(false);
                    edittxt2.setEnabled(false);
                }
                String a = edittxt1.getText();
                String b = edittxt2.getText();


                correctData(a, b, c, textview3);
            }
            @Override
            public void keyTyped(KeyEvent e) {
                getS();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                getS();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                getS();
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

    }
}