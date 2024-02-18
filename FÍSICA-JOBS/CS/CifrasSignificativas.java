import javax.swing.JOptionPane;

public class CifrasSignificativas {
    public static void main(String[] args) {
        // Pedir al usuario que ingrese un n�mero
        String input = JOptionPane.showInputDialog("Ingrese un n�mero:");

        // Validar la entrada y obtener cifras significativas
        CifrasSignificativasInfo info = obtenerCifrasSignificativas(input);

        // Mostrar el resultado
        JOptionPane.showMessageDialog(null, "Cifras Significativas: " + info.cifrasSignificativas +
                "\nLas Cifras Significativas son: " + info.cifrasSignificativasString);
    }

    private static CifrasSignificativasInfo obtenerCifrasSignificativas(String numero) {
        // Eliminar espacios y convertir a min�sculas para facilitar la comparaci�n
        numero = numero.trim().toLowerCase();

        // Inicializar contadores
        int cifrasSignificativas = 0;
        boolean entreCifrasSignificativas = false;
        boolean puntoDecimalEncontrado = false;
        StringBuilder cifrasSignificativasString = new StringBuilder();

        // Iterar sobre cada d�gito
        for (int i = 0; i < numero.length(); i++) {
            char caracter = numero.charAt(i);

            // Regla 1: D�gitos diferentes de cero son siempre significativos
            if (Character.isDigit(caracter) && caracter != '0') {
                cifrasSignificativas++;
                entreCifrasSignificativas = true;
                cifrasSignificativasString.append(caracter);
            }

            // Regla 2: Ceros entre dos cifras significativas son significativos
            if (caracter == '0' && entreCifrasSignificativas) {
                cifrasSignificativas++;
                cifrasSignificativasString.append(caracter);
            }

            // Regla 3: Ceros al final en la parte decimal son significativos
            if (caracter == '.') {
                puntoDecimalEncontrado = true;
                entreCifrasSignificativas = false;
            }
        }

        // Eliminar ceros finales no significativos en el caso de n�meros enteros
        if (!puntoDecimalEncontrado && cifrasSignificativasString.length() > 0) {
            cifrasSignificativasString = new StringBuilder(cifrasSignificativasString.toString().replaceAll("0*$", ""));
        }

        return new CifrasSignificativasInfo(cifrasSignificativas, cifrasSignificativasString.toString());
    }

    static class CifrasSignificativasInfo {
        int cifrasSignificativas;
        String cifrasSignificativasString;

        public CifrasSignificativasInfo(int cifrasSignificativas, String cifrasSignificativasString) {
            this.cifrasSignificativas = cifrasSignificativas;
            this.cifrasSignificativasString = cifrasSignificativasString;
        }
    }
}
