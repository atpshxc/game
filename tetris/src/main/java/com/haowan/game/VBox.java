package com.haowan.game;

/**
 * 口口口口
 */
public class VBox extends Box {
    private Box[] box = {};

    public VBox() {
        cells = new Cell[][]{
                new Cell[]{new Cell(0, 4), null, null, null},
                new Cell[]{new Cell(1, 4), null, null, null},
                new Cell[]{new Cell(2, 4), null, null, null},
                new Cell[]{new Cell(3, 4), null, null, null},
        };
    }

    public Box rotate() {
        return box[index++ % box.length];
    }
}
