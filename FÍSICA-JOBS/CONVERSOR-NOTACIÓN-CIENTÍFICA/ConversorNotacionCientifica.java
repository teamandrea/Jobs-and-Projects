import javax.swing.JOptionPane;

public class ConversorNotacionCientifica {
    public static void main(String[] args) {
        // Pedir al usuario que ingrese un n�mero
        String input = JOptionPane.showInputDialog("Ingrese un n�mero en decimales o notaci�n cient�fica:");

        try {
            // Intentar convertir de notaci�n cient�fica a decimal
            double decimalNumber = convertirANotacionDecimal(input);
            JOptionPane.showMessageDialog(null, "N�mero en decimal: " + decimalNumber);
        } catch (NumberFormatException e) {
            try {
                // Intentar convertir de decimal a notaci�n cient�fica
                double decimalNumber = Double.parseDouble(input);
                int exponente = 0;

                // Calcular el exponente para representar en notaci�n cient�fica
                while (decimalNumber >= 10.0 || decimalNumber <= -10.0) {
         
                    decimalNumber /= 10.0;
                    exponente++;
                }

                while (decimalNumber < 1.0 && decimalNumber > -1.0) {
                    decimalNumber *= 10.0;
                    exponente--;
                }

                String scientificNotation = String.format("%.15fx10^%d", decimalNumber, exponente);
                JOptionPane.showMessageDialog(null, "N�mero en notaci�n cient�fica: " + scientificNotation);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Entrada inv�lida. Por favor, ingrese un n�mero v�lido.");
            }
        }
    }

    // M�todo para convertir notaci�n cient�fica a decimal
    private static double convertirANotacionDecimal(String input) {
        // Verificar si la notaci�n cient�fica es con "e" o "x"
        if (input.toLowerCase().contains("e")) {
            // Convertir notaci�n cient�fica con "e" a decimal
            String[] parts = input.split("e|E");
            double base = Double.parseDouble(parts[0]);
            int exponente = Integer.parseInt(parts[1]);
            return base * Math.pow(10, exponente);
        } else if (input.toLowerCase().contains("x")) {
            // Convertir notaci�n cient�fica con "x" a decimal
            String[] parts = input.split("x|X");
            double base = Double.parseDouble(parts[0]);
            int exponente = Integer.parseInt(parts[1]);
            return base * Math.pow(10, exponente);
        } else {
            throw new NumberFormatException();
        }
    }
}
