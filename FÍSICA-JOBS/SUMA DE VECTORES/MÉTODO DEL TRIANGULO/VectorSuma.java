import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

public class VectorSuma extends JFrame {

    private JPanel inputPanel;
    private JPanel drawPanel;
    private JTextField vector1xField;
    private JTextField vector1yField;
    private JTextField vector2xField;
    private JTextField vector2yField;
    private JButton calculateButton;
    private int sumX, sumY; // Estos almacenarán la suma de los vectores

    public VectorSuma() {
        initUI();
    }

    private void initUI() {
        setTitle("Suma de Vectores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputPanel = new JPanel(new FlowLayout());

        vector1xField = new JTextField(5);
        vector1yField = new JTextField(5);
        vector2xField = new JTextField(5);
        vector2yField = new JTextField(5);
        calculateButton = new JButton("Calcular");

        inputPanel.add(new JLabel("Vector 1 (x, y): "));
        inputPanel.add(vector1xField);
        inputPanel.add(vector1yField);
        inputPanel.add(new JLabel("Vector 2 (x, y): "));
        inputPanel.add(vector2xField);
        inputPanel.add(vector2yField);
        inputPanel.add(calculateButton);

        drawPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                drawCoordinateSystem(g2d);

                // Solo dibuja los vectores si se han calculado (sumX y sumY tienen valor)
                if (sumX != 0 || sumY != 0) {
                    drawVectors(g2d);
                }
            }
        };
        
        drawPanel.setBackground(Color.WHITE);

        add(inputPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        calculateButton.addActionListener(e -> calculateAndDraw());
    }

    private void calculateAndDraw() {
        try {
            int v1x = Integer.parseInt(vector1xField.getText());
            int v1y = Integer.parseInt(vector1yField.getText());
            int v2x = Integer.parseInt(vector2xField.getText());
            int v2y = Integer.parseInt(vector2yField.getText());

            // Suma de vectores
            sumX = v1x + v2x;
            sumY = v1y + v2y;

            // Solicita a drawPanel que se repinte para mostrar los nuevos vectores
            drawPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.");
        }
    }

    private void drawCoordinateSystem(Graphics2D g2d) {
       
      int width = drawPanel.getWidth();
      int height = drawPanel.getHeight();
      int centerX = width / 2;
      int centerY = height / 2;

      // Ejes
      g2d.drawLine(centerX, 0, centerX, height); // Eje Y
      g2d.drawLine(0, centerY, width, centerY); // Eje X

      // Etiquetas para los ejes
      g2d.drawString("N", centerX - 10, 15);
      g2d.drawString("S", centerX - 10, height - 5);
      g2d.drawString("E", width - 15, centerY + 15);
      g2d.drawString("O", 5, centerY + 15);

      // Etiquetas para x y y
      g2d.drawString("X", width - 20, centerY - 10);
      g2d.drawString("Y", centerX + 5, 10);

      // Marcas en los ejes
      int axisStep = 20; // Cambia esto según la escala que desees
      for (int i = centerX; i < width; i += axisStep) {
        g2d.drawLine(i, centerY - 5, i, centerY + 5);
        g2d.drawLine(width - i, centerY - 5, width - i, centerY + 5);
        // Dibujando etiquetas de escala en el eje X
        if (i != centerX) { // Evita sobreponer el "0"
            g2d.drawString(Integer.toString((i - centerX) / axisStep), i - 5, centerY + 20);
            g2d.drawString(Integer.toString(-(i - centerX) / axisStep), width - i - 5, centerY + 20);
        }
    }
    for (int i = centerY; i < height; i += axisStep) {
        g2d.drawLine(centerX - 5, i, centerX + 5, i);
        g2d.drawLine(centerX - 5, height - i, centerX + 5, height - i);
        // Dibujando etiquetas de escala en el eje Y
        if (i != centerY) { // Evita sobreponer el "0"
            g2d.drawString(Integer.toString(-(i - centerY) / axisStep), centerX + 10, i + 5);
            g2d.drawString(Integer.toString((i - centerY) / axisStep), centerX + 10, height - i + 5);
        }   
      }
    }

    private void drawVectors(Graphics2D g2d) {
        int centerX = drawPanel.getWidth() / 2;
        int centerY = drawPanel.getHeight() / 2;
        int v1x = Integer.parseInt(vector1xField.getText());
        int v1y = Integer.parseInt(vector1yField.getText());

        g2d.drawLine(centerX, centerY, centerX + v1x, centerY - v1y);
        g2d.drawLine(centerX + v1x, centerY - v1y, centerX + sumX, centerY - sumY);
        g2d.setColor(Color.RED);
        g2d.drawLine(centerX, centerY, centerX + sumX, centerY - sumY);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VectorSuma VS = new VectorSuma();
            VS.setVisible(true);
        });
    }
}