package cn.itcast.algorithm.sort;
//冒泡排序
public class Bubble {

    public static void sort_once(Comparable[] a, int lo, int hi) {
        for (int i = lo; i+1 <= hi; i++) {
            if (greater(a[i], a[i+1])){
                exch(a, i, i+1);
            }
        }
    }
    /*
       对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a){
        for(int i=a.length-1;i>0;i--){
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
