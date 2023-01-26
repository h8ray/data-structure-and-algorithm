package datastructure.sort;

import util.CommonUtil;

public class MergeSort {
    //迭代实现
    public static void mergeSort(int[] arr) {
        int distance = 1;//表示要合并的一对数组各对应位次上数的下标差值，也是每个数组的长度
        while (distance < arr.length) {
            for (int start = 0; start + distance < arr.length; start += 2 * distance) {
                //start表示第1个数组的起始下标，start+distance表示第2个数组的起始下标，因此center = start+distance-1
                //start += 2*distance表示对下一对数组（每个数组的长度是distance，每一对数组的总长度是2*distance）进行合并
                //因此第2个数组的末尾下标为 start + 2 * distance - 1
                merge(arr, start, start + distance - 1, Math.min(arr.length - 1, start + 2 * distance - 1));
            }
            distance = distance << 1;//每次数组的长度（距离）翻倍
        }
    }

    //递归实现
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int center = (left + right) >> 1;
            mergeSort(arr, left, center);
            mergeSort(arr, center + 1, right);
            merge(arr, left, center, right);
        }
    }

    private static void merge(int[] arr, int left, int center, int right) {
        int len = right - left + 1;
        int[] tmp = new int[len];

        int tmpIndex = 0;
        int leftIndex = left;//左数组的起始位置
        int rightIndex = center + 1;//右数组的起始位置

        while (leftIndex <= center && rightIndex <= right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                tmp[tmpIndex] = arr[leftIndex++];
            } else {
                tmp[tmpIndex] = arr[rightIndex++];
            }
            tmpIndex++;
        }
        while (leftIndex <= center) {
            tmp[tmpIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            tmp[tmpIndex++] = arr[rightIndex++];
        }

        tmpIndex = 0;
        while (tmpIndex < len) {
            arr[left + tmpIndex] = tmp[tmpIndex++];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        mergeSort(arr1);
        CommonUtil.printArray(arr1);

        int[] arr2 = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        mergeSort(arr2, 0, arr2.length - 1);
        CommonUtil.printArray(arr2);
    }
}
