public class Algorithms {


    public static void NaivStandard(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
        int aux;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aux = 0;
                for (int k = 0; k < P; k++) {
                    aux += A[i][k]*B[k][j];
                }
                Result[i][j] = aux;
            }
        }
    }

}
