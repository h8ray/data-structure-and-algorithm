package datastructure.sort;

import util.CommonUtil;

public class CountingSort {
    public static void countingSort(int[] arr) {
        int len = arr.length;
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }

        int bucketCount = max - min + 1;//桶的数量
        int[] buckets = new int[bucketCount];//桶

        for (int x : arr) {
            buckets[x - min]++;//落在某个桶里就加1
        }

        for (int i = 1; i < bucketCount; i++) {
            //后面桶对前面桶的累加，表示从前面的桶到该桶为止，小于等于该桶中的数有多少个
            buckets[i] += buckets[i - 1];
        }

        //把原数组保存到临时数组中
        int[] tmp = new int[len];
        System.arraycopy(arr, 0, tmp, 0, len);

        for (int i = 0; i < len; i++) {
            //前面提到，桶buckets[i]表示小于等于桶中那个数的数有多少(n)个（包括桶中的那个数）
            //那么桶中那个数最终在排好序的数组中的位置就是第n个，从桶中取出该数放到下标为n-1的位置，该桶的大小同时也得减1
            arr[--buckets[tmp[i] - min]] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, -99, 0, -8, 13, 64, 86, -16, 7, 10, 88, 25, 90};
        countingSort(arr);
        CommonUtil.printArray(arr);
    }
}
