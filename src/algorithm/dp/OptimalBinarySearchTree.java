package algorithm.dp;

public class OptimalBinarySearchTree {
    /**
     * 给定按升序排序的数据集 S = < x1 , x2 , …, xn >,
     * 及S 的存取概率分布如下：
     * P = < a0 , b1 , a1 , b2 , a2 , … , bn , an >
     * 求一棵最优的( 即平均比较次数最少的) 二分检索树.
     */
    public static double optimalBinarySearchTree(int[] S, double[] P) {
        int n = S.length;
        double[][] dp = new double[n + 2][n + 2];

        //初值条件,algorithm.dp[i][i-1] = 0

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Double.MAX_VALUE;
                for (int k = i; k <= j; k++) {//x_k为树根
                    dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + sumOfIJ(P, (i - 1) * 2, (j - 1) * 2 + 2));
                }
            }


        }
        return dp[1][n];
    }

    private static double sumOfIJ(double[] P, int i, int j) {
        double sum = 0;
        for (int k = i; k <= j; k++) {
            sum += P[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] S = {1, 2, 3, 4, 5};
        double[] P = {0.04, 0.1, 0.02, 0.3, 0.02, 0.1, 0.05, 0.2, 0.06, 0.1, 0.01};
        double ans = optimalBinarySearchTree(S, P);
        System.out.println("最小的平均比较次数：" + ans);
    }
}
