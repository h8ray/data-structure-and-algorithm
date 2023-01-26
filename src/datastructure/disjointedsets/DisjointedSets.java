package datastructure.disjointedsets;

/**
 * 并查集（不相交集类）
 * 下述中，【 集合编号 == 集合的根 == s[root]<0的root 】 这3个概念是等价的
 */
public class DisjointedSets {
    private int[] s;

    public DisjointedSets(int numElements) {
        s = new int[numElements];
        for (int i = 0; i < s.length; i++) {
            s[i] = -1;
        }
    }


    /**
     * 将元素x1和元素x2所在的集合合并
     */
    public void union(int x1, int x2) {
        if (isConnect(x1, x2)) {
            return;
        }
        int root1 = find(x1);
        int root2 = find(x2);
        unionRoot(root1, root2);
    }

    /**
     * 合并根为root1和root2的两个集合
     * 这里默认root1和root2是不同集合的根结点
     */
    private void unionRoot(int root1, int root2) {
        // 1.任意合并
        // s[root2] = root1;

        // 2.按大小合并
        // 根为root的集合大小（集合中的元素数）为-s[root]
       /* if (s[root2] < s[root1]) {//root2集合更大
            s[root2] += s[root1];//root1并入root2
            s[root1] = root2;
        } else {//root1集合更大或和root2一样大
            s[root1] += s[root2];//root2并入root1
            s[root2] = root1;
        }*/

        // 3.按高度（秩）合并
        // 根为root的高度为-s[root]
        if (s[root2] < s[root1]) {
            //root2更高
            //让root2成为新的root（root1并入root2）
            s[root1] = root2;
        } else {//root1高于或等于root2
            if (s[root1] == s[root2]) {
                //高度相等
                //让root1高度增加1
                s[root1]--;
            }
            //让root1成为新的root（root2并入root1）
            s[root2] = root1;
        }
    }

    /**
     * 返回元素x所在的集合编号
     */
    public int find(int x) {
        if (s[x] < 0) {
            //找到集合的根
            return x;
        } else {
            // 路径压缩
            // 会使x和x到根之间的元素都压缩1层（除了根的直接儿子）
            // 比如 4->3->2->1，1是根，使用find(4)会变成 4->2->1以及3->1
            return s[x] = find(s[x]);
        }
    }

    /**
     * 判断元素x1和x2是否在同一个集合中
     */
    public boolean isConnect(int x1, int x2) {
        return find(x1) == find(x2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append("s[").append(i).append("] = ").append(s[i]).append("\t");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DisjointedSets uf = new DisjointedSets(8);
        System.out.println(uf);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(4, 6);
        System.out.println(uf);
    }
}
