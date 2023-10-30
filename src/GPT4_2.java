
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPT4_2 extends JFrame {
    private JTable matrixTableX;
    private JTable matrixTableY;
    private JTextField textField;
    private JButton updateButton;
    private JButton calculateButton;
    private DefaultTableModel tableModelX;
    private DefaultTableModel tableModelY;
    private double[][] matrixX;
    private double[][] matrixY;

    public GPT4_2(int n) {
        setTitle("Матрица");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        matrixX = createMatrix(n);
        matrixY = new double[n][n];

        tableModelX = new DefaultTableModel(n, n);
        matrixTableX = new JTable(tableModelX);
        matrixTableX.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matrixTableX.setColumnSelectionAllowed(false);
        matrixTableX.setRowSelectionAllowed(false);
        matrixTableX.setCellSelectionEnabled(true);
        matrixTableX.setShowGrid(true);

        updateTable(matrixTableX, matrixX);

        JScrollPane scrollPaneX = new JScrollPane(matrixTableX);
        add(scrollPaneX, BorderLayout.WEST);

        tableModelY = new DefaultTableModel(n, n);
        matrixTableY = new JTable(tableModelY);
        matrixTableY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matrixTableY.setColumnSelectionAllowed(false);
        matrixTableY.setRowSelectionAllowed(false);
        matrixTableY.setCellSelectionEnabled(true);
        matrixTableY.setShowGrid(true);

        JScrollPane scrollPaneY = new JScrollPane(matrixTableY);
        add(scrollPaneY, BorderLayout.EAST);

        JPanel panel = new JPanel(new FlowLayout());

        textField = new JTextField(10);
        panel.add(textField);

        updateButton = new JButton("Занести на место");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMatrix();
            }
        });
        panel.add(updateButton);

        calculateButton = new JButton("Вычислить");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMatrix();
            }
        });
        panel.add(calculateButton);

        add(panel, BorderLayout.SOUTH);

        matrixTableX.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = matrixTableX.getSelectedRow();
            int selectedColumn = matrixTableX.getSelectedColumn();
            if (selectedRow >= 0 && selectedColumn >= 0) {
                double value = (double) matrixTableX.getValueAt(selectedRow, selectedColumn);
                textField.setText(String.valueOf(value));
            }
        });

        pack();
        setVisible(true);
    }

    private double[][] createMatrix(int n) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = n - i;
                }
            }
        }
        return matrix;
    }

    private void updateTable(JTable table, double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                table.setValueAt(matrix[i][j], i, j);
            }
        }
    }

    private void updateMatrix() {
        int selectedRow = matrixTableX.getSelectedRow();
        int selectedColumn = matrixTableX.getSelectedColumn();
        if (selectedRow >= 0 && selectedColumn >= 0) {
            String valueStr = textField.getText();
            try {
                double value = Double.parseDouble(valueStr);
                matrixX[selectedRow][selectedColumn] = value;
                tableModelX.setValueAt(value, selectedRow, selectedColumn);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Неверное значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calculateMatrix() {
        int n = matrixX.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixY[i][j] = matrixX[i][ j] + matrixX[j][ j];
            }
        }
        updateTable(matrixTableY, matrixY);
    }

    public static void main(String[] args) {
        int n = 4; // Заданное значение n
        SwingUtilities.invokeLater(() -> new GPT4_2(n));
    }
}