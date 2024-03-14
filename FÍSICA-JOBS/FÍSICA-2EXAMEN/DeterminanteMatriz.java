import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeterminanteMatriz extends JFrame {

    private JTextField rowsField, colsField, matrixField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public DeterminanteMatriz() {
        setTitle("Calculadora de Determinante n x m");
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 10));

        mainPanel.add(new JLabel("Número de Filas:"));
        rowsField = new JTextField();
        mainPanel.add(rowsField);

        mainPanel.add(new JLabel("Número de Columnas:"));
        colsField = new JTextField();
        mainPanel.add(colsField);

        mainPanel.add(new JLabel("Ingrese la Matriz (separada por comas):"));
        matrixField = new JTextField();
        mainPanel.add(matrixField);

        calculateButton = new JButton("Calcular Determinante");
        mainPanel.add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularDeterminante();
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private void calcularDeterminante() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            String[] elements = matrixField.getText().split(",");
            double[][] matriz = new double[rows][cols];

            if (elements.length != rows * cols) {
                JOptionPane.showMessageDialog(this, "El número de elementos ingresados no coincide con el tamaño de la matriz.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int index = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String element = elements[index++].trim();
                    if (element.startsWith("+0") || element.startsWith("-0")) {
                        JOptionPane.showMessageDialog(this, "El número 0 no puede tener un signo '+' o '-'. Por favor, inténtelo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    matriz[i][j] = Double.parseDouble(element);
                }
            }

            double determinante = calcularDeterminante(matriz);
            resultLabel.setText("La determinante de la matriz es: " + determinante);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calcularDeterminante(double[][] matriz) {
        int n = matriz.length;

        if (n == 1) {
            return matriz[0][0];
        }

        double determinante = 0;
        int signo = 1;

        for (int j = 0; j < n; j++) {
            double[][] submatriz = new double[n - 1][n - 1];

            for (int k = 1; k < n; k++) {
                int l = 0;
                for (int m = 0; m < n; m++) {
                    if (m != j) {
                        submatriz[k - 1][l] = matriz[k][m];
                        l++;
                    }
                }
            }

            determinante += signo * matriz[0][j] * calcularDeterminante(submatriz);
            signo *= -1;
        }

        return determinante;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeterminanteMatriz());
    }
}
