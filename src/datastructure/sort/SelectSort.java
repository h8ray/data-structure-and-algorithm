package datastructure.sort;

import util.CommonUtil;

public class SelectSort {
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            //往后查找比该数arr[i]小的数，用index记录其下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            if (index != i) {
                //找到比arr[i]小的数arr[index]，交换arr[i]和arr[index]
                CommonUtil.swap(arr, i, index);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        selectSort(arr);
        CommonUtil.printArray(arr);
    }
}
