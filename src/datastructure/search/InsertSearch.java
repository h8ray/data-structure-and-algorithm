package datastructure.search;


public class InsertSearch {
    /**
     * @param arr 按升序排序的数组
     * @param key 要查找的关键字
     * @return 关键字所在的数组下标，不存在返回-1
     */
    public static int insertSearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (arr[left] == arr[right]) {
                if (arr[left] == key) {
                    return left;
                } else {
                    return -1;
                }
            }
            //类似于把数组下标作为x，数组上的值作为y，令要找的数组下标mid在x的比值和key在y上的比值一样，来寻找mid
            //这样能让mid离要查找的key的下标很近
            // (mid - left)/(right - left) = (key - arr[left])/(arr[right] - arr[left])
            int mid = left + (key - arr[left]) / (arr[right] - arr[left]) * (right - left);
            int midVal = arr[mid];
            if (key == midVal) {
                return mid;
            } else if (key < midVal) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-99, -16, -8, 0, 7, 10, 13, 25, 27, 64, 86, 88, 90};
        int key = -99;
        int keyPos = insertSearch(arr, key);
        System.out.println(keyPos);
    }
}
