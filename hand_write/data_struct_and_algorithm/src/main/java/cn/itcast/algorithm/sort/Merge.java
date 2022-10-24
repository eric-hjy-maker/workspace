package cn.itcast.algorithm.sort;

public class Merge {
    //归并所需要的辅助数组
    private static Comparable[] assist;

    /*
       比较v元素是否小于w元素
    */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w)<0;
    }

    /*
    数组元素i和j交换位置
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    /*
           对数组a中的元素进行排序
        */
    public static void sort(Comparable[] a) {
        assist = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if (lo >= hi){
            return;
        }
        // 之所以不用 （lo + hi）/2 是因为，lo + hi 有可能超过 int 的表示范围
        int mid = lo + (hi-lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int p1 = lo;
        int p2 = mid + 1;
        int p3 = lo;
        while (p1 <= mid && p2 <= hi){
            if (less(a[p1], a[p2])){
                assist[p3++] = a[p1++];
            }else {
                assist[p3++] = a[p2++];
            }
        }
        while (p1 <= mid){
            assist[p3++] = a[p1++];
        }
        while (p2 <= hi){
            assist[p3++] = a[p2++];
        }
        for (int i = lo; i <= hi; i++){
            a[i] = assist[i];
        }
    }
}

