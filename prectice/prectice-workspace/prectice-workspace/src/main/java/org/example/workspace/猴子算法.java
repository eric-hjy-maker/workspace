package org.example.workspace;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

@Slf4j
public class 猴子算法 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] a = new int[]{1, 4, 6, 10, 5, 7, 8, 9, 11, 12, 13};
        monkeySort(a);
        log.info(String.format("总计耗时：%s", System.currentTimeMillis() - start));
    }

    private static final Random r = new Random();

    public static void monkeySort(int[] a){
        int count = 0;
        boolean isOrdered = false;
        while (!isOrdered) {
            isOrdered = true;
            for (int i = 0; i+1 < a.length; i++) {
                if (great(a, i, i+1)) {
                    isOrdered = false;
                    int j = r.nextInt(a.length);
                    exchange(a, i, j);
                }
            }
            log.info(String.format("%s------%s", Arrays.toString(a), ++count));
        }
    }

    public static boolean great(int[] a, int i, int j) {
        return a[i] > a[j];
    }

    public static void exchange(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
