package util;


public class CommonUtil {
    public static final int KEY_NOT_EXISTS = Integer.MIN_VALUE;

    public static <E> void printArray(E[] arr) {
        for (E e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }
}
