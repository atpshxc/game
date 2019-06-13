package com.haowan.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.haowan.game.Constant.*;

public class TablePanel extends JPanel {
    private JPanel previewPanel;
    private JLabel scoreLabel;
    private int score;

    private static final int P_GAP = 10;
    private Cell[][] table = new Cell[ROWS][COLS];
    private Box currentBox = BoxFactory.next();
    private Box nextBox = BoxFactory.next();
    private boolean pause;

    public TablePanel(JPanel previewPanel, JLabel scoreLabel) {
        this.previewPanel = previewPanel;
        this.scoreLabel = scoreLabel;
        setLocation(0, 0);
        setSize(LEFT_PANEL_WIDTH, WIN_HEIGHT);
        setLayout(null);
        addKeyListener(new KeyBoardListener(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, LEFT_PANEL_WIDTH, WIN_HEIGHT);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int y = P_GAP; y < V_CELLS * CELL_SIZE; y += CELL_SIZE) {
            g.drawLine(P_GAP, y, LEFT_PANEL_WIDTH - P_GAP, y);
        }
        for (int x = P_GAP; x < H_CELLS * CELL_SIZE; x += CELL_SIZE) {
            g.drawLine(x, P_GAP, x, (V_CELLS - 2) * CELL_SIZE - P_GAP);
        }
    }

    public boolean checkOver(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null && table[cell.getRow()][cell.getCol()] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void start() {
        requestFocus();
        scoreLabel.setText(String.valueOf(score));
        draw(currentBox.getCells());
        nextBox = BoxFactory.next();
        while (true) {
            Box box = currentBox;
            drawPreview(nextBox);
            if (checkOver(box.getCells())) {
                JOptionPane.showMessageDialog(null, "Game Over!", ""
                        , JOptionPane.INFORMATION_MESSAGE, null);
                return;
            }

            while (pause) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
            }
            if (canDrop(box.getCells())) {
                clean(box.getCells());
                draw(box.drop());
            } else {
                addCells(box.getCells());
                clean(box.getCells());
                currentBox = nextBox;
                nextBox = BoxFactory.next();
            }
            calculateScore();
            draw(table);
        }
    }

    private void calculateScore() {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            boolean b = true;
            for (int j = 0; j < table[0].length; j++) {
                Cell cell = table[i][j];
                if (cell == null) {
                    b = false;
                    break;
                }
            }
            if (b) {
                table[i] = new Cell[COLS];
                adjustTable(i);
                count++;
            }
        }
        if (count > 0) {
            scoreLabel.setText(String.valueOf(calScore(count)));
        }
    }

    private void adjustTable(int num) {
        for (int i = num - 1; i >=0; i--) {
            for (int j = 0; j < table[0].length; j++) {
                Cell cell = table[i][j];
                if (cell != null) {
                    table[i + 1][j] = cell;
                    table[i][j] = null;
                }
            }
        }
    }

    private int calScore(int count) {
        int score = 100;
        for (int i = 1; i < count; i++) {
            score *= 2;
        }
        return score;
    }

    private void drawPreview(Box nextBox) {
        Graphics g = previewPanel.getGraphics();
        g.translate(-(WIN_WIDTH - LEFT_PANEL_WIDTH)/2 + 20, 20);
        draw(nextBox.getCells(), g);

    }

    private void addCells(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null) {
                    table[cell.getRow()][cell.getCol()] = cell;
                }
            }
        }
    }

    private boolean canDrop(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null && (cell.getRow() >= ROWS - 1 || table[cell.getRow() + 1][cell.getCol()] != null)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void draw(Cell[][] cells) {
        Graphics g = getGraphics();
        g.translate(P_GAP, P_GAP);
        draw(cells, g);
    }

    private void draw(Cell[][] cells, Graphics g) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null) {
                    try {
                        BufferedImage img = ImageIO.read(new File(TablePanel.class.getClassLoader().getResource(cell.getImage()).getFile()));
                        g.drawImage(img, cell.getCol() * CELL_SIZE, cell.getRow() * CELL_SIZE, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void clean(Cell[][] cells) {
        Graphics g = getGraphics();
//        for (int i = 0; i < cells.length; i++) {
//            for (int j = 0; j < cells[0].length; j++) {
//                Cell cell = cells[i][j];
//                if (cell != null) {
//                    g.clearRect(cell.getCol() * CELL_SIZE, cell.getRow() * CELL_SIZE, 4 * CELL_SIZE, CELL_SIZE * 4);
//                }
//            }
//        }
        g.clearRect(0, 0, COLS * CELL_SIZE + CELL_SIZE, CELL_SIZE * ROWS + CELL_SIZE);
        drawGrid(g);
    }

    public void processDown() {
        if (canDrop(currentBox.getCells())) {
            currentBox.drop();
        }
    }

    public void processLeft() {
        if (canLeft(currentBox.getCells())) {
            currentBox.left();
        }
    }

    private boolean canLeft(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null && (cell.getCol() - 1 < 0 || table[cell.getRow()][cell.getCol() - 1] != null)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void processRight() {
        if (canRight(currentBox.getCells())) {
            currentBox.right();
        }
    }

    private boolean canRight(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell cell = cells[i][j];
                if (cell != null && (cell.getCol() >= COLS - 1 || table[cell.getRow()][cell.getCol() +1] != null)) {
                    return false;
                }
            }
        }
        return true;
    }
}
