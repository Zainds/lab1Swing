import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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

        for (double x : X) {
            System.out.println(x);
        }

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
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                boolean separate = false;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    X.add(Double.valueOf(line));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void calculateBtn(ActionEvent e) {
        for (double y: Y) {
            /*for(int i = X.size()-1; i != 0; i --){
                y = X.get(i);
            }*/
            for (double x: X) {
                y = x;
            }
        }
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
        button1 = new JButton();
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
            "[fill]",
            // rows
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

        //---- button1 ----
        button1.setText("text");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1, "cell 4 2");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
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
    private JButton button1;
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
