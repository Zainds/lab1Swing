import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private Font form_font = new Font("Console", Font.PLAIN, 14);

    private JFrame main_form;
    private JTextField input;
    private JButton button;
    private JLabel print;
    private JLabel Label;

    private ButtonGroup rbuttons_group;
    private JRadioButton radio_1;
    private JRadioButton radio_2;
    private JRadioButton radio_3;

    private JCheckBox double_check;
    ButtonGroup mygroup = new ButtonGroup();

    private JRadioButton _initRButton(String text) {
        JRadioButton rbutton = new JRadioButton();
        rbutton.setText(text);
        rbutton.setFont(form_font);

        return rbutton;
    }

    MainForm() {
        main_form = new JFrame();
        main_form.setLocation(10, 20);
        main_form.setMinimumSize(new Dimension(410, 180));
        main_form.setTitle("Лабораторная работа №7");
        main_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout grid = new GridBagLayout();

        main_form.setLayout(grid);

        GridBagConstraints grid_constraints = new GridBagConstraints();
        grid_constraints.fill = GridBagConstraints.HORIZONTAL;
        grid_constraints.insets = new Insets(5, 5, 5, 5);
        grid_constraints.weightx = .5;
        grid_constraints.weighty = .5;

        Label = new JLabel();
        Label.setText("Значение x:");
        Label.setHorizontalAlignment(SwingConstants.RIGHT);
        Label.setFont(form_font);
        grid_constraints.gridx = 0;
        grid_constraints.gridy = 0;
        main_form.add(Label, grid_constraints);

        input = new JTextField(10);
        input.setFont(form_font);
        grid_constraints.gridx = 1;
        grid_constraints.gridy = 0;
        main_form.add(input, grid_constraints);

        button = new JButton();
        button.setText("Вычислить");
        button.setFont(form_font);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculate(0);
            }
        });
        grid_constraints.gridx = 0;
        grid_constraints.gridy = 1;
        main_form.add(button, grid_constraints);

        print = new JLabel();
        print.setFont(form_font);
        grid_constraints.gridx = 0;
        grid_constraints.gridy = 3;
        main_form.add(print, grid_constraints);

        radio_1 = _initRButton("tg(cos(x+1))");
        radio_2 = _initRButton("sin(x^3-1)");
        radio_3 = _initRButton("x/3 - 1.5");
        rbuttons_group = new ButtonGroup();
        rbuttons_group.add(radio_1);
        grid_constraints.gridx = 2;
        grid_constraints.gridy = 0;
        main_form.add(radio_1, grid_constraints);
        rbuttons_group.add(radio_2);
        grid_constraints.gridx = 2;
        grid_constraints.gridy = 1;
        main_form.add(radio_2, grid_constraints);
        rbuttons_group.add(radio_3);
        grid_constraints.gridx = 2;
        grid_constraints.gridy = 2;
        main_form.add(radio_3, grid_constraints);
        radio_1.addActionListener(MyListener);
        radio_2.addActionListener(MyListener);
        radio_3.addActionListener(MyListener);

        double_check = new JCheckBox();
        double_check.setText("Удвоить");
        double_check.setFont(form_font);
        double_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = 0;
                if(radio_1.isSelected())choice = 1;
                if(radio_2.isSelected())choice = 2;
                if(radio_3.isSelected())choice = 3;
                Calculate(choice);
            }
        });
        grid_constraints.gridx = 2;
        grid_constraints.gridy = 3;
        main_form.add(double_check, grid_constraints);


        mygroup.add(radio_1);
        mygroup.add(radio_2);
        mygroup.add(radio_3);
        main_form.show();
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    public void Calculate(int choice){
        double result = 1;
        try{

            double x = Double.parseDouble(input.getText());
            if(!isNumeric(input.getText()))throw new NumberFormatException("Use numeric format");

            switch (choice){
                case 1:
                    result = Math.tan(Math.cos(x+1));
                    radio_1.setSelected(true);
                    break;
                case 2:
                    result = Math.sin(x*x*x-1);
                    radio_2.setSelected(true);
                    break;
                case 3:
                    result = x/3-1.5;
                    radio_3.setSelected(true);
                    break;
                default:
                    if(x<1 ){
                        result = Math.tan(Math.cos(x+1));
                        radio_1.setSelected(true);
                    }
                    if(  (x >= 1 && x <= 3) ) {
                        result = Math.sin(x*x*x-1);
                        radio_2.setSelected(true);
                    }
                    if(x>3){
                        result = x/3-1.5;
                        radio_3.setSelected(true);
                    }
                    break;
            }
            if (double_check.isSelected()) result*=2;

            print.setText("Ответ: " + String.valueOf(result));

        }
        catch (NumberFormatException ex){
            System.out.println(ex);
            print.setText("Некорректные данные");
            mygroup.clearSelection();
        }
    }
    ActionListener MyListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = 0;
            JRadioButton b= (JRadioButton) e.getSource();
            if(b == radio_1)choice = 1;
            if(b == radio_2)choice = 2;
            if(b == radio_3)choice = 3;

            Calculate(choice);
        }
    };





}
