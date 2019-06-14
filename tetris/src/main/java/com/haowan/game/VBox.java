package com.haowan.game;

/**
 * 口口口口
 */
public class VBox extends Box {
    private Box[] box = {};

    public VBox() {
        cells = new Cell[]{
                new Cell(0, 4),
                new Cell(1, 4),
                new Cell(2, 4),
                new Cell(3, 4)
        };
    }

    public Box rotate() {
        return box[index++ % box.length];
    }
}
