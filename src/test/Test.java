package test;

import java.util.List;

public class Test {
    public static void main(String[] args) {
//        Integer i = -129, j = -129;
//        System.out.println(i.equals(j));
//
//        double d = 1.0f;
//        BigDecimal bigDecimal = BigDecimal.valueOf(d);
//        System.out.println(d + " " + bigDecimal);
//        int[] myArray = {1, 2, 3};
//        List myList = Arrays.asList(myArray);
//        System.out.println(myList.size());//1
//        System.out.println(myList.get(0));//数组地址值
////        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        int[] array = (int[]) myList.get(0);
//        System.out.println(array[0]);//1

        Integer[] myArray = {1, 2, 3};
        List myArray1 = List.of(myArray);
        System.out.println(myArray1);
    }
}
