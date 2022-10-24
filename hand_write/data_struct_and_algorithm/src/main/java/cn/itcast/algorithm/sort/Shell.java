package cn.itcast.algorithm.sort;

public class Shell {

    public static void sort_once(Comparable[] a, int hi, int h){
        for (int i = hi; i-h >= 0; i-=h) {
            if (greater(a[i-h], a[i])){
                exch(a, i-h, i);
            }
        }
    }
    /*
       对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a){
        int h = 1;
        while (h < a.length/2){
            h = 2*h + 1;
        }

        while (h>=1){
            for (int i = h; i < a.length; i++) {
                sort_once(a, i, h);
            }
            h = h / 2;
        }

    }

    /*
        比较v元素是否大于w元素
     */
    private static  boolean greater(Comparable v,Comparable w){
        return v.compareTo(w)>0;
    }

    /*
    数组元素i和j交换位置
     */
    private static void exch(Comparable[] a,int i,int j){
        Comparable temp;
        temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
