package com.haowan.game;

import com.haowan.game.panel.HLinePanel;
import com.haowan.game.panel.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.haowan.game.Constant.*;

/**
 * 名称: StartPauseButtonListener.java <br>
 * 描述: <br>
 * 类型: JAVA <br>
 * 最近修改时间:2019/6/9 22:07.<br>
 *
 * @author HONGXINCHEN
 * @version [版本号, V1.0]
 * @since 2019/6/9 22:07.
 */
public class ButtonListener implements ActionListener {
    private TablePanel mainPanel;
    private JPanel scorePanel;

    public ButtonListener(TablePanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private volatile boolean running;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public void actionPerformed(final ActionEvent e) {
        executorService.execute(() -> {
            JButton button = (JButton) e.getSource();
            String text = button.getText();
            if (text.equals(BUTTON_START)) {
                button.setText(BUTTON_PAUSE);
                mainPanel.setPause(false);
                if (running) {
                    return;
                }
            } else {
                button.setText(BUTTON_START);
                mainPanel.setPause(true);
                return;
            }
//            mainPanel.repaint();
            running = true;
            mainPanel.start();
        });

    }
}
