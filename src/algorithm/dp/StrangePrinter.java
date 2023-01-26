package algorithm.dp;

/**
 * 664. 奇怪的打印机
 * <p>
 * 有台奇怪的打印机有以下两个特殊要求：
 * <p>
 * <p>
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * <p>
 * <p>
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 * <p>
 * Related Topics 深度优先搜索 动态规划
 */
public class StrangePrinter {
    public int strangePrinter(String s) {
        int n = s.length();

        //dp[i][j] 表示s[i,j]的最少打印次数
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //如果两端字符一样，则打印s[i]可以顺便打印s[j]
                    //那么s[i,j]所需的最少打印次数和s[i,j-1]一样
                    dp[i][j] = dp[i][j - 1];
                } else {
                    //如果两端字符不一样，那么找到一个分割点，分成左右两边
                    //左右两边s[i,k]和s[k+1,j]总和的最少打印次数就是s[i,j]的最少打印次数
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }

            }
        }

        return dp[0][n - 1];
    }
}
