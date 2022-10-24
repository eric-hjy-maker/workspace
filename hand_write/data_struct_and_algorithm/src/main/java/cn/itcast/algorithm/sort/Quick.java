package cn.itcast.algorithm.sort;

public class Quick {
    /*
      比较v元素是否小于w元素
   */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }



    /*
   数组元素i和j交换位置
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //对数组内的元素进行排序
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if (lo >= hi){
            return;
        }
        int mid = partation(a, lo, hi);
        sort(a, lo, mid-1);
        sort(a, mid+1, hi);
    }

    public static int partation(Comparable[] a, int lo, int hi){
        Comparable key = a[lo];
        int left = lo;
        int right = hi + 1;
        while (true){
            while (less(a[++left], key) && left < hi){
            }
            while (less(key, a[--right]) && right > lo){
            }
            if (left < right){
                exch(a, left, right);
            }else {
                break;
            }
        }
        exch(a, lo, right);
        return right;
    }

}

