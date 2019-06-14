package com.haowan.game;

public class Box {
    int index;
    Cell[] cells;
    public Cell[] getCells() {
        return cells;
    }

    public Cell[] drop() {
        for (Cell cell : cells) {
            cell.setRow(cell.getRow() + 1);
        }
        return cells;
    }

    public Cell[] left() {
        for (Cell cell : cells) {
            cell.setCol(cell.getCol() - 1);
        }
        return cells;
    }

    public Cell[] right() {
        for (Cell cell : cells) {
            cell.setCol(cell.getCol() + 1);
        }
        return cells;
    }

    public Box rotate(Cell[][] table) {
        return null;
    }

    boolean checkRowCol(Cell[][] table, int row, int col) {
        return row >= 0 && row < table.length && col >= 0 && col < table[0].length
            && table[row][col] == null;
    }

    void copyRowCol(Box box) {
        for (int i=0; i<cells.length; i++) {
            box.cells[i].setRow(cells[i].getRow());
            box.cells[i].setCol(cells[i].getCol());
        }
    }
}
