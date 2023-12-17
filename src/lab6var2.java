import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class lab6var2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}

class TextEditor extends JFrame {
    private JTextPane textPane;
    private SimpleAttributeSet highlightStyle;
    private SimpleAttributeSet regularStyle;

    public TextEditor() {
        textPane = new JTextPane();

        String initialText = "Я люблю Ходить Пешком в новый Торговый Центр ";
        String initialText2 = "This is a Test. And Another Test. One More test";
        textPane.setText(initialText);

        textPane.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
        textPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int caretPos = textPane.getCaretPosition();
                String text = textPane.getText();
                String[] words = text.split(" ");

                for (int i = 0; i < words.length - 1; i++) {
                    if (Character.isUpperCase(words[i].charAt(0)) && Character.isUpperCase(words[i + 1].charAt(0))) {
                        int start = text.indexOf(words[i] + " " + words[i + 1]);
                        int end = start + words[i].length() + words[i + 1].length() + 1;

                        if (caretPos >= start && caretPos <= end) {
                            if (e.getKeyCode() == KeyEvent.VK_F2) {
                                String replaced = words[i] + "-" + words[i + 1];

                                textPane.select(start, end);
                                textPane.replaceSelection(replaced);
                                textPane.select(start, end);
                                textPane.setCharacterAttributes(new SimpleAttributeSet(), true);
                                textPane.setCaretPosition(start);

                                break;
                            } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                                textPane.select(start, end);
                                textPane.setCharacterAttributes(new SimpleAttributeSet(), true);
                                textPane.setCaretPosition(start);
                                break;
                            }
                        }
                    }
                }
            }
        });

        regularStyle = new SimpleAttributeSet();
        StyleConstants.setFontSize(regularStyle, 12);
        StyleConstants.setBold(regularStyle, false);
        StyleConstants.setForeground(regularStyle, Color.black);

        highlightStyle = new SimpleAttributeSet();
        StyleConstants.setFontSize(highlightStyle, 14);
        StyleConstants.setBold(highlightStyle, true);
        StyleConstants.setForeground(highlightStyle, Color.black);
        StyleConstants.setFontFamily(highlightStyle, "Comic Sans");



        add(new JScrollPane(textPane));
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        highlightText();
    }

    private void highlightText() {
        StyledDocument doc = textPane.getStyledDocument();
        String text = textPane.getText();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length - 1; i++) {
            if (Character.isUpperCase(words[i].charAt(0)) && Character.isUpperCase(words[i + 1].charAt(0))) {
                int start = text.indexOf(words[i] + " " + words[i + 1]);
                int end = start + words[i].length() + words[i + 1].length() + 1;
                doc.setCharacterAttributes(start, end - start, highlightStyle, false);
            }
        }
    }
}
