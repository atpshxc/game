package com.haowan.game;

import javax.swing.*;
import java.awt.*;

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

    public void start() {
        requestFocus();
        scoreLabel.setText(String.valueOf(score));
        draw(currentBox.getCells());
        drawPreview(nextBox.getCells());
        while (true) {
            Box box = currentBox;
            if (checkOver(box.getCells())) {
                JOptionPane.showMessageDialog(null, "Game Over!", ""
                        , JOptionPane.INFORMATION_MESSAGE, null);
                reset();
                return;
            }

            while (pause) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e1) {
            }
            if (canDrop(box.getCells())) {
                draw(box.drop());
            } else {
                addCells(box.getCells());
                currentBox = nextBox;
                nextBox = BoxFactory.next();
                drawPreview(nextBox.getCells());
            }
            calculateScore();
        }
    }

    private void reset() {
        for (Cell[] cells : table) {
            for (Cell cell : cells) {
                if (cell != null) {
                    cell.setRow(-100);
                }
            }
        }

        cleanPreview();

        table = new Cell[ROWS][COLS];
        pause = false;
        currentBox = BoxFactory.next();
        nextBox = BoxFactory.next();
    }

    private void draw(Cell[] cells) {
        for (Cell cell : cells) {
            add(cell);
            cell.updateUI();
        }
    }

    private void drawPreview(Cell[] cells) {
        cleanPreview();
        for (Cell cell : cells) {
            Cell preCell = new Cell(cell.getRow(), cell.getCol());
            preCell.setCol(cell.getCol() - 3);
            preCell.setRow(cell.getRow() + 1);
            previewPanel.add(preCell);
            preCell.updateUI();
        }
    }

    private void cleanPreview() {
        Component[] components = previewPanel.getComponents();
        for (Component c : components) {
            ((Cell) c).setRow(-100);
            ((Cell) c).updateUI();
        }
    }

    private void drawGrid(Graphics g) {
        for (int y = P_GAP; y < V_CELLS * CELL_SIZE; y += CELL_SIZE) {
            g.drawLine(P_GAP, y, LEFT_PANEL_WIDTH - P_GAP, y);
        }
        for (int x = P_GAP; x < H_CELLS * CELL_SIZE; x += CELL_SIZE) {
            g.drawLine(x, P_GAP, x, (V_CELLS - 2) * CELL_SIZE - P_GAP);
        }
    }

    public boolean checkOver(Cell[] cells) {
        for (Cell cell : cells) {
            if (table[cell.getRow()][cell.getCol()] != null) {
                return true;
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

    private void calculateScore() {
        int count = 0;
        int firstDelRow = -1;
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
                if (firstDelRow < 0) {
                    firstDelRow = i;
                }
                for (int j = 0; j < table[0].length; j++) {
                    Cell cell = table[i][j];
                    cell.setRow(-100);
                    table[i][j] = null;
                    cell.updateUI();
                }
                count++;
            }
        }
        adjustTable(firstDelRow, count);
        if (count > 0) {
            scoreLabel.setText(String.valueOf(calScore(count)));
        }
    }

    private int calScore(int count) {
        int s = 100;
        for (int i = 1; i < count; i++) {
            s *= 2;
        }
        score += s;
        return score;
    }

    private void adjustTable(int num, int count) {
        for (int i = num - 1; i >= 0; i--) {
            for (int j = 0; j < table[0].length; j++) {
                Cell cell = table[i][j];
                if (cell != null) {
                    cell.setRow(cell.getRow() + count);
                    table[i + count][j] = cell;
                    table[i][j] = null;
                }
            }
        }
    }

    private void addCells(Cell[] cells) {
        for (Cell cell : cells) {
            table[cell.getRow()][cell.getCol()] = cell;
        }
    }

    private boolean canDrop(Cell[] cells) {
        for (Cell cell : cells) {
            if ((cell.getRow() >= ROWS - 1
                    || table[cell.getRow() + 1][cell.getCol()] != null)) {
                return false;
            }
        }
        return true;
    }

    private boolean canLeft(Cell[] cells) {
        for (Cell cell : cells) {
            if ((cell.getCol() - 1 < 0
                    || table[cell.getRow()][cell.getCol() - 1] != null)) {
                return false;
            }
        }
        return true;
    }

    private boolean canRight(Cell[] cells) {
        for (Cell cell : cells) {
            if ((cell.getCol() >= COLS - 1
                    || table[cell.getRow()][cell.getCol() + 1] != null)) {
                return false;
            }
        }
        return true;
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

    public void processRight() {
        if (canRight(currentBox.getCells())) {
            currentBox.right();
        }
    }
}
