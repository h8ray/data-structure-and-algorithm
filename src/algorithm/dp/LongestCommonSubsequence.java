package algorithm.dp;

/**
 * 最长公共子序列
 */
public class LongestCommonSubsequence {
    private static StringBuffer longestCommonStr = new StringBuffer();

    //最长公共子序列
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        //dp[i][j]表示text1中下标0到i-1(长度为i)的子串和text2中下标0到j-1(长度为j)的子串的最长公共子序列
        int[][] dp = new int[len1 + 1][len2 + 1];

        //初值条件，dp[i][0] = 0, dp[0][j] = 0;

        for (int i = 1; i <= len1; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    longestCommonStr.append(c1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";

        int len = longestCommonSubsequence(text1, text2);
        System.out.println("最长公共子序列的长度：" + len);

        System.out.println("最长公共子序列：" + longestCommonStr);
    }
}
