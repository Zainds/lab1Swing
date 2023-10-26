import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import net.miginfocom.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*
 * Created by JFormDesigner on Sun Oct 15 14:54:18 NOVT 2023
 */



/**
 * @author Admin
 */

public class tst extends JFrame {
    public static List<Double> X = new ArrayList<Double>();
    public static List<Double> Y = new ArrayList<Double>();

    public static int N = 10;


    public tst() {
        initComponents();
    }

    private void exitBtn(ActionEvent e) {
        System.exit(0);
    }

    private void newBtn(ActionEvent e) {
       X.clear();
       list1.removeAll();

        for (int i = 0; i < N; i++){
            if(i==0){
                X.add( 0.5  );
            }
            if(i==N-1){
                X.add(  1/(1 + Math.pow(N+1, 2) )  );
            }
            if(i!=0 && i!=N-1){
                X.add(  1/(1 + Math.pow(i+1, 2) )  );
            }
        }
        list1.setListData(X.toArray());

        /*for (int i = 0; i < N; i++){
            Y.add(1.0);

        }*/
    }

    private void saveBtn(ActionEvent e) { //Сохраняется только массив Y
        FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setFile("*.txt");
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename == null)
            System.out.println("You cancelled the choice");
        else {
            System.out.println("You chose " + filename);
            try (FileWriter writer = new FileWriter("Y.txt", false)) {
                int i = 0;
                for (double y : Y) {
                    writer.write(String.valueOf(y));
                    if(i != Y.size()-1) writer.append("\n");
                    i++;
                }

                writer.flush();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    private void loadBtn(ActionEvent e) { //Загружается только массив Х
        FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("");
        fd.setFile("*.txt");
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename == null)
            System.out.println("You cancelled the choice");
        else{
            System.out.println("You chose " + filename);
            File file = new File(filename);
            X.clear();
            list1.removeAll();
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                boolean separate = false;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    X.add(Double.valueOf(line));
                }
                list1.setListData(X.toArray());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void calculateBtn(ActionEvent e) {
        int j = 0;
        for (int i = X.size()-1; i>= 0; i--){
            Y.set(j, X.get(i));
            j++;
        }
        comboBox1.setModel(new DefaultComboBoxModel<Double>(Y.toArray(new Double[0])));
    }

    private void button1(ActionEvent e) {
        for (double x: X) {
            System.out.println(x);
        }
        System.out.println("---Разделение---");
        for (double y: Y) {
            System.out.println(y);
        }
    }
    private void clearArrays(){
        X.clear();
        Y.clear();
    }

    private void list1ValueChanged(ListSelectionEvent e) {
        if(list1.getSelectedIndex() == -1) return; //Если ничего не выбрано (при загрузке
        System.out.println(list1.getSelectedIndex());
        textField1.setText(X.get(list1.getSelectedIndex()).toString());
    }

    private void replaceX(ActionEvent e) {
        int indexToReplace = list1.getSelectedIndex();
        X.set(indexToReplace, Double.valueOf(textField1.getText()));
        list1.setListData(X.toArray());
        list1.setSelectedIndex(indexToReplace);
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {
        if(comboBox1.getSelectedIndex() == -1) return; //Если ничего не выбрано (при загрузке
        int indexToReplace = comboBox1.getSelectedIndex();
        System.out.println(indexToReplace);
        textField2.setText(Y.get(indexToReplace).toString());
    }

    private void replaceY(ActionEvent e) {
        int indexToReplace = comboBox1.getSelectedIndex();
        Y.set(indexToReplace, Double.valueOf(textField2.getText()));
        comboBox1.setModel(new DefaultComboBoxModel<Double>(Y.toArray(new Double[0])));
        comboBox1.setSelectedIndex(indexToReplace);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Michail
        menuBar2 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        comboBox1 = new JComboBox();
        textField1 = new JTextField();
        textField2 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();
        menuBar1 = new JMenuBar();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //======== menuBar2 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u0424\u0430\u0439\u043b");

                //---- menuItem1 ----
                menuItem1.setText("\u041d\u043e\u0432\u044b\u0439");
                menuItem1.addActionListener(e -> newBtn(e));
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c");
                menuItem2.addActionListener(e -> loadBtn(e));
                menu1.add(menuItem2);

                //---- menuItem5 ----
                menuItem5.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
                menuItem5.addActionListener(e -> saveBtn(e));
                menu1.add(menuItem5);

                //---- menuItem4 ----
                menuItem4.setText("\u0412\u044b\u0445\u043e\u0434");
                menuItem4.addActionListener(e -> exitBtn(e));
                menu1.add(menuItem4);
            }
            menuBar2.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u0420\u0430\u0431\u043e\u0442\u0430");

                //---- menuItem3 ----
                menuItem3.setText("\u0412\u044b\u0447\u0438\u0441\u043b\u0438\u0442\u044c");
                menuItem3.addActionListener(e -> calculateBtn(e));
                menu2.add(menuItem3);
            }
            menuBar2.add(menu2);
        }
        setJMenuBar(menuBar2);

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.addListSelectionListener(e -> list1ValueChanged(e));
            scrollPane1.setViewportView(list1);
        }
        contentPane.add(scrollPane1, "cell 3 3 1 6");

        //---- comboBox1 ----
        comboBox1.addItemListener(e -> comboBox1ItemStateChanged(e));
        contentPane.add(comboBox1, "cell 11 3");
        contentPane.add(textField1, "cell 3 12");
        contentPane.add(textField2, "cell 11 12");

        //---- button2 ----
        button2.setText("\u0417\u0430\u043c\u0435\u043d\u0430 \u0425");
        button2.addActionListener(e -> replaceX(e));
        contentPane.add(button2, "cell 3 13");

        //---- button3 ----
        button3.setText("\u0417\u0430\u043c\u0435\u043d\u0430 Y");
        button3.addActionListener(e -> replaceY(e));
        contentPane.add(button3, "cell 11 13");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(350, 400);
        setVisible(true);
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Michail
    private JMenuBar menuBar2;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem5;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JScrollPane scrollPane1;
    private JList list1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button2;
    private JButton button3;
    private JMenuBar menuBar1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
    com.formdev.flatlaf.themes.FlatMacDarkLaf.install();

        new tst();

        for (int i = 0; i < N; i++){
            X.add(1.0);
        }

        for (int i = 0; i < N; i++){
            Y.add(1.0);
        }

    }

}
