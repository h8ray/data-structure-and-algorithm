package datastructure.search;

import util.CommonUtil;

import java.util.Arrays;

public class HashSearch {
    /**
     * @param hash 哈希数组
     * @param key  关键字
     * @return 关键字在哈希数组中的下标
     */
    public static int hashSearch(int[] hash, int key) {
        int index = Math.abs(key % hash.length);
        while (hash[index] != Integer.MIN_VALUE && hash[index] != key) {
            index = (index + 1) % hash.length;
        }
        if (hash[index] == Integer.MIN_VALUE) {
            return -1;
        }
        return index;
    }

    public static int[] buildHash(int hashLength) {
        int[] hash = new int[hashLength];
        Arrays.fill(hash, Integer.MIN_VALUE);
        return hash;
    }

    public static void insertHash(int[] hash, int data) {
        int index = Math.abs(data % hash.length);
        while (hash[index] != Integer.MIN_VALUE) {
            index = (index + 1) % hash.length;
        }
        hash[index] = data;
    }

    public static void main(String[] args) {
        int[] arr = {-99, -16, -8, 0, 7, 10, 13, 25, 27, 64, 86, 88, 90};
        int key = -99;

        int hashLength = 31;
        int[] hash = buildHash(hashLength);
        for (int x : arr) {
            insertHash(hash, x);
        }

        int keyHashPos = hashSearch(hash, key);
        System.out.println(keyHashPos);

        // CommonUtil.printArray(hash);
    }

}
