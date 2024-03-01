import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VectorCalculator extends JFrame {

    public VectorCalculator() {
        initUI();
    }

    private void initUI() {
        // Configurar ventana principal
        setTitle("Calculadora de Vectores");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear menú
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Guardar");
        JMenuItem closeItem = new JMenuItem("Salir");

        // Agregar acciones
        closeItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        menuBar.add(fileMenu);

        // Submenú para operaciones con vectores
        JMenu vectorMenu = new JMenu("Vectores");
        JMenuItem sumVectorsItem = new JMenuItem("Sumar Vectores");

        sumVectorsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí iría el código para abrir el panel de suma de vectores
                EventQueue.invokeLater(() -> {
                VectorSuma VS = new VectorSuma();
                VS.setVisible(true);
                });
            }
        });

        vectorMenu.add(sumVectorsItem);
        menuBar.add(vectorMenu);

        setJMenuBar(menuBar);
    }

    private void openVectorSumPanel() {
        // Implementar lógica para abrir el panel de suma de vectores
        JOptionPane.showMessageDialog(this, "Aquí se abriría el panel para sumar vectores.");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VectorCalculator vc = new VectorCalculator();
            vc.setVisible(true);
        });
    }
}
