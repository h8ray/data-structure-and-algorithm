package datastructure.heap;

import java.util.Arrays;
import java.util.Comparator;


public class Heap<E> {
    private static final int DEFAULTCAPACITY = 16;

    private Object[] data;
    private int size;
    private Comparator<? super E> comparator;

    public Heap() {
        this.data = new Object[DEFAULTCAPACITY];
    }

    public Heap(int initialCapacity) {
        this(initialCapacity, null);
    }

    public Heap(Comparator<? super E> comparator) {
        this();
        this.comparator = comparator;
    }

    public Heap(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 1) {
            this.data = new Object[DEFAULTCAPACITY];
        } else {
            this.data = new Object[initialCapacity];
        }
        this.comparator = comparator;
    }

    public boolean add(E e) {
        if (e == null) {
            //添加的元素不能为null
            throw new NullPointerException();
        }
        if (size >= data.length) {
            //堆的空间不够，扩容2倍
            data = Arrays.copyOf(data, data.length << 1);
        }
        up(e);
        size++;//添加完之后size要加1
        return true;
    }

    public boolean add(E[] arr) {
        for (E e : arr) {
            add(e);
        }
        return true;
    }

    public E remove() {
        if (size == 0) {
            return null;
        }
        size--;
        E ret = (E) data[0];
        E x = (E) data[size];//取出最后一个元素
        data[size] = null;//最后一个元素的位置置空

        if (size != 0) {//取走堆顶元素后堆不为空，需要将最后一个元素放到栈顶，然后向下调整
            down(x);
        }

        return ret;
    }

    public E peek() {
        return size == 0 ? null : (E) data[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void up(E e) {
        int child = size;//子节点的初始位置

        while (child > 0) {
            int parent = (child - 1) >>> 1;//找到父节点
            E parentVal = (E) data[parent];

            //和父节点比较，如果比父节点大就退出循环，不再调整
            if (comparator != null) {
                if (comparator.compare(e, (E) parentVal) >= 0) {
                    break;
                }
            } else {
                if (((Comparable<? super E>) e).compareTo(parentVal) >= 0) {
                    break;
                }
            }
            //如果比父节点小，就和父节点交换，继续往上调整
            data[child] = parentVal;
            child = parent;
        }

        //通过调整，找到合适的位置插入
        data[child] = e;
    }

    private void down(E e) {
        int half = size >>> 1;
        int index = 0;//父节点初始位置
        while (index < half) {//保证父节点有子节点
            int minIndex = (index << 1) + 1;//左子节点的位置
            E minChild = (E) data[minIndex];
            int rigthIndex = minIndex + 1;//右子节点的位置

            if (rigthIndex < size) {//右子节点存在
                //如果有右子节点，肯定会有左子节点。那么就需要左右两个子节点比较，把小的赋值给minChild
                E rightChild = (E) data[rigthIndex];
                if (comparator != null) {
                    if (comparator.compare(minChild, rightChild) > 0) {
                        minIndex = rigthIndex;
                        minChild = rightChild;
                    }
                } else {
                    if (((Comparable<? super E>) minChild).compareTo(rightChild) > 0) {
                        minIndex = rigthIndex;
                        minChild = rightChild;
                    }
                }
            }

            //用节点e和他的最小的子节点比较，如果小于他最小的子节点就退出循环，不再往下调整了
            if (comparator != null) {
                if (comparator.compare(e, minChild) <= 0) {
                    break;
                }
            } else {
                if (((Comparable<? super E>) e).compareTo(minChild) <= 0) {
                    break;
                }
            }

            //如果e比它的最小的子节点大，就用最小的子节点和e交换位置，然后再继续往下调整
            data[index] = minChild;
            index = minIndex;
        }

        data[index] = e;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]);
            sb.append(", ");
        }
        sb.append(data[size - 1]);
        return "[" + sb.toString() + "]";
    }
}
