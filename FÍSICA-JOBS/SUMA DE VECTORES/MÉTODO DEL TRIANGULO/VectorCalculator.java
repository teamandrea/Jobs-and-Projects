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

        // Crear men�
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

        // Submen� para operaciones con vectores
        JMenu vectorMenu = new JMenu("Vectores");
        JMenuItem sumVectorsItem = new JMenuItem("Sumar Vectores");

        sumVectorsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aqu� ir�a el c�digo para abrir el panel de suma de vectores
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
        // Implementar l�gica para abrir el panel de suma de vectores
        JOptionPane.showMessageDialog(this, "Aqu� se abrir�a el panel para sumar vectores.");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VectorCalculator vc = new VectorCalculator();
            vc.setVisible(true);
        });
    }
}
