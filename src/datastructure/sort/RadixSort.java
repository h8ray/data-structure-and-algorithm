package datastructure.sort;

import util.CommonUtil;

public class RadixSort {
    public static void radixSort(int[] arr) {
        int digitCount = 19;//从-9到9有19个数
        int maxCount = getBitCount(getMaxBit(arr));//获取数组中位数最多的数（绝对值最大的数）
        int radix = 1;//基数，1表示正在按个位排序，10表示正在按十位排序，以此类推
        int[][] tmpArr = new int[digitCount][arr.length];//二维数组来暂时存放每个基数上的数有哪些

        for (int i = 0; i < maxCount; i++) {//每一位循环一次
            int[] count = new int[digitCount];//记录每个基数上的数有几个

            for (int x : arr) {//遍历数组中的每个数
                int tmp = (x / radix) % 10 + 9;//算出在当前位上的数，再加上9，9是偏移量，为了让负数方便对应数组下标
                tmpArr[tmp][count[tmp]++] = x;
            }

            int index = 0;
            for (int j = 0; j < digitCount; j++) {
                //取出暂时存放在tmpArr的数，赋值给arr，相当按当前位上的数字大小排序一次
                if (count[j] > 0) {
                    for (int k = 0; k < count[j]; k++) {
                        arr[index++] = tmpArr[j][k];
                    }
                }
            }

            radix *= 10;
        }
    }


    //返回数组中位数最多的数
    private static int getMaxBit(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        return max > -min ? max : min;
    }

    //获取一个数的位数
    private static int getBitCount(int num) {
        int count = 1;
        num /= 10;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {27, -99, 0, -8, 13, 64, 86, -16, 7, 10, 88, 25, 90};
        radixSort(arr);
        CommonUtil.printArray(arr);
    }
}
