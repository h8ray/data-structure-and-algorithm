package algorithm.binary;

/**
 * 1482. 制作 m 束花所需的最少天数
 * <p>
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * <p>
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * <p>
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 * <p>
 * Related Topics 数组 二分查找
 */
public class MinimumNumberOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < m * k) {
            // 花园中花的数量不够制作每束k朵的m束花
            return -1;
        }

        // 在0到bloomDay中的最大值之间用二分法寻找答案
        int left = 0, right = (int) 1e9;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(bloomDay, mid, m, k)) {
                // 等待mid天可以完成所有制作，说明答案不会大于mid天
                // 可以继续寻找比mid小的天数
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return check(bloomDay, right, m, k) ? right : -1;
    }

    /**
     * @param bloomDay 每朵花开花需要等待的天数
     * @param day      已经等待的天数
     * @param m        需要制作的花束数
     * @param k        每束花需要的连续k朵花
     * @return 是否能制作所有花束
     */

    private boolean check(int[] bloomDay, int day, int m, int k) {
        // 记录已制作的花束数
        int cnt = 0;

        //
        for (int i = 0; i < bloomDay.length && cnt < m; ) {
            // 第i朵花已开，连续开的花数continuousCnt设为1
            // 否则设为0
            int continuousCnt = bloomDay[i] <= day ? 1 : 0;
            if (continuousCnt > 0) {
                int j = i + 1;
                while (continuousCnt < k && j < bloomDay.length && bloomDay[j] <= day) {
                    continuousCnt++;
                    j++;
                }

                if (continuousCnt == k) {
                    cnt++;
                }

                i = j;
            } else {
                //第i朵没开，下一朵
                i++;
            }
        }

        return cnt >= m;
    }

    public static void main(String[] args) {
        /*
         * 花园在 7 天后和 12 天后的情况如下：
         * 7 天后：[x, x, x, x, _, x, x]
         * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
         * 12 天后：[x, x, x, x, x, x, x]
         * 显然，我们可以用不同的方式制作两束花。
         */
        int[] bloomDay = {7, 7, 7, 7, 12, 7, 7};
        int m = 2, k = 3;
        int ans = new MinimumNumberOfDaysToMakeMBouquets().minDays(bloomDay, m, k);
        System.out.println("ans = " + ans);
    }
}
