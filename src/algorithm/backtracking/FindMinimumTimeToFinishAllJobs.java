package algorithm.backtracking;

/**
 * 1723. 完成所有工作的最短时间
 * <p>
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * <p>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
 * 设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * <p>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 107
 * <p>
 * Related Topics 递归 回溯算法
 * <p>
 * <p>
 * 题解：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/gong-shui-san-xie-yi-ti-shuang-jie-jian-4epdd/
 */
public class FindMinimumTimeToFinishAllJobs {
    int ans = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        int[] sum = new int[k];
        backtrace(jobs, 0, sum, 0, 0);
        return ans;
    }

    void backtrace(int[] jobs, int index, int[] sum, int max, int used) {
        if (max >= ans) {
            //找到的某个解（某个解的前一部分）大于等于前面找到的某个解，剪枝
            return;
        }

        if (index == jobs.length) {
            ans = max;
            return;
        }

        //优先选择空闲员工,used即表示当前空闲员工的下标
        if (used < sum.length) {
            sum[used] = jobs[index];
            backtrace(jobs, index + 1, sum, Math.max(max, sum[used]), used + 1);
            sum[used] = 0;
        }

        for (int i = 0; i < used; i++) {
            sum[i] += jobs[index];
            backtrace(jobs, index + 1, sum, Math.max(max, sum[i]), used);
            sum[i] -= jobs[index];
        }
    }

    public static void main(String[] args) {
        int[] jobs = {1, 2, 4, 7, 8};
        int k = 2;
        int ans = new FindMinimumTimeToFinishAllJobs().minimumTimeRequired(jobs, k);
        System.out.println(ans);
    }
}
