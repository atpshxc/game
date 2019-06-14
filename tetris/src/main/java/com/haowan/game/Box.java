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
}
