package com.haowan.game.panel;

import javax.swing.*;

public class DropDownPanel extends JPanel {
    int [][] cells;

    public void initCells(int x, int y) {
        cells = new int[x][y];
    }

    public int[][] getCells() {
        return cells;
    }
}
