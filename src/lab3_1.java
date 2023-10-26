import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lab3_1 extends JFrame {

    private JPanel mainpanel;

    public lab3_1(){
        setContentPane(mainpanel);
        setTitle("DD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        /*Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculate();
            }
        });*/
    }
    public static void main(String[] args) {
        new lab3_1();
    }
}
