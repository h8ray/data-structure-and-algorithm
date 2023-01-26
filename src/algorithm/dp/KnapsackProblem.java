package algorithm.dp;

public class KnapsackProblem {

    /**
     * 一个旅行者随身携带一个背包. 可以放入背包的物品有 n 种, 每种物品的重量和价值分别为 wi , vi ，
     * 如果背包的最大重量限制是 b, 每种物品可以放多个. 怎样选择放入背包的物品以使得背包的价值最大 ?
     */
    public static int knapsackProblem(int[] values, int[] weights, int b) {
        int n = values.length;

        // dp[i][j]表示装前 i 种物品，总重不超过 j，背包达到的最大价值
        int[][] dp = new int[n + 1][b + 1];

        // 初值条件，dp[i][0] = 0，dp[0][j] = 0，即背包重量限制为0或装前0种物品获得的价值为0
        // 只装第1种物品，总重不超过 j，背包达到的最大价值
        for (int j = 1; j <= b; j++) {
            // j/weights[0] 向下取整表示能装多少个第1种物品
            dp[1][j] = j / weights[0] * values[0];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= b; j++) {
                //第 i 种物品的重量 weights[i - 1]

                //如果背包重量限制允许装第i种物品
                if (j - weights[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][b];
    }

    public static void main(String[] args) {
        int[] values = {1, 3, 5, 9};
        int[] weights = {2, 3, 4, 7};
        int b = 10;
        int ans = knapsackProblem(values, weights, b);
        System.out.println("最大价值：" + ans);
    }
}
