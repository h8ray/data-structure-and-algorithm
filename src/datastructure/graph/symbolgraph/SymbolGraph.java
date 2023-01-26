package datastructure.graph.symbolgraph;

import datastructure.graph.AbstractGraph;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 符号图
 */
public class SymbolGraph {
    /**
     * 符号名 → 索引
     */
    private Map<String, Integer> st;

    /**
     * 索引 → 符号名
     */
    private String[] keys;

    /**
     * 图
     */
    private AbstractGraph graph;

    /**
     * @param fileName  文件名
     * @param delimiter 每行顶点的分隔符
     * @param clazz     有向图或无向图
     * @return
     */
    public SymbolGraph(String fileName, String delimiter, Class<? extends AbstractGraph> clazz) {
        st = new HashMap<>();
        String line = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            //构造索引
            while ((line = reader.readLine()) != null) {
                //读取字符串，以分隔符delimter分隔
                String[] a = line.split(delimiter);

                //为每个不同的字符串关联一个索引
                for (int i = 0; i < a.length; i++) {
                    if (!st.containsKey(a[i])) {
                        st.put(a[i], st.size());
                    }
                }
            }

            //获得顶点名的反向索引
            keys = new String[st.size()];
            for (String name : st.keySet()) {
                keys[st.get(name)] = name;
            }

            //构造图
            //graph = new Undigraph(st.size());
            Constructor<? extends AbstractGraph> con = clazz.getConstructor(int.class);
            graph = con.newInstance(st.size());
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while ((line = reader.readLine()) != null) {
                //读取字符串，以分隔符delimter分隔
                String[] a = line.split(delimiter);
                int v = st.get(a[0]);

                for (int i = 1; i < a.length; i++) {
                    this.graph.addEdge(v, st.get(a[i]));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String s) {
        return st.containsKey(s);
    }

    public Integer index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        if (v < 0 || v >= keys.length) {
            return null;
        }
        return keys[v];
    }

    public AbstractGraph getGraph() {
        return graph;
    }

//    public static void test(Class<? extends Graph> clazz) throws NoSuchMethodException {
//        Constructor<?> constructor = clazz.getConstructor(int.class);
//        Object o = constructor.newInstance();
//    }
//
//    public static void main(String[] args) throws NoSuchMethodException {
//        test(Digraph.class);
//    }

}
