package com.haowan.game;

import static com.haowan.game.Constant.BUTTON_START;
import static com.haowan.game.Constant.CELL_SIZE;
import static com.haowan.game.Constant.LEFT_PANEL_WIDTH;
import static com.haowan.game.Constant.RIGHT_PANEL_WIDTH;
import static com.haowan.game.Constant.WIN_HEIGHT;
import static com.haowan.game.Constant.WIN_WIDTH;

import java.awt.Color;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * 名称: GameFrame.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 17:11.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 17:11.
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        this.setVisible(true);
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setTitle("Tetris");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        JPanel rightPanel = new JPanel();
        rightPanel.setLocation(LEFT_PANEL_WIDTH, 0);
        rightPanel.setSize(RIGHT_PANEL_WIDTH, WIN_HEIGHT);
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(null);
        previewPanel.setBounds(0, 0, RIGHT_PANEL_WIDTH, 100);
        previewPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createEtchedBorder());
        scorePanel.setLayout(null);
        scorePanel.setBounds(0, 100, RIGHT_PANEL_WIDTH, 200);
        JLabel scoreLabel = new JLabel("Score: ");
        scoreLabel.setBounds(0, 50, 40, 40);
        JLabel score = new JLabel("0");
        score.setBounds(40, 50, 100, 40);
        scorePanel.add(scoreLabel);
        scorePanel.add(score);

        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEtchedBorder());
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 200, RIGHT_PANEL_WIDTH, 300);

        TablePanel mainPanel = new TablePanel(previewPanel, score);
        mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 35, 0, Color.WHITE));

        JButton start = new JButton(BUTTON_START);
        start.setBounds(30, 160, 80, 40);
        start.addActionListener(new ButtonListener(mainPanel));
        menuPanel.add(start);

        rightPanel.add(previewPanel);
        rightPanel.add(scorePanel);
        rightPanel.add(menuPanel);

        Container container = this.getContentPane();
        container.setLayout(null);
//        JMenu classic = new JMenu("经典");
//        classic.setBounds(0,0,100,50);
//        JMenu numeric = new JMenu("数字");
//        numeric.setBounds(120,0, 100, 50);
//        container.add(classic);
//        container.add(numeric);
        container.add(mainPanel);
        container.add(rightPanel);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
