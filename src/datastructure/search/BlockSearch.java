package datastructure.search;

public class BlockSearch {
    /**
     * 分块查找是折半查找和顺序查找的一种改进方法，
     * 分块查找由于只要求索引表是有序的，对块内节点没有排序要求，因此特别适合于节点动态变化的情况。
     * 分块查找要求把一个数据分为若干块，每一块里面的元素可以是无序的，但是块与块之间的元素需要是有序的。
     * 块之间有序是指前一块的最大值要小于后一块的最小值。
     * 输入的查找数组arr和每个块的大小blockSize，必须保证前一块中的任意元素都小于后一块中的任意元素
     */


    /**
     * @param arr       要查找的数组
     * @param key       要查找的关键字
     * @param index     每块的最大值
     * @param blockSize 每个块的大小
     * @return 找到关键字返回其所在的下标，没找到返回-1
     */
    public static int blockSearch(int[] arr, int key, int[] index, int blockSize) {
        int i = binarySearchBlock(index, key);
        if (i == -1) {
            return -1;
        }

        for (int j = i * blockSize, len = j + blockSize; j < arr.length && j < len; j++) {
            if (arr[j] == key) {
                return j;
            }
        }

        return -1;
    }

    /**
     * @param arr       要查找的数组
     * @param key       要查找的关键字
     * @param blockSize 每个块的大小
     * @return 找到关键字返回其所在的下标，没找到返回-1
     */
    public static int blockSearch(int[] arr, int key, int blockSize) {
        //块的数量
        int blockCount = (arr.length - 1) / blockSize + 1;

        int[] index = new int[blockCount];
        for (int i = 0; i < blockCount; i++) {
            index[i] = arr[i * blockSize];
        }

        for (int i = 0; i < arr.length; i++) {
            index[i / blockSize] = Math.max(index[i / blockSize], arr[i]);
        }
        return blockSearch(arr, key, index, blockSize);
    }

    /**
     * @param index 每块的最大值
     * @param key   要查找的关键字
     * @return 要查找关键字所在的块
     */
    private static int binarySearchBlock(int[] index, int key) {
        int left = 0;
        int right = index.length - 1;
        if (key > index[right]) {
            //如果要查找关键字比最后一个块（最大块）的最大值还大，说明该关键字不存在
            return -1;
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midVal = index[mid];
            if (key <= midVal) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        //输入的查找数组arr和每个块的大小blockSize，必须保证前一块中的任意元素都小于后一块中的任意元素
        int arr[] = {22, 12, 13, 8, 9, 20, 33, 42, 44, 38, 24, 48, 60, 58, 74, 49, 86, 53};
        int blockSize = 6;

        int key = 44;
        int keyPos = blockSearch(arr, key, blockSize);
        System.out.println(keyPos);

        int index[] = {22, 48, 86};
        int keyPos1 = blockSearch(arr, key, index, blockSize);
        System.out.println(keyPos1);
    }

}
