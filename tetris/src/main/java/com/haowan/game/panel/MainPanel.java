package com.haowan.game.panel;

import static com.haowan.game.Constant.*;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 名称: MainPanel.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 18:44.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 18:44.
 */
public class MainPanel extends JPanel {
    private int[][] filled = new int[H_CELLS][V_CELLS - 2];
    private volatile boolean pause;

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setFilled(DropDownPanel panel) {
        int x = panel.getX();
        int y = panel.getY();
        int[][] cells = panel.getCells();
        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[0].length; j++) {
                if (cells[i][j] == 1) {
                    int h = i + (x - P_GAP) / CELL_SIZE;
                    int v = j + (y - P_GAP) / CELL_SIZE;
                    filled[h][v] = 1;
                }
            }
        }
    }

    public void reset() {
        filled = new int[H_CELLS][V_CELLS - 2];
        for (int i=0; i< filled.length; i++) {
            filled[i][V_CELLS-3] = 1;
        }
    }

    public int getFilledValue(int h, int v) {
        return filled[h][v];
    }

    public MainPanel() {
        setLocation(0, 0);
        setSize(LEFT_PANEL_WIDTH, WIN_HEIGHT);
        setLayout(null);
        for (int i=0; i< filled.length; i++) {
            filled[i][V_CELLS-3] = 1;
        }
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

}
