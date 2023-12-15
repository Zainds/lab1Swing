import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class lab6var2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}

class TextEditor extends JFrame {
    private JTextPane textPane;
    private SimpleAttributeSet highlightStyle;
    private SimpleAttributeSet regularStyle;
    private Pattern pattern;

    public TextEditor() {
        textPane = new JTextPane();

        // Задаем строку текстового поля в коде
        String initialText = "This is a Test. And Another еest. One More test.";
        textPane.setText(initialText);

        textPane.setFont(new Font("Serif", Font.PLAIN, 12));
        textPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int caretPos = textPane.getCaretPosition();
                String text = textPane.getText();
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    String[] words = text.substring(start, end).split(" ");
                    if (caretPos >= start && caretPos <= start + words[0].length() ||
                            caretPos >= start + words[0].length() + 1 && caretPos <= end) {
                        if (e.getKeyCode() == KeyEvent.VK_F2) {
                            // Заменяем выделенные слова, к которым подведена каретка
                            String replaced = text.substring(start, end).replaceAll("\\b([A-Z]\\w+) ([A-Z]\\w+)\\b", "$1-$2");

                            textPane.select(start, end);
                            textPane.replaceSelection(replaced);
                            // Снимаем выделение
                            textPane.select(start, end);
                            textPane.setCharacterAttributes(new SimpleAttributeSet(), true);
                            textPane.setCaretPosition(start);

                            break;
                        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                            // Снимаем выделение
                            textPane.select(start, end);
                            textPane.setCharacterAttributes(new SimpleAttributeSet(), true);
                            textPane.setCaretPosition(start);
                            break;
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
        StyleConstants.setForeground(highlightStyle, Color.RED);

        pattern = Pattern.compile("\\b[A-Z]\\w+ [A-Z]\\w+\\b");

        add(new JScrollPane(textPane));
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Выделяем участки текста при запуске
        highlightText();
    }

    private void highlightText() {
        StyledDocument doc = textPane.getStyledDocument();
        String text = textPane.getText();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            doc.setCharacterAttributes(start, end - start, highlightStyle, false);
        }
    }
}
