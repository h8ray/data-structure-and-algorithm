package datastructure.sort;

import util.CommonUtil;

public class QuickSort {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //该数所在的位置就是最终排序好的位置
            int pivot = partition(arr, start, end);

            //对枢纽左边的数进行排序
            quickSort(arr, start, pivot - 1);

            //对枢纽右边的数进行排序
            quickSort(arr, pivot + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        //枢纽
        int pivotVal = arr[start];

        while (start < end) {
            while (start < end && arr[end] >= pivotVal) {
                end--;//从右往左找比枢纽小的数
            }

            //交换枢纽和比枢纽小的数的位置
            CommonUtil.swap(arr, start, end);

            while (start < end && arr[start] <= pivotVal) {
                start++;//从左往右找比枢纽大的数
            }

            //交换枢纽和比枢纽大的数的位置
            CommonUtil.swap(arr, start, end);
        }
        return start;
    }

    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        quickSort(arr);
        CommonUtil.printArray(arr);
    }
}
