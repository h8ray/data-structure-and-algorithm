package datastructure.heap;

import util.CommonUtil;

import java.util.Comparator;

public class HeapTest {
    public static void main(String[] args) {
        Integer[] array = {10, 4, 8, 3, 5, 1};
        System.out.print("原数组：");
        CommonUtil.printArray(array);

        Heap<Integer> minHeap = new Heap<>(10);
        minHeap.add(array);
        System.out.println("最小堆中的元素：" + minHeap);

        int size = minHeap.size();
        System.out.print("升序：");
        for (int i = 0; i < size; i++) {
            System.out.print(minHeap.remove() + " ");
        }
        System.out.println();

        Heap<Integer> maxHeap = new Heap<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        maxHeap.add(array);
        System.out.println("最大堆中的元素：" + maxHeap);

        size = maxHeap.size();
        System.out.print("降序：");
        for (int i = 0; i < size; i++) {
            System.out.print(maxHeap.remove() + " ");
        }
        System.out.println();
    }
}
