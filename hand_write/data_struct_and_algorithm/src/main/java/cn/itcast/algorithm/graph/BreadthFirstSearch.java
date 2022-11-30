package cn.itcast.algorithm.graph;

import cn.itcast.algorithm.linear.Queue;

public class BreadthFirstSearch {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录有多少个顶点与s顶点相通
    private int count;
    //用来存储待搜索邻接表的点
    private Queue<Integer> waitSearch;

    //构造广度优先搜索对象，使用广度优先搜索找出G图中s顶点的所有相邻顶点
    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count=0;
        this.waitSearch = new Queue<Integer>();

        bfs(G,s);
    }

    //使用广度优先搜索找出G图中v顶点的所有相邻顶点
    private void bfs(Graph G, int v) {
        marked[v] = true;
        waitSearch.enqueue(v);
        while (!waitSearch.isEmpty()) {
            Integer dequeue = waitSearch.dequeue();
            for (Integer w : G.adj(dequeue)) {
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    waitSearch.enqueue(w);
                }
            }
        }
    }

    //判断w顶点与s顶点是否相通
    public boolean marked(int w) {
        return marked[w];
    }

    //获取与顶点s相通的所有顶点的总数
    public int count() {
        return count;
    }
}
