package algorithm.dc;

import java.util.Random;

/**
 * 快速选择  O(n)
 */
public class QuickSelect {

    //选择第k小的数
    public static int quickSelect(int[] nums, int k) {
        //第k小的数对应的排好序后的下标为k-1
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    private static int quickSelect(int[] nums, int begin, int end, int index) {
        int p = randmonPartition(nums, begin, end);
        if (p == index) {
            return nums[p];
        } else if (p < index) {
            return quickSelect(nums, p + 1, end, index);
        } else {
            return quickSelect(nums, begin, p - 1, index);
        }
    }

    private static int randmonPartition(int[] nums, int begin, int end) {
        int randomIndex = new Random().nextInt(end - begin + 1) + begin;
        swap(nums, begin, randomIndex);
        return partition(nums, begin, end);
    }

    private static int partition(int[] nums, int begin, int end) {
        int pivot = nums[begin];
        int i = end + 1;
        for (int j = end; j > begin; j--) {
            if (nums[j] >= pivot) {
                swap(nums, j, --i);
            }
        }
        swap(nums, begin, --i);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        //0 7 8 10 13 16 25 27 64 86 88 90 99
        int[] nums = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        int ans = quickSelect(nums, 5);
        System.out.println(ans);
    }
}
