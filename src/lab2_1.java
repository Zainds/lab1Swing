import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab2_1 extends JFrame {
    private JButton button1;
    private JPanel MainPanel;
    private JTextField textField1;
    private JRadioButton RadioButton1;
    private JRadioButton RadioButton2;
    private JRadioButton RadioButton3;
    private JCheckBox CheckBox;
    private JLabel label;

    ButtonGroup mygroup = new ButtonGroup();

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public void Calculate(int choice){
        double result = 1;
        try{

            double x = Double.parseDouble(textField1.getText());
            if(!isNumeric(textField1.getText()))throw new NumberFormatException("Use numeric format");

            switch (choice){
                case 1:
                    result = x * Math.sin(x);
                    RadioButton1.setSelected(true);
                    break;
                case 2:
                    result = Math.sqrt(x)/x;
                    RadioButton2.setSelected(true);
                    break;
                case 3:
                    result = x*x + 2;
                    RadioButton3.setSelected(true);
                    break;
                default:
                    if(x<=0 ){
                        result = x * Math.sin(x);
                        RadioButton1.setSelected(true);
                    }
                    if(  (x > 0 && x < 3) ) {
                        result = Math.sqrt(x)/x;
                        RadioButton2.setSelected(true);
                    }
                    if(x>=3){
                        result = x*x + 2;
                        RadioButton3.setSelected(true);
                    }
                    break;
            }
            if (CheckBox.isSelected()) result*=2;

            label.setText(String.valueOf(result));

        }
        catch (NumberFormatException ex){
            System.out.println(ex);
            label.setText("Некорректные данные");
            mygroup.clearSelection();
        }
    }
    public lab2_1(){
        setContentPane(MainPanel);
        setTitle("DD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        mygroup.add(RadioButton1);
        mygroup.add(RadioButton2);
        mygroup.add(RadioButton3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculate(0);
            }
        });
        CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = 0;
                if(RadioButton1.isSelected())choice = 1;
                if(RadioButton2.isSelected())choice = 2;
                if(RadioButton3.isSelected())choice = 3;
                Calculate(choice);
            }
        });

        RadioButton1.addActionListener(MyListener);
        RadioButton2.addActionListener(MyListener);
        RadioButton3.addActionListener(MyListener);
    }
    ActionListener MyListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = 0;
            JRadioButton b= (JRadioButton) e.getSource();
            if(b == RadioButton1)choice = 1;
            if(b == RadioButton2)choice = 2;
            if(b == RadioButton3)choice = 3;

            Calculate(choice);
        }
    };
    public static void main(String[] args) {
        new lab2_1();
    }
}
