import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.Caret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class lab6 extends JPanel{
    JScrollPane textarea_scrollpane;
    JTextPane text_area;

    JPanel status_panel;
    JLabel status_label;

    JLabel desired_word_label;
    JTextField desired_word_textfield;

    JLabel replacement_word_label;
    JTextField replacement_word_textfield;
    JButton replacement_button;
    JLabel tar_amount_label;
    JTextField tar_amount_textfiled;
    String default_string = "Hello, my name is Oleg. ";
    String desired_substring = ",";
    String replacement_substring = ".";
    String tar_amount = "0";
    char replacement_substring2 = 'q';

    Font main_font;
    Font select_font;

    lab6(){
        main_font = new Font("calibri", 1, 12);
        select_font = new Font("impact", Font.BOLD, 12);

        textarea_scrollpane = new JScrollPane();
        textarea_scrollpane.setBounds(15, 60, 555, 470);

        text_area = new JTextPane();
        text_area.setText(default_string);
        text_area.setFont(main_font);
        textarea_scrollpane.getViewport().add(text_area);
        text_area.setEditable(true);
        text_area.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){}

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){

                    case KeyEvent.VK_F2:
                        replaceDisiredSubstring();
                        findAndSelectWords(replacement_substring);
                        break;

                    case KeyEvent.VK_F4:
                        clearSelection();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(textarea_scrollpane);

        status_panel = new JPanel();
        status_panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        status_panel.setBounds(0, 540, 500, 20);
        status_panel.setLayout(new BoxLayout(status_panel, BoxLayout.X_AXIS));
        add(status_panel);
        status_label = new JLabel();
        status_label.setHorizontalAlignment(SwingConstants.LEFT);
        status_panel.add(status_label);
        status_panel.setVisible(true);

        desired_word_label = new JLabel("Искомый знак:");
        desired_word_label.setBounds(15, 10, 100, 20);
        add(desired_word_label);
        desired_word_textfield = new JTextField(desired_substring);
        desired_word_textfield.setBounds(15, 35, 100, 20);
        desired_word_textfield.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                desired_substring = desired_word_textfield.getText();
                clearSelection();
                findAndSelectWords(desired_substring);
            }
        });
        add(desired_word_textfield);

        replacement_word_label = new JLabel("знак для подстановки:");
        replacement_word_label.setFont(new Font(replacement_word_label.getFont().getFontName(), 1, 9));
        replacement_word_label.setBounds(125, 10, 120, 20);
        add(replacement_word_label);
        replacement_word_textfield = new JTextField(replacement_substring);
        replacement_word_textfield.setBounds(125, 35, 120, 20);
        add(replacement_word_textfield);

        tar_amount_label = new JLabel("Число для сравнения:");
        tar_amount_label.setFont(new Font(tar_amount_label.getFont().getFontName(), 1, 9));
        tar_amount_label.setBounds(255, 10, 100, 20);
        add(tar_amount_label);
        tar_amount_textfiled = new JTextField(tar_amount);
        tar_amount_textfiled.setBounds(255, 35, 100, 20);
        tar_amount_textfiled.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                tar_amount = tar_amount_textfiled.getText();
                clearSelection();
                findAndSelectWords(tar_amount);
            }
        });
        add(tar_amount_textfiled);
        replacement_button = new JButton("Заменить искомое слово");
        replacement_button.setBounds(365, 35, 190, 20);
        replacement_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                replaceDisiredSubstring();
                findAndSelectWords(replacement_substring);

            }
        });
        add(replacement_button);


        String[] font_names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JComboBox main_font_combobox = new JComboBox<String>(font_names);
        main_font_combobox.setBounds(365, 5, 100, 20);
        main_font_combobox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                main_font = new Font(main_font_combobox.getSelectedItem().toString(), 1, text_area.getFont().getSize());
                text_area.setFont(main_font);
                status_label.setText("Шрифт " + main_font.getFontName() + " выбран");
                findAndSelectWords(desired_substring);
            }
        });
        add(main_font_combobox);
        JComboBox select_font_combobox = new JComboBox<String>(font_names);
        select_font_combobox.setBounds(470, 5, 100, 20);
        select_font_combobox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                select_font = new Font(select_font_combobox.getSelectedItem().toString(), Font.BOLD, select_font.getSize());
                status_label.setText("Шрифт " + main_font.getFontName() + " выбран");
                findAndSelectWords(desired_substring);
            }
        });
        add(select_font_combobox);

        findAndSelectWords(desired_substring);
    }


    void replaceDisiredSubstring(){
        String text = text_area.getText();



        int pos = 0;
        int t_a_t=0;
        int counter=0;

        String t_a;
        t_a=tar_amount_textfiled.getText();
        t_a_t = Integer.parseInt(t_a);
        String []Count= text.split(" ");
        counter = Count.length;
        while ((pos = text.toUpperCase().indexOf(desired_substring.toUpperCase(), pos)) >= 0){

            for(int i=0;i<text.length();i++) {

                if(text.charAt(i)==',') {

                    replacement_substring2 = text.toUpperCase().charAt(i+2);
                    Character.toString(replacement_substring2);break;
                }

            }

            if ((text.charAt(pos+desired_substring.length()) != ' ' || text.charAt(pos+desired_substring.length()) != ',')){
                if (pos-1 < 0){ pos += desired_substring.length(); continue; }



                text = text.substring(0, pos) + replacement_substring +' '+replacement_substring2+ text.substring(pos+2+desired_substring.length());

            }
            pos += desired_substring.length();

        }
        if(counter>t_a_t) {
            text_area.setText(text);
        }
    }




    void clearSelection(){
        StyledDocument style_document = text_area.getStyledDocument();
        SimpleAttributeSet atribute_set = new SimpleAttributeSet();
        StyleConstants.setFontSize(atribute_set, main_font.getSize());
        StyleConstants.setBold(atribute_set, main_font.isBold());
        StyleConstants.setFontFamily(atribute_set, main_font.getFamily());
        style_document.setCharacterAttributes(0, text_area.getText().length(), atribute_set, true);
    }

    void findAndSelectWords(String str){

        StyledDocument style_document = text_area.getStyledDocument();
        SimpleAttributeSet atribute_set = new SimpleAttributeSet();
        StyleConstants.setFontSize(atribute_set, select_font.getSize());
        StyleConstants.setBold(atribute_set, true);
        StyleConstants.setFontFamily(atribute_set, select_font.getFamily());

        int pos = 0;

        while ((pos=text_area.getText().toUpperCase().indexOf(str.toUpperCase(), pos)) >= 0){

            if ((text_area.getText().charAt(pos+str.length()) == ' ' || text_area.getText().charAt(pos+str.length()) == ',')){
                if (pos-1 < 0 || text_area.getText().charAt(pos-1) != ' '){

                    style_document.setCharacterAttributes(pos, str.length(), atribute_set, true);
                    style_document.setCharacterAttributes(pos+2, str.length(), atribute_set, true);}

            }

            pos += str.length();

        }
    }

    public static void main(String[] args){
        try{
            JFrame main = new JFrame();
            main.setBounds(15, 15, 600, 600);
            main.setTitle("Лабораторная работа № 6");
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setLayout(null);

            lab6 main_panel = new lab6();
            main_panel.setBounds(0, 0, main.getWidth(), main.getHeight());
            main_panel.setLayout(null);
            main.add(main_panel);

            main.setVisible(true);
        }
        catch (Exception ex){
            System.err.println("Something went wrong...");
            System.err.println(ex.getMessage());
            System.exit(ABORT);
        }
    }
}
