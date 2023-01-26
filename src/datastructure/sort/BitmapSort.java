package datastructure.sort;

import util.CommonUtil;

import java.util.Arrays;

/**
 * 位图排序也称为bitmap排序，它主要用于海量数据去重和海量数据排序，假如说有10
 * 亿个int类型且全部不相同的数据，给1G内存让你排序，你怎么排，如果全部加载到内
 * 存中，相当于40亿个字节，大概约等于4G内存。所以全部加载到内存肯定不行，如果我
 * 们使用位图排序的话，我们用long类型表示，一个long占用8个字节也就是64位，所以
 * 如果我们使用位图排序的话只会占用约0.125G内存,内存占用大大减少。但位图排序有
 * 个缺点就是数据不能有重复的，如果有重复的会覆盖掉，这也是位图能在海量数据中去
 * 重的原因，
 */
public class BitmapSort {
    public static int[] bitmapSort(int[] arr) {
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

        //需要用n个long
        int n = (max - min) / 64 + 1;
        long[] bitmap = new long[n];
        //arr中每个数都在对应的long上找到自己的位置，该位用1来表示
        for (int x : arr) {
            bitmap[(x - min) / 64] |= 1L << ((x - min) % 64);
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 64; j++) {
                if ((bitmap[i] & (1L << j)) != 0) {
                    arr[index++] = i * 64 + min + j;
                }
            }
        }

        if (index < len) {
            return Arrays.copyOfRange(arr, 0, index);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {27, -99, 0, -8, 13, 64, 86, -16, 7, 10, 88, 25, 90};
        int[] sortArr = bitmapSort(arr);
        CommonUtil.printArray(sortArr);
    }
}
