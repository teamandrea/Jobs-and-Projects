import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReglaDeSarrus extends JFrame {

    private JTextField rowsField, colsField;
    private JTextArea matrixArea, resultArea, diagonalPrincipalArea, diagonalSecundariaArea;

    public ReglaDeSarrus() {
        setTitle("Regla de Sarrus");
        setBounds(500, 300, 600, 300);
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

        JPanel matrixPanel = new JPanel(new GridLayout(1, 3)); // Panel para mostrar la matriz y las diagonales
        matrixArea = new JTextArea("Matriz\n");
        matrixArea.setEditable(false);
        matrixPanel.add(new JScrollPane(matrixArea));

        diagonalPrincipalArea = new JTextArea("Diagonal principal\n"); // JTextArea para mostrar la diagonal principal
        diagonalPrincipalArea.setEditable(false);
        matrixPanel.add(new JScrollPane(diagonalPrincipalArea));

        diagonalSecundariaArea = new JTextArea("Diagonal Secundaria\n"); // JTextArea para mostrar la diagonal secundaria
        diagonalSecundariaArea.setEditable(false);
        matrixPanel.add(new JScrollPane(diagonalSecundariaArea));

        add(matrixPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
    }

    private void llenarMatriz() {
    try {
        int rows = Integer.parseInt(rowsField.getText());
        int cols = Integer.parseInt(colsField.getText());

        if (rows != 3 || cols != 3) {
            JOptionPane.showMessageDialog(this, "La matriz debe ser de 3x3. Por favor, inténtelo de nuevo.");
            return;
        }

        int[][] matriz = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String inputValue;
                do {
                    inputValue = JOptionPane.showInputDialog("Ingrese valor para la posición (" + (i + 1) + "," + (j + 1) + "):");
                    if (inputValue.startsWith("+0") || inputValue.startsWith("-0")) {
                        JOptionPane.showMessageDialog(this, "El número 0 no puede tener un signo '+' o '-'. Por favor, inténtelo de nuevo.");
                    }
                } while (inputValue.startsWith("+0") || inputValue.startsWith("-0"));
                matriz[i][j] = Integer.parseInt(inputValue);
            }
        }

        mostrarMatriz(matriz);
        mostrarDiagonales(matriz);
        determinarTipoMatriz(matriz);
        // Mostrar el determinante
        int determinante = calcularDeterminante(matriz);
        resultArea.append("\nDeterminante: " + determinante);
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


        // Verificar si es una matriz fila
        boolean esMatrizFila = rows == 1 && cols > 1;

        // Verificar si es una matriz columna
        boolean esMatrizColumna = rows > 1 && cols == 1;

        // Verificar si es una matriz cuadrada
        boolean esMatrizCuadrada = rows == cols;

        // Verificar si es una matriz rectangular
        boolean esMatrizRectangular = rows != cols;

        // Verificar si es una matriz diagonal
        boolean esMatrizDiagonal = esMatrizCuadrada && verificaMatrizDiagonal(matriz);

        // Verificar si es una matriz identidad (matriz unidad)
        boolean esMatrizIdentidad = esMatrizDiagonal && verificaMatrizIdentidad(matriz);

        // Mostrar los resultados
        StringBuilder resultado = new StringBuilder();
        if (esMatrizNula) {
            resultado.append("Matriz Nula");
        } else if (esMatrizFila) {
            resultado.append("Matriz Fila");
        } else if (esMatrizColumna) {
            resultado.append("Matriz Columna");
        } else if (esMatrizCuadrada) {
            resultado.append("Matriz Cuadrada");
        } else if (esMatrizRectangular) {
            resultado.append("Matriz Rectangular");
        }

        if (esMatrizDiagonal) {
            resultado.append("\nMatriz Diagonal");
        }

        if (esMatrizIdentidad) {
            resultado.append("\nMatriz Identidad");
        }

        resultArea.setText("Tipo de matriz: " + resultado.toString());
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

    private int calcularDeterminante(int[][] matriz) {
        if (matriz.length != 3 || matriz[0].length != 3) {
            throw new IllegalArgumentException("La matriz debe ser de 3x3.");
        }

        int determinante = 0;

        // Multiplicación de elementos en la diagonal principal
        determinante += matriz[0][0] * matriz[1][1] * matriz[2][2];
        determinante += matriz[0][1] * matriz[1][2] * matriz[2][0];
        determinante += matriz[0][2] * matriz[1][0] * matriz[2][1];

        // Multiplicación de elementos en la diagonal secundaria
        determinante -= matriz[0][2] * matriz[1][1] * matriz[2][0];
        determinante -= matriz[0][0] * matriz[1][2] * matriz[2][1];
        determinante -= matriz[0][1] * matriz[1][0] * matriz[2][2];

        return determinante;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReglaDeSarrus());
    }
}