package datastructure.sort;

import util.CommonUtil;

public class InsertSort {
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];//记录当前值
            int j = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                //将当前值和前面已排序好的数比较
                //如果当前值<前面值，继续和再前一个数比较，直到比前面某个数大或者该数就是目前最小的
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;//在前面某个比该数小的数的后面插入该数（或者该数是第一个数）
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        insertSort(arr);
        CommonUtil.printArray(arr);
    }
}
