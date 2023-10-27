
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPT4 extends JFrame {
private JTable matrixTable;
private JTextField textField;
private JButton updateButton;
private DefaultTableModel tableModel;
private double[][] matrix;

public GPT4(int n) {
        setTitle("Матрица");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        matrix = createMatrix(n);

        tableModel = new DefaultTableModel(n, n);
        matrixTable = new JTable(tableModel);
        matrixTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matrixTable.setColumnSelectionAllowed(false);
        matrixTable.setRowSelectionAllowed(false);
        matrixTable.setCellSelectionEnabled(true);
        matrixTable.setShowGrid(true);

        updateTable();

        JScrollPane scrollPane = new JScrollPane(matrixTable);
        add(scrollPane, BorderLayout.CENTER);

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

        add(panel, BorderLayout.SOUTH);

        matrixTable.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = matrixTable.getSelectedRow();
        int selectedColumn = matrixTable.getSelectedColumn();
        if (selectedRow >= 0 && selectedColumn >= 0) {
        double value = (double) matrixTable.getValueAt(selectedRow, selectedColumn);
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

private void updateTable() {
        for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
        matrixTable.setValueAt(matrix[i][j], i, j);
        }
        }
        }

private void updateMatrix() {
        int selectedRow = matrixTable.getSelectedRow();
        int selectedColumn = matrixTable.getSelectedColumn();
        if (selectedRow >= 0 && selectedColumn >= 0) {
        String valueStr = textField.getText();
        try {
        double value = Double.parseDouble(valueStr);
        matrix[selectedRow][selectedColumn] = value;
        tableModel.setValueAt(value, selectedRow, selectedColumn);
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Неверное значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        }
        }

public static void main(String[] args) {
        int n = 5; // Заданное значение n
        SwingUtilities.invokeLater(() -> new GPT4(n));
        }
        }