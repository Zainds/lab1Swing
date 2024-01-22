import javax.swing.*;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lab6var18 {
    public static void main(String[] args) {
        // Создаем JFrame и JTextArea
        JFrame frame = new JFrame();
        JTextArea textArea = new JTextArea();
        frame.add(textArea);
        frame.setSize(500, 500);
        frame.setVisible(true);

        // Устанавливаем текст в JTextArea
        String text = "Это ваш текст. Здесь есть некоторые комментарии, которые нужно заменить. // это комментарий и " +
                "\n парные /* символы */ sdf /* открывающийся комментарий */ - закрывающий.";
        textArea.setText(text);

        // Инициализируем Highlighter
        Highlighter highlighter = textArea.getHighlighter();

        // Регулярное выражение для поиска комментариев
        Pattern pattern = Pattern.compile("/\\*.*?\\*/|//.*");
        Matcher matcher = pattern.matcher(text);

        // Подсвечиваем каждый найденный комментарий
        DefaultHighlightPainter painter = new DefaultHighlightPainter(Color.RED);
        while (matcher.find()) {
            try {
                highlighter.addHighlight(matcher.start(), matcher.end(), painter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Добавляем KeyListener для удаления подсветки при нажатии клавиши F2
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    highlighter.removeAllHighlights();
                    textArea.setText(textArea.getText().replace("//", "").replace("/*", "").replace("*/", ""));
                }
            }
        });
    }
}
