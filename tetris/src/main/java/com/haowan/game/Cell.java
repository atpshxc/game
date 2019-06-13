package com.haowan.game;

import static com.haowan.game.Constant.CELL_SIZE;
import static com.haowan.game.Constant.P_GAP;

import java.awt.Color;
import javax.swing.JPanel;

public class Cell extends JPanel {
    private int row;
    private int col;
    private int[] rgb;
    private String text;
    private String image = "square.png";

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        setBackground(Color.BLACK);
        setBounds(P_GAP + col * CELL_SIZE, P_GAP + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        setBounds(P_GAP + col * CELL_SIZE, P_GAP + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
        setBounds(P_GAP + col * CELL_SIZE, P_GAP + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
