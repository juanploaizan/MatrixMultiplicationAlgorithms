import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        int[][] matrix1 = MatrixGenerator.loadMatrixFromFile(new File("matrix3.txt"));
        int[][] matrix2 = MatrixGenerator.loadMatrixFromFile(new File("matrix3.txt"));

        int option = 0;

        while (true) {
            int[][] result = new int[matrix1.length][matrix1[0].length];
            option = Integer.parseInt(JOptionPane.showInputDialog(
                    """
                    Menú.
                    1. NaivStandard\s
                    2. NaivOnArray\s
                    3. NaivKahan\s
                    4. NaivLoopUnrollingTwo\s
                    5. NaivLoopUnrollingThree\s
                    6. NaivLoopUnrollingFour\s
                    7. Strassen\s
                    8. Salir\s
                    """
                    ));
            if (option == 8) {
                break;
            }

            long startTime, endTime;
            switch (option) {
                case 1:
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivStandard(matrix1, matrix2, result, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 2:
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivOnArray(matrix1, matrix2, result, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 3:
                    double[][] resultDouble = new double[matrix1.length][matrix1[0].length];
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivKahan(matrix1, matrix2, resultDouble, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 4:
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivLoopUnrollingTwo(matrix1, matrix2, result, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 5:
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivLoopUnrollingThree(matrix1, matrix2, result, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 6:
                    startTime = System.currentTimeMillis();
                    Algorithms.NaivLoopUnrollingFour(matrix1, matrix2, result, matrix1.length, matrix1[0].length, matrix2[0].length);
                    endTime = System.currentTimeMillis();
                    printMatrix(result);
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                case 7:
                    startTime = System.currentTimeMillis();
                    GFG s = new GFG();
                    result = s.multiply(matrix1, matrix2);
                    endTime = System.currentTimeMillis();
                    printMatrix(result);
                    System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    public static void printMatrix(int[][] matriz) {
        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

}
