package datastructure.tree;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PriorityQueue;


/******************************************************************************
 *  Compilation:  javac Huffman.java
 *  Execution:    java Huffman - < input.txt   (compress)
 *  Execution:    java Huffman + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   https://algs4.cs.princeton.edu/55compression/abra.txt
 *                https://algs4.cs.princeton.edu/55compression/tinytinyTale.txt
 *                https://algs4.cs.princeton.edu/55compression/medTale.txt
 *                https://algs4.cs.princeton.edu/55compression/tale.txt
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  % java Huffman - < abra.txt | java BinaryDump 60
 *  010100000100101000100010010000110100001101010100101010000100
 *  000000000000000000000000000110001111100101101000111110010100
 *  120 bits
 *
 *  % java Huffman - < abra.txt | java Huffman +
 *  ABRACADABRA!
 *
 ******************************************************************************/

/**
 * The {@code Huffman} class provides static methods for compressing
 * and expanding a binary input using Huffman codes over the 8-bit extended
 * ASCII alphabet.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/55compression">Section 5.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Huffman {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private Huffman() {
    }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * @param s 输入的要编码的字符串
     * @return 该字符串的huffman编码
     */
    public static String compress(String s) {
        char[] input = s.toCharArray();

        // 计算s中每个字符出现的次数
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        // build Huffman trie
        Node root = buildTrie(freq);

        // build code table
        String[] st = new String[R];
        buildCode(st, root, "");

        // print trie for decoder
        writeTrie(root, new StringBuffer());

        // print number of bytes in original uncompressed message
        System.out.println(root.freq);
        System.out.println("input length = " + input.length);

        // use Huffman code to encode input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            char ch = input[i];
            String code = st[ch];
            sb.append(code);
        }

        return sb.toString();
    }

    /**
     * 根据每个字母出现的次数，建立huffman字典树
     *
     * @param freq 每个字母出现的次数
     * @return huffman字典树
     */
    private static Node buildTrie(int[] freq) {

        // initialze priority queue with singleton trees
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                //有出现的字符才能加入到优先队列中
                pq.offer(new Node(c, freq[c], null, null));
            }
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.offer(parent);
        }

        return pq.poll();
    }

    /**
     * 解析huffman字典树，对每个字符进行编码，编码保存到编码表中
     *
     * @param st 编码表
     * @param x  字典树
     * @param s  记录每条路径上的编码
     */
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left, s + '0');
            buildCode(st, x.right, s + '1');
        } else {
            st[x.ch] = s;
        }
    }

    /**
     * 输出各字符的huffman编码
     */
    private static void writeTrie(Node x, StringBuffer s) {
        if (x.isLeaf()) {
            System.out.println(x.ch + "\t" + s);
            return;
        }
        s.append(0);
        writeTrie(x.left, s);
        s.deleteCharAt(s.length() - 1);
        s.append(1);
        writeTrie(x.right, s);
        s.deleteCharAt(s.length() - 1);
    }


    public static void main(String[] args) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("resources\\tinytinyTale.txt"));
        StringBuilder input = new StringBuilder();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            input.append(new String(buf, 0, len));
        }
        System.out.println("input string is: \n" + input.toString());
        String compress = compress(input.toString());
        System.out.println("after compress: \n" + compress);
    }

}

