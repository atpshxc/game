package com.haowan.game;

/**
 * 口口口口
 */
public class HBox extends Box {

    public HBox() {
        cells = new Cell[]{
            new Cell(0, 4),
            new Cell(0, 5),
            new Cell(0, 6),
            new Cell(0, 7)};
    }

    @Override
    public Box rotate(Cell[][] table) {
        Cell first = cells[0];
        Box box = new VBox();
        int i = 0;
        for (Cell cell : box.getCells()) {
            int row = first.getRow() - i++;
            int col = first.getCol();
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
