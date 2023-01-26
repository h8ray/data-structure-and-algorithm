package algorithm.dp;


public class MatrixChain {
    private static int[][] solution;//备忘录records记录右括号在哪个矩阵的右边

    //矩阵链相乘的最小乘法次数
    //序列P0、P1..Pn表示矩阵M0(P0*P1)、M1(P1*P2)...M{n-2}(P{n-2}*P{n-1})
    public static int matrixChain(int[] P) {
        if (P == null || P.length == 0) {
            return 0;
        }
        int n = P.length;

        //dp[i][j]表示矩阵Mi到Mj的矩阵链相乘的最小乘法次数
        int[][] dp = new int[n - 1][n - 1];

        //初值条件，algorithm.dp[i][i] = 0

        solution = new int[n - 1][n - 1];

        for (int len = 2; len <= n - 1; len++) {//len表示相乘的矩阵个数
            for (int left = 0; left <= n - 1 - len; left++) {//left表示最左边的矩阵下标
                int right = left + len - 1;//left表示最右边的矩阵下标

                //右括号初始位置在矩阵M{left}的右边
                dp[left][right] = dp[left + 1][right] + P[left] * P[left + 1] * P[right + 1];
                solution[left][right] = left;

                for (int split = left + 1; split <= right - 1; split++) {//split表示右括号的位置
                    int tmp = dp[left][split] + dp[split + 1][right] + P[left] * P[split + 1] * P[right + 1];
                    if (tmp < dp[left][right]) {
                        dp[left][right] = tmp;
                        solution[left][right] = split;
                    }
                }
            }
        }

        return dp[0][n - 2];
    }

    public static String trackSolution(int[][] solution, int i, int j) {
        if (i == j) {
            return "A_" + i;
        }
        int split = solution[i][j];
        return "(" + trackSolution(solution, i, split) + ")·(" + trackSolution(solution, split + 1, j) + ")";
    }

    public static void printMatrix(int[] P) {
        for (int i = 0; i < P.length - 1; i++) {
            System.out.println("A_" + i + ": " + P[i] + " × " + P[i + 1]);
        }
    }

    public static void main(String[] args) {
        int[] P = {30, 35, 15, 5, 10, 20};
        printMatrix(P);

        int cnt = matrixChain(P);
        System.out.println("最小乘法次数：" + cnt);

        int matrixNum = P.length - 1;
        String s = trackSolution(solution, 0, matrixNum - 1);
        System.out.println("括号位置：" + s);
    }
}
