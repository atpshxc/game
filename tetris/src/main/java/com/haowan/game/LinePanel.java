package com.haowan.game;

import static com.haowan.game.GameFrame.LEFT_PANEL_WIDTH;
import static com.haowan.game.GameFrame.WIN_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 名称: LinePanel.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 23:04.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 23:04.
 */
public class LinePanel extends JPanel {
    private int x = 100;
    private int y = 0;
    private int width = 4 * 20;
    private int height = 1 * 20;

    public void setY(int y) {
        this.y = y;
    }

    public LinePanel() {
        setBackground(Color.BLUE);
        init();
    }

    public void init() {
        setBounds(x, y, width,height);
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.drawRect(0, 0, LEFT_PANEL_WIDTH, WIN_HEIGHT);
////        drawGrid(g);
//    }
}
