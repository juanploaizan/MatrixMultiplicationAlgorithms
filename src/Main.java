import java.io.File;

public class Main {

    public static void main(String[] args) {
        int[][] matriz = MatrixGenerator.loadMatrixFromFile(new File("matrix.txt"));

        for (int[] ints : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

}
