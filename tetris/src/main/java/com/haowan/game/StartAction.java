package com.haowan.game;

import static com.haowan.game.Constant.BUTTON_PAUSE;
import static com.haowan.game.Constant.BUTTON_START;
import static com.haowan.game.GameFrame.LEFT_PANEL_WIDTH;
import static com.haowan.game.GameFrame.WIN_HEIGHT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 名称: StartAction.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 22:07.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 22:07.
 */
public class StartAction implements ActionListener {
    private GamePanel mainPanel;

    public StartAction(GamePanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private volatile boolean pause;
    private volatile boolean running;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public void actionPerformed(final ActionEvent e) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                JButton button = (JButton) e.getSource();
                String text = button.getText();
                if (text.equals(BUTTON_START)) {
                    button.setText(BUTTON_PAUSE);
                    pause = false;
                    if (running) return;
                } else {
                    button.setText(BUTTON_START);
                    pause = true;
                    return;
                }
                running = true;
                int y = 0;
                LinePanel linePanel = new LinePanel();
                while (true) {
                    mainPanel.add(linePanel);
                    mainPanel.repaint();
                    while (pause) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                    }
                    y += 20;
                    for (int i=0; i<LEFT_PANEL_WIDTH/20; i++) {
                        if (y + mainPanel.getFilledValue(i) >= WIN_HEIGHT - 2 * 20) {
                            mainPanel.setFilled(i, mainPanel.getFilledValue(i) + linePanel.getHeight());
                            y = 0;
                            linePanel = new LinePanel();
                            continue;
                        }
                    }
                    linePanel.setY(y);
                    linePanel.init();
                    mainPanel.repaint();
                }
            }
        });

    }
}
