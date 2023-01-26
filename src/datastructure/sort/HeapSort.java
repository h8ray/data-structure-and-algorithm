package datastructure.sort;

import util.CommonUtil;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr, len);
        for (int i = 0; i < len; i++) {
            CommonUtil.swap(arr, 0, len - 1 - i);//将最大值取出放到数组的后面，数组后面原先的值放到堆顶
            down(arr, 0, len - 1 - i);//堆的大小每次循环都减一，调整最大堆
        }
    }

    //调整数组下标为i的节点为根节点的最大堆
    private static void down(int[] arr, int i, int heapSize) {
        int leftChild = 2 * i + 1;//左子节点的下标
        int rightChild = 2 * i + 2;//右子节点的下标
        int maxIndex = i;//最大值的下标

        if (leftChild < heapSize && arr[leftChild] > arr[maxIndex]) {
            maxIndex = leftChild;
        }
        if (rightChild < heapSize && arr[rightChild] > arr[maxIndex]) {
            maxIndex = rightChild;
        }

        if (maxIndex != i) {
            //如果子节点的值比父节点的值大，交换两个值
            CommonUtil.swap(arr, i, maxIndex);
            //继续向下调整以子节点为根节点的最大堆
            down(arr, maxIndex, heapSize);
        }
    }

    public static void buildMaxHeap(int[] arr, int heapSize) {
        //从最后一个非叶节点(有儿子的节点）开始循环
        for (int i = (heapSize - 2) >> 1; i >= 0; i--) {
            down(arr, i, heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr = {27, 99, 0, 8, 13, 64, 86, 16, 7, 10, 88, 25, 90};
        heapSort(arr);
        CommonUtil.printArray(arr);
    }

}
