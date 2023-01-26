package algorithm.dp;

import java.util.LinkedList;
import java.util.List;

public class MaxSubArray {
    private static List<Integer> maxSubList = new LinkedList<>();

    //最大子序和
    public static int maxSubArray(int[] nums) {
        int maxSum = 0, sum = 0;
        List<Integer> tmpList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = 0;
                tmpList.clear();
            }
            sum += nums[i];
            tmpList.add(nums[i]);
            if (sum > maxSum) {
                maxSum = sum;
                maxSubList.clear();
                maxSubList.addAll(tmpList);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArray(nums);
        System.out.println("最大子序和：" + maxSum);
        System.out.println("最大子序和的序列：" + maxSubList);
    }
}
