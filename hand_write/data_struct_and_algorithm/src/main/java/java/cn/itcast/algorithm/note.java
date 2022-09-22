package java.cn.itcast.algorithm;

public class note {
//• B站 主页地址：https://www.bilibili.com/video/BV1Cz411B7qd?from=search&seid=8751873717913591722
//
//   • 01_数据结构与算法概述_数据结构
//    数据结构：组织和存储数据的集合
//		○ 逻辑结构：集合、线形、树形、图形
//		○ 物理结构：顺序结构、链式结构
//	• 02_数据结构与算法概述_算法
//		○ 算法：根据条件对数据计算，得到需要的结果
//	• 03_算法分析_时间复杂度分析1
//		○ 程序运行时间主要取决于：（时间和规模）
//            § 1.算法采用的策略和方案（时间）
//            § 2.问题输入的规模（规模）
//      ○ 常数可以忽略
//		○ 常数因子可以忽略
//		○ 时间复杂度取决于最高次项
//	• 04_算法分析_时间复杂度分析2
//		○ 随着规模的增大，需要计算的次数
//	• 05_算法分析_时间复杂度分析3
//	• 06_算法分析_时间复杂度分析4
//		○ 大O计法
//		○ 常数记为1
//		○ 只保留最高阶项
//	• 07_算法分析_时间复杂度分析5
//		○ 对数阶：不管以多少为底，都记为logn。因为不同底，变化趋势差别不大
//	• 08_算法分析_时间复杂度分析6
//	• 09_算法分析_空间复杂度分析
//		○ 基本数据类型
//			§ Byte	Short	Int	Long
//			1	2	4	8
//                    § Float	Double
//			4	8
//    Boolean	Char
//			1	2
//      • 机器访问内存都是一次一个字节
//		• 引用（机器地址）需要8个字节表示
//		• 创建一个对象，对象头占16个字节，成员变量还需要空间，另外填充为8的倍数
//	• 10_排序_Comparable接口
//	• 11_排序_冒泡排序1 （n^2）
//      • 外层循环从最大长度减小，表示参与比较的元素个数
//		• 内层循环对参与比较的元素换顺序
//	• 12_排序_冒泡排序2
//	• 13_排序_冒泡排序3
//	• 14_排序_选择排序1 （n^2）
//      • 每次选出最小的数放到对应位置
//		• 假定第一个位置最小，和后边比较，如果有更小的，就交换位置
//		• 假定第二个位置最小，和后边比较，如果有更小的，就交换位置
//	• 15_排序_选择排序2
//	• 16_排序_选择排序3
//	• 17_排序_插入排序1（n^2）
//       • 现实模型，抓牌，插入
//	• 18_排序_插入排序2
//	• 19_排序_插入排序3
//	• 20_排序_希尔排序1
//		• 对插入排序的改良
//		• 按步长分组
//			§ 最大步长为n  2n+1<数组长度/2   n尽可能大
//			§ 后续步长依次除2
//		• 分组内按插入排序
//	• 21_排序_希尔排序2
//		• 对1000000 个数倒序，变正序
//		• 插入排序37499毫秒
//		• 希尔排序30毫秒
//	• 22_排序_希尔排序3
//	• 23_排序_递归
//	• 24_排序_归并排序1 O(nlogn)
//		• 思想：分组，分组排序，归并
//	• 25_排序_归并排序2
//		• 归并思路
//			§ 两个排好序的数组，一个临时数组，三个指针
//			§ 哪个数组小，放入临时数组，指针后移，临时指针也后移
//	• 26_排序_归并排序3
//	• 27_排序_归并排序4
//	• 28_排序_归并排序5
//	• 29_排序_归并排序6
//	• 30_排序_归并排序7
//	• 31_排序_归并排序8
//	• 32_排序_快速排序1
//		• 思想：分组，排序，归并
//	• 33_排序_快速排序2
//		• 最重要是找一个标记，让左边的数小于标记，右边的数大于标记
//		• 一个数组，两个指针从两头开始，左边的找比标记大的数，右边的找比标记小的数，交换
//	• 34_排序_快速排序3
//	• 35_排序_快速排序4
//	• 36_排序_快速排序5
//	• 37_排序_排序稳定性
//		• 数值相等的两个数，在排序前后，相对位置是否发生改变，如果AB还是AB则稳定，如果AB变BA则不稳定
//		• 算法	冒泡	选择	插入	希尔	归并	快速
//    是否稳定	稳定	不稳定	稳定	不稳定	稳定	不稳定
//	• 38_线性表_线性表概述
//		• 前驱元素 A在B前边，则A为B的前驱元素
//		• 后继元素 D在C后边，则D为C的后继元素
//	• 39_线性表_顺序表_基本实现
//	• 40_线性表_顺序表_测试
//	• 41_线性表_顺序表_遍历
//	• 42_线性表_顺序表_容量可变
//	• 43_线性表_顺序表_时间复杂度
//	• 44_线性表_顺序表_ArrayList源码
//	• 45_线性表_链表_概述
//	• 46_线性表_链表_单向链表1
//	• 47_线性表_链表_单向链表2
//	• 48_线性表_链表_单向链表3
//	• 49_线性表_链表_双向链表1
//	• 50_线性表_链表_双向链表2
//	• 51_线性表_链表_双向链表3
//	• 52_线性表_链表_双向链表4
//	• 53_线性表_链表_双向链表5
//	• 54_线性表_链表_双向链表_LinkeList源码
//	• 55_线性表_链表_时间复杂度分析
//	• 56_线性表_链表_单链表反转1
//	• 57_线性表_链表_单链表反转2
//	• 58_线性表_链表_快慢指针_中间值问题
//	• 59_线性表_链表_快慢指针_单链表是否有环问题
//		• 定义两个指针，慢指针，快指针
//		• 如果这两个指针能相遇，就是有环的
//	• 60_线性表_链表_快慢指针_有环链表入口问题
//		• 定义三个指针，慢指针，快指针，临时指针
//		• 快慢指针先走，相遇后
//		• 临时指针和慢指针，继续步长为1，当临时指针和慢指针相遇的时候就是环的入口
//	• 61_线性表_链表_循环链表
//	• 62_线性表_链表_约瑟夫问题1
//		• 39个犹太人，约瑟夫和他一个朋友，共41人
//		• 围成一圈，数到3的人自杀，接着从新开始数，再数到3的人自杀
//		• 约瑟夫和他的朋友 选了16和31的位置，避免了死亡
//	• 63_线性表_链表_约瑟夫问题2
//	• 64_线性表_链表_约瑟夫问题3
//	• 65_线性表_栈_概述
//	• 66_线性表_栈_代码实现1
//	• 67_线性表_栈_代码实现2
//	• 68_线性表_栈_案例_括号匹配问题1
//		• 遇到一个左括号就入栈
//		• 遇到一个右括号，左括号就出栈
//	• 69_线性表_栈_案例_括号匹配问题2
//	• 70_线性表_栈_案例_逆波兰表达式1
//		• 中缀表达式 1+3*2
//            • 后缀表达式：逆波兰表达式
//	• 71_线性表_栈_案例_逆波兰表达式2
//	• 72_线性表_队列1
//	• 73_线型表_队列2
//	• 74_符号表_概述
//		• 也就是Map
//	• 75_符号表_代码实现
//	• 76_符号表_测试
//	• 77_符号表_有序符号表实现
//	• 78_符号表_有序符号表测试
//	• 79_树_树的定义
//	• 80_树_树的相关术语
//		• 节点的度：一个节点含有的子树的个数为该节点的度
//		• 叶节点：度为0的节点
//		• 分支节点：度不为0的节点
//		• 节点的层次：根节点的层次为1，根的直接后继层次为2，依次类推
//		• 节点的层序编号：将树中的结点，按从上层到下层，同层从左到有的次序排成一个线性序列，把他们编成连续的自然数
//		• 树的度：树中所有节点度的最大值
//	• 81_树_二叉树定义
//		• 满二叉树：一个二叉树，如果每一层的结点都达到最大值，则这个二叉树就是满二叉树
//		• 完全二叉树：叶节点只能出现在最下层和次下层 ，并且最下面一层的节点都集中在该层最左边的若干位置的二叉树
//	• 82_树_二叉查找树创建_API设计
//	• 83_树_二叉查找树创建_插入方法
//	• 84_树_二叉查找树创建_获取方法
//	• 85_树_二叉查找树创建_删除方法
//	• 86_树_二叉查找树创建_测试
//	• 87_树_二叉查找树创建_查找最小键
//	• 88_树_二叉查找树创建_查找最大键
//	• 89_树_二叉树_遍历概述
//	• 90_树_二叉树_前序遍历
//	• 91_树_二叉树_中序遍历
//	• 92_树_二叉树_后序遍历
//	• 93_树_二叉树_层序遍历
//		• 一层一层遍历
//		• 实现步骤
//			§ 创建队列，存储每一层的结点
//			§ 判断是否有左右结点，没有就弹出，有就入队，并弹出
//	• 94_树_二叉树_最大深度问题
//	• 95_树_二叉树_折纸问题
//	• 96_堆_堆的概述
//		• 用数组实现，从1开始
//		• 如果一个结点的位置为k，则父节点位置为k/2
//            • 他的两个子节点位置为2k和2k+1
//            • 和二叉查找树的区别，每个结点>=两个子节点（子节点顺序没有规定）
//            • 97_堆_堆的API设计
//	• 98_堆_堆的插入
//	• 99_堆_堆的删除
//	• 100_堆_堆的测试
//	• 101_堆_堆排序1
//	• 102_堆_堆排序2
//	• 103_堆_堆排序3
//	• 104_堆_堆排序4
//	• 105_堆_堆排序5
//	• 106_优先队列_概述
//	• 107_优先队列_最大优先队列1
//		• 最大的放在最前边
//	• 108_优先队列_最大优先队列2
//	• 109_优先队列_最小优先队列1
//	• 110_优先队列_最小优先队列2
//	• 111_优先队列_最小优先队列3
//	• 112_优先队列_索引最小优先队列1
//	• 113_优先队列_索引最小优先队列2
//	• 114_优先队列_索引最小优先队列3
//	• 115_优先队列_索引最小优先队列4
//	• 116_优先队列_索引最小优先队列5
//	• 117_优先队列_索引最小优先队列6
//	• 118_优先队列_索引最小优先队列7
//	• 119_树_2-3查找树_概述
//		• 2-3查找树
//		• 含有一个结点的位置，左子树比他小，右子树比他大
//		• 含有两个结点的位置，左子树比最小的小，中子树位于这两个树范围内，右子树比最大的大
//	• 120_树_2-3查找树_查找
//	• 121_树_2-3查找树_插入
//	• 122_树_2-3查找树_性质
//	• 123_树_红黑树_概述
//		• 基于2-3树
//	• 124_树_红黑树_结点类设计
//	• 125_树_红黑树_平衡化
//		• 当某个结点的左子结点为黑色，右子结点为红色，此时需要左旋
//			§ 左旋过程
//		• 某个结点的左子结点为红色，且左子结点也是红色，需要右旋
//	• 126_树_红黑树_插入1
//	• 127_树_红黑树_插入2
//	• 128_树_红黑树_实现1
//	• 129_树_红黑树_实现2
//	• 130_树_红黑树_实现3
//	• 131_树_B树_概述
//		• B树允许包含多个key，可以是3、4、5等，我们选M=4，那么就是一个4阶B树
//		• 该树会有以下特点
//			§ 每个结点最多有M-1个key，并且升序排列
//			§ 每个结点最多能有M个子结点
//			§ 根节点至少有两个子节点
//		• 实际应用中，阶数都比较大，通常大于100，那么高度就很小
//	• 132_树_B树_插入
//	• 133_树_B树_磁盘文件应用
//	• 134_树_B+树_概述和插入
//		• B+树是堆B树的一种变形树，它与B树的差异在于
//			§ 非叶结点仅具有索引作用，也就是说非叶子结点只存储key，不存value
//			§ 树的所有叶子结点构成一个有序链表，可以按照key排序的次序遍历全部数据
//	• 135_树_B+树_数据库应用
//	• 136_树_并查集_概述
//		• 并查集是一种树形的数据结构，能够高效的进行如下操作
//			§ 查询元素p和元素q是否是同一组
//			§ 合并元素p和元素q所在组
//		• 特点
//			§ 每个元素都唯一对应一个结点
//			§ 每一组数据中的多个元素都在同一棵树中
//			§ 一组中的数据对应的树和另外一组中的数据对应的树之间没有任何联系
//			§ 元素在树中没有父级关系的硬性要求
//	• 137_树_并查集_实现
//	• 138_树_并查集_测试
//	• 139_树_并查集_优化
//		• 判断计算机是否联通
//	• 140_树_并查集_路径压缩
//	• 141_树_并查集_案例_畅通工程
//	• 142_图_概述
//		• 生活中的图：地图、电路图
//		• 图的分类
//			§ 按顶点的边的不同：分为无向图、有向图
//	• 143_图_图的相关术语
//		• 相邻顶点：当两个顶点通过一条边相连时，我们成这两个顶点是相邻的，并称这条边依附于这两个顶点
//		• 度：某个顶点的度就是依附于该顶点边的个数
//		• 子图：是一幅图所有边的子集（包含这些便依附的顶点）组成的图
//		• 路径：是由顺序练级的一些列顶点组成
//		• 环：是一条至少含有一条边且重点和起点相同的路径
//		• 连通图：如果任意一个顶点都存在一条路径到达另外一个顶点，那么这幅图就是连通图
//		• 联通子图：一个非连通图由若干联通的部分组成，每一个联通的部分都可以成为该图的连通子图
//	• 144_图_图的存储结构
//		• 要表示一幅图，只要表示清楚以下两部分就可以了：1：图中所有的顶点；2：所有连接顶点的边
//		• 常见的图的存储结构有两种：邻接矩阵和邻接表
//		• 邻接矩阵
//			§ 用索引表示顶点号，用存储数字是否为1表示，两个顶点之间是否有边
//		• 邻接表
//			§ 用索引表示顶点号，用后边的链表存储与该顶点相连的顶点
//	• 145_图_无向图实现
//	• 146_图_深度优先搜索_概述
//		• 遇到兄弟结点和子节点时，先找子节点
//	• 147_图_深度优先搜索_实现
//	• 148_图_深度优先搜索_测试
//	• 149_图_广度优先搜索_概述
//	• 150_图_广度优先搜索_实现
//	• 151_图_广度优先搜索_测试
//	• 152_图_案例_畅通工程续
//	• 153_图_路径查找_概述
//		• 路径，是由边顺序连接一些系列顶点组成
//	• 154_图_路径查找_实现1
//	• 155_图_路径查找_实现2
//	• 156_图_路径查找_测试
//	• 157_图_有向图_概述
//		• 相关术语
//			§ 出度：某个顶点指出边的个数
//			§ 入读：某个顶点指入边的个数
//			§ 有向路径：
//          § 有向环：一条至少含一条边，且起点和重点相同
//	• 158_图_有向图_实现
//	• 159_图_拓扑排序_概述
//		• 图变为一个顺序序列
//	• 160_图_拓扑排序_检测有向环_概述
//		• 检测有向图中的环，不能有环
//	• 161_图_拓扑排序_检测有向环_实现
//	• 162_图_拓扑排序_顶点排序
//	• 163_图_拓扑排序_实现
//	• 164_图_拓扑排序_测试
//	• 165_图_加权无向图_概述
//		• 给边加上长度或者其他，那么这个图就是加权图
//	• 166_图_加权无向图_加权边表示
//	• 167_图_加权无向图_实现
//	• 168_图_最小生成树_概述
//		• 连接每一个点，且路径最短
//	• 169_图_最小生成树_切分定理
//	• 170_图_最小生成树_贪心算法
//	• 171_图_最小生成树_prim算法概述
//	• 172_图_最小生成树_prim算法原理
//	• 173_图_最小生成树_prim算法实现1
//	• 174_图_最小生成树_prim算法实现2
//	• 175_图_最小生成树_prim算法测试
//	• 176_最小生成树_kruskal算法概述
//	• 177_最小生成树_kruskal算法原理
//	• 178_最小生成树_kruskal算法实现
//	• 179_最小生成树_kruskal算法测试
//	• 180_加权有向图_加权有向边表示
//	• 181_加权有向图_实现
//	• 182_最短路径_概述和api设计
//	• 183_最短路径_松弛技术
//	• 184_最短路径_Dijkstra算法实现1
//	• 185_最短路径_Dijkstra算法实现2
//	• 186_最短路径_Dijkstra算法测试
}
