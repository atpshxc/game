package com.haowan.game;

import static com.haowan.game.GameFrame.LEFT_PANEL_WIDTH;
import static com.haowan.game.GameFrame.WIN_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 名称: GamePanel.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 18:44.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 18:44.
 */
public class GamePanel extends JPanel {
    private int[] filled = new int[LEFT_PANEL_WIDTH/20];

    public void setFilled(int index, int value) {
        filled[index] = value;
    }

    public int getFilledValue(int index) {
        return filled[index];
    }

    public GamePanel() {
//        setBackground(Color.GREEN);
        setLocation(0, 0);
        setSize(LEFT_PANEL_WIDTH, WIN_HEIGHT);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, LEFT_PANEL_WIDTH, WIN_HEIGHT);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int y = 0; y < WIN_HEIGHT; y += 20) {
            g.drawLine(0, y, LEFT_PANEL_WIDTH, y);
        }
        for (int x = 0; x < LEFT_PANEL_WIDTH; x += 20) {
            g.drawLine(x, 0, x, WIN_HEIGHT);
        }
    }

}
