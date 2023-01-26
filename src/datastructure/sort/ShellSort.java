package datastructure.sort;

import util.CommonUtil;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int len = arr.length;
        for (int step = len >> 1; step > 0; step >>= 1) {//迭代的是步长，根据步长分为多个子序列
            for (int i = step; i < len; i++) {//迭代的是每个子序列

                //对每个子序列执行插入排序
                int tmp = arr[i];
                int j = i;
                for (; j >= step && tmp < arr[j - step]; j -= step) {
                    arr[j] = arr[j - step];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, -99, 0, -8, 13, 64, 86, -16, 7, 10, 88, 25, 90};
        shellSort(arr);
        CommonUtil.printArray(arr);
    }
}
