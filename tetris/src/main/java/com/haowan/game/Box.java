package com.haowan.game;

public class Box {
    int index;
    Cell[][] cells;
    public Cell[][] getCells() {
        return cells;
    }

    public Cell[][] drop() {
        for (int i=0; i< cells.length; i++) {
            for (int j=0; j<cells[0].length;j++) {
                Cell cell = cells[i][j];
                if (cell != null) {
                    cell.setRow(cell.getRow() + 1);
                }
            }
        }
        return cells;
    }

    public Cell[][] left() {
        for (int i=0; i< cells.length; i++) {
            for (int j=0; j<cells[0].length;j++) {
                Cell cell = cells[i][j];
                if (cell != null) {
                    cell.setCol(cell.getCol() - 1);
                }
            }
        }
        return cells;
    }

    public Cell[][] right() {
        for (int i=0; i< cells.length; i++) {
            for (int j=0; j<cells[0].length;j++) {
                Cell cell = cells[i][j];
                if (cell != null) {
                    cell.setCol(cell.getCol() + 1);
                }
            }
        }
        return cells;
    }
}
