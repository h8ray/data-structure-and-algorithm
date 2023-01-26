package algorithm.dc;


public class FibonacciPow {
    private static int[][] fibonacciMatrix = new int[2][2];

    static {
        fibonacciMatrix[0][0] = 1;
        fibonacciMatrix[0][1] = 1;
        fibonacciMatrix[1][0] = 1;
        fibonacciMatrix[1][1] = 0;
    }

    public static int[][] mtimes(int[][] matrix1, int[][] matrix2) {
        int m = matrix1.length;
        int n = matrix2[0].length;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrix;
    }

    //n>=1
    public static int[][] fibonacciMatrixPow(int n) {
        if (n == 1) {
            return fibonacciMatrix;
        }
        int[][] matrix = fibonacciMatrixPow(n >> 2);
        int[][] ans;
        if ((n & 1) == 0) { //n为偶数
            ans = mtimes(matrix, matrix);
        } else {
            ans = mtimes(mtimes(matrix, matrix), fibonacciMatrix);
        }
        return ans;
    }

    public static int fibonacciPow(int n) {
        return fibonacciMatrixPow(n)[0][1];
    }

    public static void main(String[] args) {
        int n = 2;
        int ans = fibonacciPow(n);
        System.out.println("fib(" + n + ") = " + ans);
    }

}
