package com.haowan.game;

/*
 *   口
 * 口口
 * 口
 */
public class Z1Box extends Box {

    public Z1Box() {
        cells = new Cell[]{
            new Cell(0, 5),
            new Cell(1, 5),
            new Cell(1, 4),
            new Cell(2, 4)};
    }

    @Override
    public Box rotate(Cell[][] table) {
        ZBox box = new ZBox();
        box.cells[0].setCol(cells[2].getCol());
        box.cells[0].setRow(cells[2].getRow() - 1);
        box.cells[1].setCol(cells[0].getCol());
        box.cells[1].setRow(cells[0].getRow());
        box.cells[2].setCol(cells[1].getCol());
        box.cells[2].setRow(cells[1].getRow());
        box.cells[3].setCol(cells[1].getCol() + 1);
        box.cells[3].setRow(cells[1].getRow());
        for (Cell cell : box.getCells()) {
            if (!checkRowCol(table, cell.getRow(), cell.getCol())) {
                return null;
            }
        }
        return box;
    }
}
