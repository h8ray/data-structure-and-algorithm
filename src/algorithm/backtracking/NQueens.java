package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NQueens {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (char[] chars : chess) {
            Arrays.fill(chars, '.');
        }
        backtrace(chess, 0);
        return ans;
    }

    private void backtrace(char[][] chess, int row) {
        //终止条件，row是从0开始的，最后一行都可以放，说明成功了
        if (row == chess.length) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < chess.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < chess[i].length; j++) {
                    sb.append(chess[i][j]);
                }
                list.add(sb.toString());
            }
            ans.add(list);
            return;
        }

        for (int col = 0; col < chess.length; col++) {
            //验证当前位置是否可以放皇后
            if (valid(chess, row, col)) {
                //如果在当前位置放一个皇后不冲突，就在当前位置放一个皇后
                chess[row][col] = 'Q';

                //递归，下一行
                backtrace(chess, row + 1);

                //回溯撤销当前位置的皇后
                chess[row][col] = '.';
            }
        }

    }

    private boolean valid(char[][] chess, int row, int col) {
        //判断该在chess[row][col]上方的同列中有没有其他皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }

        //判断左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        //判断右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess[i].length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> ans = new NQueens().solveNQueens(n);
        System.out.println(ans);
    }
}