package com.jgma.xcode.func;

/**
 * @Author: admin
 */
public class BubbleFun {

    public static void main(String[] args) {

        int[] array = new int[]{3, 1, 2, 5, 4, 8, 6, 45, 21, 0, 36, 56, 210, 254, 489, 1475, 124, 365, 88, 99};
        int[] array2 = new int[]{3, 1, 2, 5, 4, 8, 6, 45, 21, 0, 36, 56, 210, 254, 489, 1475, 124, 365, 88, 99};

        bubbleUp(array);
        bubbleDown(array2);

    }

    private static void bubbleUp(int[] array) {
        long l = System.currentTimeMillis();
        // 遍历需要冒泡排序的数组，第一轮循环最小的在最前面
        for (int i = 0; i < array.length - 1; i++) {
            // 用此值与剩下的所有值比较
            for (int j = i +1; j < array.length; j++) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 如果这个值大就把它放到最后面(沉底)
                if (array[i] > array[j]) {
                    // 替换值
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l));
        for (int i : array) {
            System.out.println(i);
        }
    }

    private static void bubbleDown(int[] array) {
        long l = System.currentTimeMillis();
        // 遍历需要冒泡排序的数组，第一轮循环最小的在最前面
        for (int i = 0; i < array.length - 1; i++) {
            // 用此值与剩下的所有值比较
            for (int j = 0; j < array.length -i -1; j++) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 如果这个值大就把它放到最后面(沉底)
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j]  = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l));
        for (int i : array) {
            System.out.println(i);
        }
    }

}
