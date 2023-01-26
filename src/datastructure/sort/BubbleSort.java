package datastructure.sort;

import util.CommonUtil;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    CommonUtil.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        bubbleSort(arr);
        CommonUtil.printArray(arr);
    }
}
