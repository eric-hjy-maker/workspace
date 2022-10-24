package cn.itcast.algorithm.sort;

public class Selection {


    public static void sort_once(Comparable[] a, int lo, int hi){
        for (int i = lo; i <= hi; i++) {
            if (greater(a[lo], a[i])){
                exch(a, lo, i);
            }
        }
    }
    /*
       对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a){
        // 外层循环控制要排序的数组范围
        for(int i=0;i<=a.length-2;i++){
            // 对数组进行一次排序
            sort_once(a, i, a.length-1);
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
