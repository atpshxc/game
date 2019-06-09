package com.haowan.game;

import static com.haowan.game.Constant.BUTTON_START;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
    public static final int WIN_WIDTH = 410;
    public static final int WIN_HEIGHT = 510;
    public static final int LEFT_PANEL_WIDTH = 260;

    public GameFrame() {
        this.setVisible(true);
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setTitle("Tetris");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        GamePanel mainPanel = new GamePanel();
        mainPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel rightPanel = new JPanel();
        rightPanel.setLocation(LEFT_PANEL_WIDTH, 0);
        rightPanel.setSize(WIN_WIDTH-LEFT_PANEL_WIDTH, WIN_HEIGHT);
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(null);
        previewPanel.setBounds(0,0,WIN_WIDTH-LEFT_PANEL_WIDTH,100);
        previewPanel.setBorder(BorderFactory.createEtchedBorder());

        JPanel scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createEtchedBorder());
        scorePanel.setLayout(null);
        scorePanel.setBounds(0, 100, WIN_WIDTH-LEFT_PANEL_WIDTH, 200);
        JLabel scoreLabel = new JLabel("Score: ");
        scoreLabel.setBounds(0,50,40,40);
        JLabel score= new JLabel("0");
        score.setBounds(40,50,100,40);
        scorePanel.add(scoreLabel);
        scorePanel.add(score);

        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEtchedBorder());
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 200, WIN_WIDTH-LEFT_PANEL_WIDTH, 300);
        JButton start = new JButton(BUTTON_START);
        start.setBounds(30, 160, 80, 40);
        start.addActionListener(new StartAction(mainPanel));
        menuPanel.add(start);

        rightPanel.add(previewPanel);
        rightPanel.add(scorePanel);
        rightPanel.add(menuPanel);

        Container container = this.getContentPane();
        container.setLayout(null);
        container.add(mainPanel);
        container.add(rightPanel);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
