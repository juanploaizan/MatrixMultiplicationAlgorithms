public class Algorithms {

    // 1 NaivStandard
    public static void NaivStandard(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
        int aux;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                aux = 0;
                //Dado que la matriz es n*n recorre los elemento de la fila/columna
                //de la matriz A/B y va sumando los resultados hasta llegar al final
                for (int k = 0; k < P; k++) {
                    aux += A[i][k]*B[k][j];
                }
                Result[i][j] = aux;
            }
        }
    }

    // 2 NaivOnArray
    public static void NaivOnArray(int[][]A,int[][] B,int[][] Result,int N,int  P,int M){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //Funciona igual que el NaivStandard, sin embargo, se ahorra la declaración de una variable
                Result[i][j] = 0;
                for (int k = 0; k < P; k++) {
                    Result[i][j] += A[i][k]*B[k][j];
                }
            }
        }
    }

    // 3 NaivKahan
    /* Este método funciona igual que los dos anteriores sin embargo esta pensado para multiplicar matrices
       con valores doubles por eso tiene ñas varibales t,sum,err que lo que hacen es acumular el error que se puede generar
       al operar este tipo de numeros
    */
    public static void NaivKahan(int[][]A,int[][] B,double[][] Result,int N,int  P,int M){
        double t, sum, err;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum = 0.0;
                err = 0.0;
                for (int k = 0; k < P; k++) {
                    err = err + A[i][k]*B[k][j];
                    t = sum + err;
                    err = (sum - t) + err;
                    sum = t;
                }
                Result[i][j] = sum;
            }
        }
    }

    // 4 NaivLoopUnrollingTwo
    public static void NaivLoopUnrollingTwo(int[][]A,int[][] B,int[][] Result,int N,int P,int M) {
        int i, j, k;
        int aux;
        if (P % 2 == 0) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Si el numero de filas/columna es par, empieza a multiplicar por parejas (k y k+1) y amuenta en dos para continuar con la siguiente pareja
                    for (k = 0; k < P; k += 2) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else {
            int PP = P - 1; //Si el número de filas/columnas es impar se le quita uno a la P para obtener la posición del ultimo elemento
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Si es impar hace el mismo proceso, pero sin contar el último elemento
                    for (k = 0; k < PP; k += 2) {
                        aux += A[i][k]*B[k][j] + A[i][k+1]*B[k+1][j];
                    }
                    //El ultimo elemento se multiplica aquí
                    Result[i][j] = aux + A[i][PP]*B[PP][j];
                }
            }
        }
    }

    // 5 NaivLoopUnrollingThree
    // Funciona muy parecido al anterior, solo que utilizando ternas
    public static void NaivLoopUnrollingThree(int[][]A,int[][] B,int[][] Result,int N,int P,int M) {
        int i, j, k;
        int aux;
        if (P % 3 == 0) {
            //Si el numero de filas/columnas es un numero multiplo de 3
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < P; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else if (P % 3 == 1) {
            //Si el número de filas/columnas no es un múltiplo de 3 y deja una posición sin evaluar
            int PP = P - 1; //Se obtiene la última posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < PP; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    //Se multiplica la ultima posición
                    Result[i][j] = aux + A[i][PP] * B[PP][j];
                }
            }
        } else {
            //Si el número de filas/columnas no es un múltiplo de 3 y deja dos posiciones sin evaluar
            int PP = P - 2; //Se obtiene la penúltima posición
            int PPP = P - 1;//Se obtiene la ultima posición la cual no se  va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de ternas y por eso aumentará el for en k+=3
                    for (k = 0; k < PP; k += 3) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j];
                    }
                    //Se multiplica las ultimas dos posiciones que quedaron sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j];
                }
            }
        }
    }

    // 6 NaivLoopUnrollingFour
    // Funciona muy parecido que el anterior, solo que utilizando cuartas
    public static void NaivLoopUnrollingFour(int[][]A,int[][] B,int[][] Result,int N,int P,int M){
        int i, j, k;
        int aux;
        if (P % 4 == 0) {
            //Si el numero de filas/columnas es un numero multiplo de 4
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < P; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    Result[i][j] = aux;
                }
            }
        } else if (P % 4 == 1) {
            //Si el numero de filas/columnas no es un multiplo de 4 y deja una posición sin evaluar
            int PP = P - 1; //Se obtiene la ultima posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica la ultima posicione que quedo sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j];
                }
            }
        } else if (P % 4 == 2) {
            //Si el número de filas/columnas no es un múltiplo de 4 y deja dos posiciones sin evaluar
            int PP = P - 2; //Se obtiene la penúltima posición la cual no se va a evaluar dentro del for
            int PPP = P - 1; //Se obtiene la última posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica las últimas dos posiciones que quedaron sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j];
                }
            }
        } else {
            int PP = P - 3; //Se obtiene la antepenúltima posición la cual no se va a evaluar dentro del for
            int PPP = P - 2; //Se obtiene la penúltima posición la cual no se va a evaluar dentro del for
            int PPPP = P - 1; //Se obtiene la última posición la cual no se va a evaluar dentro del for
            for (i = 0; i < N; i++) {
                for (j = 0; j < M; j++) {
                    aux = 0;
                    //Hará la multiplicación en forma de cuartas y por eso aumentará el for en k+=4
                    for (k = 0; k < PP; k += 4) {
                        aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j] + A[i][k + 2] * B[k + 2][j]
                                + A[i][k + 3] * B[k + 3][j];
                    }
                    //Se multiplica las últimas tres posiciones que quedaron sin evaluar en el for anterior
                    Result[i][j] = aux + A[i][PP] * B[PP][j] + A[i][PPP] * B[PPP][j] + A[i][PPPP] * B[PPPP][j];

                }
            }
        }
    }

    // 7 - Strassen
    public static int[][] Strassen(int[][] A, int[][] B) {
        // Order of matrix
        int n = A.length;

        int[][] r = new int[n][n];

        //SI SON MATRICES DE 1*1 AMBAS
        if (n == 1) r[0][0] = A[0][0] * B[0][0];

        else {
            //crear 4 submatrices para ambas matrices del mismo tamaño
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // LLena cada cuarto de matriz con la matriz correspondiente A o B
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            // Using Formulas as described in algorithm

            // M1:=(A1+A3)×(B1+B2)
            int[][] M1 = Strassen(add(A11, A22), add(B11, B22));

            // M2:=(A2+A4)×(B3+B4)
            int[][] M2 = Strassen(add(A21, A22), B11);

            // M3:=(A1−A4)×(B1+A4)
            int[][] M3 = Strassen(A11, sub(B12, B22));

            // M4:=A1×(B2−B4)
            int[][] M4 = Strassen(A22, sub(B21, B11));

            // M5:=(A3+A4)×(B1)
            int[][] M5 = Strassen(add(A11, A12), B22);

            // M6:=(A1+A2)×(B4)
            int[][] M6 = Strassen(sub(A21, A11), add(B11, B12));

            // M7:=A4×(B3−B1)
            int[][] M7 = Strassen(sub(A12, A22), add(B21, B22));

            // P:=M2+M3−M6−M7
            int[][] C11 = add(sub(add(M1, M4), M5), M7);

            // Q:=M4+M6
            int[][] C12 = add(M3, M5);

            // R:=M5+M7
            int[][] C21 = add(M2, M4);

            // S:=M1−M3−M4−M5
            int[][] C22 = add(sub(add(M1, M3), M2), M6);

            // Step 3: Join 4 halves into one result matrix
            join(C11, r, 0, 0);
            join(C12, r, 0, n / 2);
            join(C21, r, n / 2, 0);
            join(C22, r, n / 2, n / 2);
        }

        return r;
    }

    // Method 2
    // Function to subtract two matrices
    private static int[][] sub(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        //filas y columnas
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];

        // Returning the resultant matrix
        return C;
    }

    // Method 3
    // Function to sum two matrices
    private static int[][] add(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        //filas y columnas
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];

        // Returning the resultant matrix
        return C;
    }

    // Method 4
    // Function to split parent matrix
    // into child matrices
    private static void split(int[][] P, int[][] C, int iB, int jB){
        //filas y columnas
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    // Method 5
    // Function to join child matrices
    // into (to) parent matrix
    private static void join(int[][] C, int[][] P, int iB, int jB){
        //filas y columnas
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }


}
