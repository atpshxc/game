package com.haowan.game;

/**
 * 口口口口
 */
public class HBox extends Box {
    private Box[] box = {};

    public HBox() {
        cells = new Cell[]{
                new Cell(0, 4),
                new Cell(0, 5),
                new Cell(0, 6),
                new Cell(0, 7)};
    }

    public Box rotate() {
        return box[index++ % box.length];
    }
}
