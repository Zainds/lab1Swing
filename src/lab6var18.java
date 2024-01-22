import javax.swing.*;
import javax.swing.text.BadLocationException;
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

        // Добавляем KeyListener для удаления подсветки и знаков комментария при нажатии клавиши F2 и снятия выделения при нажатии клавиши F3
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    try {
                        deleteComments(textArea, highlighter, pattern);
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_F3) {
                    removeHighlight(textArea, highlighter);
                }
            }
        });
    }

    private static void deleteComments(JTextArea textArea, Highlighter highlighter, Pattern pattern) throws BadLocationException {
        int caretPosition = textArea.getCaretPosition();
        // Находим комментарий, который содержит позицию каретки
        for (Highlighter.Highlight highlight : highlighter.getHighlights()) {
            if (caretPosition >= highlight.getStartOffset() && caretPosition <= highlight.getEndOffset()) {
                // Удаляем подсветку
                highlighter.removeHighlight(highlight);
                // Удаляем знаки комментария из текста
                String comment = textArea.getText(highlight.getStartOffset(), highlight.getEndOffset() - highlight.getStartOffset());
                comment = comment.replace("/*", "").replace("*/", "").replace("//", "");
                textArea.replaceRange(comment, highlight.getStartOffset(), highlight.getEndOffset());
                // Проверяем, есть ли внутри комментария другие комментарии
                Matcher nestedMatcher = pattern.matcher(comment);
                if (nestedMatcher.find()) {
                    // Если да, то удаляем их
                    deleteComments(textArea, highlighter, pattern);
                }
                break;
            }
        }
    }

    private static void removeHighlight(JTextArea textArea, Highlighter highlighter) {
        int caretPosition = textArea.getCaretPosition();
        // Находим фрагмент, который содержит позицию каретки
        for (Highlighter.Highlight highlight : highlighter.getHighlights()) {
            if (caretPosition >= highlight.getStartOffset() && caretPosition <= highlight.getEndOffset()) {
                // Удаляем подсветку
                highlighter.removeHighlight(highlight);
                break;
            }
        }
    }
}
