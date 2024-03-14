import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.io.*;

public class ExamenInterfazMatriz extends JFrame {

    private JFrame frame;                                                   
    private JPanel panel;                                                   
      
    public ExamenInterfazMatriz(){
     setLocationRelativeTo(null);
     setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem saveItem = new JMenuItem("Guardar");
        

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir un archivo
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    
                    // Aquí puedes trabajar con el archivo seleccionado
                    // Por ejemplo, mostrarlo en un JTextArea o hacer alguna operación
                }
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para guardar un archivo
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    // Aquí puedes guardar los datos en el archivo seleccionado
                    // Por ejemplo, guardar el contenido de un JTextArea en el archivo
                }
            }
        });

        menuBar.add(fileMenu);

        JMenu optionsMenu = new JMenu("Opciones");
        JMenuItem optionAItem = new JMenuItem("Propiedades de una Matriz");
        JMenuItem optionBItem = new JMenuItem("Regla de Sarrus");
        JMenuItem option1Item = new JMenuItem("Matriz de 2x2");
        JMenuItem option2Item = new JMenuItem("Primera Regla de Cofactores");
        JMenuItem option3Item = new JMenuItem("Segunda Regla de Cofactores");
        JMenuItem option4Item = new JMenuItem("Determinante n x m");
        JMenuItem option5Item = new JMenuItem("Matriz Triangular");

        optionsMenu.add(optionAItem);
        optionsMenu.add(optionBItem);
        optionsMenu.add(option1Item);
        optionsMenu.add(option2Item);
        optionsMenu.add(option3Item);
        optionsMenu.add(option4Item);
        optionsMenu.add(option5Item);


        menuBar.add(optionsMenu);
        
        optionAItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new PropiedadesMatriz());
            }
        
        });
        
        optionBItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new ReglaDeSarrus());
            }
        
        });
        
        option1Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new MatrizDe2X2());
            }
        
        });
        option2Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new PrimeraReglaCofactores());
            }
        
        });
        option3Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new MatriRR());
            }
        
        });
        option4Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new DeterminanteMatriz());
            }
        
        });
        option5Item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  SwingUtilities.invokeLater(() -> new MatrizTriangular());
            }
        
        });
   }
   public static void main(String[] args) {
      ExamenInterfazMatriz ventana = new ExamenInterfazMatriz();                                                       
      ventana.setBounds(600,300,300,300);                                                        
      ventana.setVisible(true);                                                                 
      ventana.setResizable(false);                                                              
      ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);   
              }
}