package algorithm.dp;

public class PortfolioProblem {
    private static int[][] solution;//备忘录

    /**
     * m元钱，n项投资， fi (x): 将 x 元投入第 i 个项目的效益. 求使得总效益最大的投资方案.
     * 问题的解是向量 < x0, x1, x2, ..., xn >， xi 是投给项目i 的钱数，i =0, 1, 2, ... , n.
     * 目标函数 max{f1(x0)+f2(x1)+…+fn(xn)}
     * 约束条件 x0+x1+…+xn=m，xi∈N
     */
    //f[i][j]表示i元钱都投资给第j+1个项目的效益
    public static int portfolioProblem(int[][] f) {
        int moneys = f.length;
        int projNum = f[0].length;

        //algorithm.dp[i][j]表示 i 元钱投给前 j+1 个项目的最大效益
        int[][] dp = new int[moneys][projNum];

        solution = new int[moneys][projNum];

        //初始状态，i元钱都投给第1个项目
        for (int i = 0; i < moneys; i++) {
            dp[i][0] = f[i][0];
            solution[i][0] = i;
        }

        for (int i = 0; i < moneys; i++) {//i元钱
            for (int j = 1; j < projNum; j++) {//投资给前j+1个项目
                for (int k = 0; k <= i; k++) {
                    //k从0到i，i-k元投资给前j个项目，k元钱投资给第j+1个项目
                    //取能让i元钱投资给前j+1个项目获取最大效益dp[i][j]的k
                    //algorithm.dp[i][j] = Math.max(algorithm.dp[i][j], algorithm.dp[i - k][j - 1] + f[k][j]);
                    int tmp = dp[i - k][j - 1] + f[k][j];
                    if (tmp > dp[i][j]) {
                        dp[i][j] = tmp;
                        solution[i][j] = k;
                    }
                }
            }
        }

        return dp[moneys - 1][projNum - 1];
    }

    public static void trackSolution() {
        int moneys = solution.length;
        int projNum = solution[0].length;

        int[] distribution = new int[projNum];
        for (int money = moneys - 1, index = projNum - 1; index >= 0; ) {
            distribution[index] = solution[money][index];
            money -= distribution[index];
            index--;
        }

        for (int i = 0; i < projNum; i++) {
            System.out.println("x_" + i + " = " + distribution[i]);
        }
    }

    public static void main(String[] args) {
        int[][] f = {{0, 0, 0, 0}, {11, 0, 2, 20}, {12, 5, 10, 21}, {13, 10, 30, 22}, {14, 15, 32, 23}, {15, 20, 40, 24}};

        int ans = portfolioProblem(f);
        System.out.println("最大效益：" + ans);

        trackSolution();
    }
}
