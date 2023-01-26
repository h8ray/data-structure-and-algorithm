package algorithm.dp;

/**
 * 740. 删除并获得点数
 * <p>
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。
 * 之后，你必须删除所有 等于nums[i] - 1 和 nums[i] + 1的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * <p>
 * 示例2：
 * <p>
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 * <p>
 */
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        //转为打家劫舍问题
        int[] cnt = new int[max + 1];
        int[] dp = new int[max + 1];
        for (int num : nums) {
            cnt[num]++;
        }

        dp[0] = cnt[0];
        dp[1] = Math.max(cnt[0], cnt[1]);
        for (int i = 2; i < cnt.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * cnt[i]);
        }

        return dp[max];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        int ans = new DeleteAndEarn().deleteAndEarn(nums);
        System.out.println("ans = " + ans);
    }
}
