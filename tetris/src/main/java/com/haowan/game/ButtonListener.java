package com.haowan.game;

import static com.haowan.game.Constant.BUTTON_PAUSE;
import static com.haowan.game.Constant.BUTTON_START;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JPanel;

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
            running = true;
            mainPanel.start();
            button.setText(BUTTON_START);
            running = false;
        });

    }
}
