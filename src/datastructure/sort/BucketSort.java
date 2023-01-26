package datastructure.sort;

import util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {
    /**
     * @param arr
     * @param bucketSize 每个桶的大小，这里的大小不是指桶能存放的元素个数，而是指桶能存放的元素种类数
     */
    public static void bucketSort(int[] arr, int bucketSize) {
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

        int bucketCount = (max - min) / bucketSize + 1;//桶的数量

        //创建桶
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        //将元素放入桶中
        for (int i = 0; i < len; i++) {
            buckets.get((arr[i] - min) / bucketSize).add(arr[i]);
        }

        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            //取出每个桶中的数据
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);

            //随便用一种排序算法对每一个桶进行排序
            Arrays.sort(bucketArray);

            //将桶中的数据依次赋值给原数组
            for (int j = 0; j < bucketArray.length; j++) {
                arr[index++] = bucketArray[j];
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        // int[] arr = {1,2,2,2};
        int bucketSize = 1;
        bucketSort(arr, bucketSize);
        CommonUtil.printArray(arr);
    }
}
