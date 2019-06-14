package com.haowan.game;

/*
 * 口
 * 口
 * 口
 * 口
 */
public class VBox extends Box {

    public VBox() {
        cells = new Cell[]{
            new Cell(0, 4),
            new Cell(1, 4),
            new Cell(2, 4),
            new Cell(3, 4)
        };
    }

    @Override
    public Box rotate(Cell[][] table) {
        Cell last = cells[cells.length - 1];
        Box box = new HBox();
        int i = 0;
        for (Cell cell : box.getCells()) {
            int row = last.getRow();
            int col = last.getCol() + i++;
            if (checkRowCol(table, row, col)) {
                cell.setRow(row);
                cell.setCol(col);
            } else {
                return null;
            }
        }
        return box;
    }
}
