import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class lab6var2 extends JFrame {
    private JTextPane textPane;
    private SimpleAttributeSet highlightStyle;
    private Pattern pattern;

    public lab6var2() {
        textPane = new JTextPane();

        // Задаем строку текстового поля в коде
        String initialText = "This is a Test. And Another Test. One More Test.";
        textPane.setText(initialText);

        textPane.setFont(new Font("Serif", Font.PLAIN, 12));
        textPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    // Заменяем выделенные слова, к которым подведена каретка
                    int start = textPane.getSelectionStart();
                    int end = textPane.getSelectionEnd();
                    String toReplace = textPane.getText().substring(start, end);
                    String replaced = toReplace.replaceAll("\\b([A-Z]\\w+) ([A-Z]\\w+)\\b", "$1-$2");
                    textPane.replaceSelection(replaced);
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    removeHighlight();
                }
            }
        });

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

    private void removeHighlight() {
        StyledDocument doc = textPane.getStyledDocument();
        doc.setCharacterAttributes(0, doc.getLength(), new SimpleAttributeSet(), true);
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

    public static void main(String[] args) {
        new lab6var2();
    }
}
