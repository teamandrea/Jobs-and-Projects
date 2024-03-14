import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PropiedadesMatriz extends JFrame {

    private JTextField rowsField, colsField;
    private JTextArea matrixArea, resultArea, diagonalPrincipalArea, diagonalSecundariaArea;

    public PropiedadesMatriz() {
        setTitle("Propiedades de Matriz");
        setBounds(400, 200, 800, 400);
        setLayout(new BorderLayout());
        setVisible(true);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Filas:"));
        rowsField = new JTextField();
        inputPanel.add(rowsField);
        inputPanel.add(new JLabel("Columnas:"));
        colsField = new JTextField();
        inputPanel.add(colsField);
        JButton fillButton = new JButton("Llenar Matriz");
        fillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                llenarMatriz();
            }
        });
        inputPanel.add(fillButton);
        add(inputPanel, BorderLayout.NORTH);

        matrixArea = new JTextArea();
        matrixArea.setEditable(false);
        add(new JScrollPane(matrixArea), BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        diagonalPrincipalArea = new JTextArea();
        diagonalPrincipalArea.setEditable(false);
        add(new JScrollPane(diagonalPrincipalArea), BorderLayout.EAST);

        diagonalSecundariaArea = new JTextArea();
        diagonalSecundariaArea.setEditable(false);
        add(new JScrollPane(diagonalSecundariaArea), BorderLayout.WEST);
    }

    private void llenarMatriz() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());

            int[][] matriz = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String inputValue = JOptionPane.showInputDialog("Ingrese valor para la posición (" + (i + 1) + "," + (j + 1) + "):");
                    int value = Integer.parseInt(inputValue);

                    if (value == 0 && (inputValue.startsWith("-") || inputValue.startsWith("+"))) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un número diferente de -0 o +0.");
                        return;
                    }

                    matriz[i][j] = value;
                }
            }

            mostrarMatriz(matriz);
            mostrarDiagonales(matriz);
            determinarTipoMatriz(matriz);
            mostrarTriangularInferior(matriz);
            mostrarTriangularSuperior(matriz);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para filas y columnas.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void mostrarMatriz(int[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(matriz[i][j]).append("\t");
            }
            sb.append("\n");
        }
        matrixArea.setText(sb.toString());
    }

    private void determinarTipoMatriz(int[][] matriz) {
        int rows = matriz.length;
        int cols = matriz[0].length;

        StringBuilder resultado = new StringBuilder();

        // Verificar si es una matriz nula
        boolean esMatrizNula = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matriz[i][j] != 0) {
                    esMatrizNula = false;
                    break;
                }
            }
            if (!esMatrizNula) {
                break;
            }
        }

        if (esMatrizNula) {
            resultado.append("Matriz Nula\n");
        }

        // Verificar si es una matriz fila
        boolean esMatrizFila = rows == 1 && cols > 1;
        if (esMatrizFila) {
            resultado.append("Matriz Fila\n");
        }

        // Verificar si es una matriz columna
        boolean esMatrizColumna = rows > 1 && cols == 1;
        if (esMatrizColumna) {
            resultado.append("Matriz Columna\n");
        }

        // Verificar si es una matriz cuadrada
        boolean esMatrizCuadrada = rows == cols;
        if (esMatrizCuadrada) {
            resultado.append("Matriz Cuadrada\n");
        }

        // Verificar si es una matriz rectangular
        boolean esMatrizRectangular = rows != cols;
        if (esMatrizRectangular) {
            resultado.append("Matriz Rectangular\n");
        }

        // Verificar si es una matriz diagonal
        boolean esMatrizDiagonal = esMatrizCuadrada && verificaMatrizDiagonal(matriz);
        if (esMatrizDiagonal) {
            resultado.append("Matriz Diagonal\n");
        }

        // Verificar si es una matriz identidad (matriz unidad)
        boolean esMatrizIdentidad = esMatrizDiagonal && verificaMatrizIdentidad(matriz);
        if (esMatrizIdentidad) {
            resultado.append("Matriz Identidad\n");
        }

        resultArea.setText("Tipo de matriz:\n" + resultado.toString());
    }

    private void mostrarDiagonales(int[][] matriz) {
        StringBuilder diagonalPrincipal = new StringBuilder();
        StringBuilder diagonalSecundaria = new StringBuilder();

        int sumaDiagonalPrincipal = 0;
        int sumaDiagonalSecundaria = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == j) {
                    diagonalPrincipal.append(matriz[i][j]).append("\n");
                    sumaDiagonalPrincipal += matriz[i][j];
                }
                if (i + j == matriz.length - 1) {
                    diagonalSecundaria.append(matriz[i][j]).append("\n");
                    sumaDiagonalSecundaria += matriz[i][j];
                }
            }
        }

        diagonalPrincipal.append("Suma de la Diagonal Principal: ").append(sumaDiagonalPrincipal);
        diagonalPrincipalArea.setText(diagonalPrincipal.toString());

        diagonalSecundaria.append("Suma de la Diagonal Secundaria: ").append(sumaDiagonalSecundaria);
        diagonalSecundariaArea.setText(diagonalSecundaria.toString());
    }

    private boolean verificaMatrizDiagonal(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i != j && matriz[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificaMatrizIdentidad(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if ((i == j && matriz[i][j] != 1) || (i != j && matriz[i][j] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void mostrarTriangularInferior(int[][] matriz) {
        int rows = matriz.length;
        int cols = matriz[0].length;
        boolean esTriangularInferior = true;

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < i; j++) {
                if (matriz[i][j] != 0) {
                    esTriangularInferior = false;
                    break;
                }
            }
            if (!esTriangularInferior) {
                break;
            }
        }

        if (esTriangularInferior) {
            resultArea.append("Triangular Inferior: Sí\n");
        } else {
            resultArea.append("Triangular Inferior: No\n");
        }
    }

    private void mostrarTriangularSuperior(int[][] matriz) {
        int rows = matriz.length;
        int cols = matriz[0].length;
        boolean esTriangularSuperior = true;

        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < cols; j++) {
                if (matriz[i][j] != 0) {
                    esTriangularSuperior = false;
                    break;
                }
            }
            if (!esTriangularSuperior) {
                break;
            }
        }

        if (esTriangularSuperior) {
            resultArea.append("Triangular Superior: Sí\n");
        } else {
            resultArea.append("Triangular Superior: No\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PropiedadesMatriz());
    }
}
