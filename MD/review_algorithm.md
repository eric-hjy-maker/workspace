##### 纬创-蔚来一面
```java
class Solution {
    public static void main(String[] args) {
        question1(1000);
        question2();
        question3();
    }
    public static void question1(int num) {
        //题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.编程     找出1000以内的所有完数。
        for (int i = 0; i < num; i++) {
            int sum = 1;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (i == sum) {
                System.out.println(i);
            }
        }
    }

    public static void question2() {
        // 题目：判断101-200之间有多少个素数，并输出所有素数。
        for (int i = 101; i < 200; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
            }
        }
    }

    public static void question3() {
        // 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在    第10次落地时，共经过多少米？第10次反弹多高？
        int count = 10;
        double hight = 100.0;
        double sum = hight;
        for (int i = 0; i < count; i++) {
            if (i < count - 1) {
                sum += hight;
            }
            hight = hight / 2;
        }
        System.out.println("第十次反弹h=" + hight);
        System.out.println("第十次落地sum=" + sum);
    }
}
```
##### 纬创-蔚来二面
```java
class Solution {
    public static void main(String[] args) {
        /* 非 0 的数按原来的顺序放在前边，0放到最后边
        输入：nums = [5,1,0,1,0,2]
        输出：[5,1,1,2,0,0]*/
        int[] a = {5, 1, 1, 2, 0, 0};
        int p1 = 0;
        int p2 = 0;
        while (p2 < a.length) {
            if (a[p2 != 0]) {
                exchange(a, p1, p2);
                p1++;
            }
            p2++;
        }
    }
    public static void exchange(int[] a, int p1, int p2) {
        int temp = a[p1];
        a[p1] = a[p2];
        a[p2] = temp;
    }
}
```

##### 睿企一面
```java
class Solution {
    public static void question1(String[] args) {
        /* ### 字符串加法
        需要有完整的思路和代码。
        输入两个长度不等的字符串（可能会超过long的最大值），计算两个字符串的数值和，不用 int long bigDecimal
        输入：`"123456789012345"`,`"9876543210123445"`
        输出：`"9999999999135790"`*/
        String s1 = "123456789012345";
        String s2 = "9876543210123445";

        int c = s1.length() - s2.length();
        if (c < 0) {
            for (int i = 0; i < -c; i++) {
                s1 = "0" + s1;
            }
        } else {
            for (int i = 0; i < c; i++) {
                s2 = "0" + s2;
            }
        }
        int up = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length() - 1; i >= 0; i--) {
            int i1 = s1.charAt(i) - '0';
            int i2 = s2.charAt(i) - '0';
            int res = i1 + i2;
            int cur = res % 10;
            sb.append(cur + up);
            up = res / 10;
        }
        System.out.println(sb.reverse());
    }
    public static void question2(String[] args) {
        /*### 数组组成数字的最小值
        输入 长度为n的数组，输出数组中元素拼接之后的最小值
        输入：{3,32,31,34}
        输出：3132334*/
        int[] nums = new int[]{3,32,31,34};
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        System.out.println(res);
    }
}
```
##### 白龙马云行科技
```java
import java.util.Stack;

class Solution {

    public static void main(String[] args) {
        // 二叉树中序遍历
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t4.left = t2;
        t2.left = t1;
        t2.right = t3;
        t4.right = t5;
        t5.right = t6;
        midTravel_stack(t4);
    }
    class LinkNode {
        int v;
        LinkNode next;

    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public void midTravel(TreeNode root) {
        // 递归方式中序遍历二叉树
        if (root == null) {
            return;
        }
        midTravel(root.left);
        System.out.println(root);
        midTravel(root.right);
    }
    public static void midTravel_stack(TreeNode root) {
        // 栈方式中序遍历二叉树
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()) {
                p = stack.pop();
                System.out.println(p.val);
                p = p.right;
            }
        }
    }
    class SingleLinkedList {
        LinkNode head;
        LinkNode end;
        int N;

        public void insertHead(LinkNode n) {
            n.next = head;
            this.head = n;
            N++;
        }
        public void insertEnd(LinkNode n) {
            end.next = n;
            this.end = n;
            N++;
        }
        public void delete(int n) {
            if (n < 0 || n > N) {
                return;
            }
            LinkNode pre = head;
            for (int i = 0; i < n - 1; i++) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            N--;
        }
        public void  deleteHead() {
            if (N > 0) {
                this.head = this.head.next;
                N--;
            }
        }
        public void deleteEnd() {
            if (N == 0) {
                return;
            }
            if (this.end == this.head) {
                this.head = null;
                this.end = null;
                N--;
                return;
            }
            LinkNode pre = this.head;
            while (pre.next != this.end) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            N--;
        }

        public boolean haveCycle(){
            LinkNode slow = this.head;
            LinkNode fast = this.head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }
}
```