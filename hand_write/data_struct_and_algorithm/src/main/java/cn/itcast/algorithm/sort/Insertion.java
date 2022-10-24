package cn.itcast.algorithm.sort;

public class Insertion {

    public static void sort_once(Comparable[] a, int lo, int hi){
        for (int i = hi; i-1 >= lo; i--) {
            if (greater(a[i-1], a[i])){
                exch(a, i-1, i);
            }else {
                break;
            }
        }
    }
    /*
       对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a){
        for(int i=1;i<a.length;i++){
            sort_once(a, 0, i);
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
