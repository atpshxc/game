package com.haowan.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Nine {
    private int[][] table;
    private Stack<Cell> stack;

    public Nine() {
        stack = new Stack<>();
        table = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
    }

    public boolean calc() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == 0) {
                    Stack<Integer> availableVal = getAvailableVal(table, i, j);
                    if (availableVal.size() == 1) {
                        return false;
                    }
                    stack.push(new Cell(availableVal));
                    Cell cell = stack.peek();
                    Integer v;
                    while ((v = cell.values.pop()) != null) {
                        table[i][j] = v;
                        if (!calc()) {
                            table[i][j] = 0;
                            continue;
                        }
                        break;
                    }
                    stack.pop();
                    if (table[i][j] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Stack<Integer> getAvailableVal(int[][] table, int i, int j) {
        List<Integer> list = new ArrayList<>();
        //横向已有的值
        for (int k = 0; k < 9; k++) {
            if (table[i][k] != 0) {
                list.add(table[i][k]);
            }
        }
        //纵向已有的值
        for (int k = 0; k < 9; k++) {
            if (table[k][j] != 0) {
                list.add(table[k][j]);
            }
        }
        //定位i,j所属的小3宫格起始位置
        for (int t = i; t <= 9; t++) {
            if (t % 3 == 0) {
                if (t > 0 && t > i) {
                    i = t - 3;
                }
                break;
            }
        }
        for (int t = j; t <= 9; t++) {
            if (t % 3 == 0) {
                if (t > 0 && t > j) {
                    j = t - 3;
                }
                break;
            }
        }

        //获取i,j所属小3宫格的已有值
        for (int ii = i; ii < i + 3; ii++) {
            for (int jj = j; jj < j + 3; jj++) {
                if (table[ii][jj] != 0) {
                    list.add(table[ii][jj]);
                }
            }
        }
        List<Integer> all = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        all.removeAll(list);
        Stack<Integer> result = new Stack<>();
        result.push(null);
        for (Integer val : all) {
            result.push(val);
        }
        return result;
    }

    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            if (!isValid(table[i])) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            int[] values = new int[9];
            for (int j = 0; j < 9; j++) {
                values[j] = table[j][i];
            }
            if (!isValid(values)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                List<Integer> list = new ArrayList<>();
                for (int ii = 0; ii < 3; ii++) {
                    for (int jj = 0; jj < 3; jj++) {
                        list.add(table[i + ii][j + jj]);
                    }
                }
                int[] values = new int[9];
                IntStream.range(0, 9).forEach(v -> values[v] = list.get(v));
                if (!isValid(values)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[] values) {
        int[] ints = Arrays.copyOf(values, values.length);
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            if ((i + 1) != ints[i]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    class Cell {
        private Stack<Integer> values;

        public Cell(Stack<Integer> availableVal) {
            values = availableVal;
        }
    }

    public static void main(String[] args) {
        Nine nine = new Nine();
        nine.calc();
        System.out.println(nine.isValid());
        System.out.println(nine);
    }
}
