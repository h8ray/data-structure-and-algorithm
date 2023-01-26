package algorithm.dc;

/**
 * 快速排序 O(logn)
 */
public class QuickSort {
    public static void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int begin, int end) {
        if (begin < end) {
            int q = partition(nums, begin, end);
            quickSort(nums, begin, q - 1);
            quickSort(nums, q + 1, end);
        }

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
        //printArr(nums);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void printArr(int[] nums) {
        for (int x : nums) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        quickSort(nums);
        printArr(nums);
    }
}
