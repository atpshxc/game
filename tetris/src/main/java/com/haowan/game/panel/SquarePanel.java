package com.haowan.game.panel;

import static com.haowan.game.Constant.CELL_SIZE;
import static com.haowan.game.Constant.LEFT_PANEL_WIDTH;

/**
 * 名称: HLinePanel.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 23:04.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 23:04.
 */
public class SquarePanel extends DropDownPanel {
    private int width = 2 * CELL_SIZE;
    private int height = 2 * CELL_SIZE;

    public SquarePanel() {
        init(0);
    }

    public void init(int y) {
        setBounds(getCenterAlignX(), y, width, height);
    }

    private int getCenterAlignX() {
        return Math.round(((float) LEFT_PANEL_WIDTH - width) / 2 / CELL_SIZE) * CELL_SIZE;
    }
}
