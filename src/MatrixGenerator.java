import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MatrixGenerator {

    public static void main(String[] args) {
        // Crear un arrays de int
        int[][] matrix = new int[2048][2048];

        // Llenar la matrix con números aleatorios
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = random.nextInt(10000000, 100000000);
            }
        }

        // Crear un archivo txt para guardar el arrays.array
        File file = new File("matrix.txt");
        try {
            // Crear un objeto PrintWriter para escribir en el archivo
            PrintWriter pw = new PrintWriter(file);

            // Recorrer la matrix y escribir cada elemento en una línea separada
            for (int[] row : matrix) {
                for (int num : row) {
                    pw.println(num);
                }
            }

            // Cerrar el PrintWriter
            pw.close();
            // Mostrar un mensaje de éxito
            System.out.println("La matriz se ha guardado en el archivo " + file.getName());
        } catch (FileNotFoundException e) {
            // Mostrar un mensaje de error si ocurre una excepción
            System.out.println("No se pudo crear el archivo " + file.getName());
        }
    }

    // Un método que permite cargar en un int[][] los valores desde el archivo generado
    public static int[][] loadMatrixFromFile(File file) {
        int[][] loadedMatrix = null;
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<ArrayList<Integer>> tempMatrix = new ArrayList<>();
            while (scanner.hasNextInt()) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < 2048 && scanner.hasNextInt(); j++) {
                    row.add(scanner.nextInt());
                }
                tempMatrix.add(row);
            }
            scanner.close();
            loadedMatrix = new int[tempMatrix.size()][tempMatrix.get(0).size()];
            for (int i = 0; i < tempMatrix.size(); i++) {
                for (int j = 0; j < tempMatrix.get(i).size(); j++) {
                    loadedMatrix[i][j] = tempMatrix.get(i).get(j);
                }
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.err.println("Archivo no encontrado.");
        }
        return loadedMatrix;
    }
}

