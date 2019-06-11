package com.haowan.game;

import static com.haowan.game.Constant.BUTTON_PAUSE;
import static com.haowan.game.Constant.BUTTON_START;
import static com.haowan.game.Constant.CELL_SIZE;
import static com.haowan.game.Constant.P_GAP;
import static com.haowan.game.Constant.V_CELLS;

import com.haowan.game.panel.HLinePanel;
import com.haowan.game.panel.MainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
public class StartPauseButtonListener implements ActionListener {
    private MainPanel mainPanel;

    public StartPauseButtonListener(MainPanel mainPanel) {
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
            mainPanel.repaint();
            running = true;
            HLinePanel linePanel = new HLinePanel(mainPanel);
            int maxHeight = 0;
            while (true) {
                if (maxHeight >= V_CELLS-3) {
                    JOptionPane
                        .showMessageDialog(null, "Game Over!", "", JOptionPane.INFORMATION_MESSAGE,
                            null);
                    running = false;
                    button.setText(BUTTON_START);
                    mainPanel.setPause(false);
                    mainPanel.reset();
                    mainPanel.removeAll();
                    break;
                }
                mainPanel.add(linePanel);
                linePanel.requestFocus();
                while (mainPanel.isPause()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e1) {
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                }
                boolean isNew = false;
                for (int i=linePanel.getX(); i<linePanel.getWidth() + linePanel.getX(); i += CELL_SIZE) {
                    if (mainPanel.getFilledValue((i-P_GAP)/CELL_SIZE, (linePanel.getY()+linePanel.getHeight()-P_GAP)/CELL_SIZE) == 1) {
                        maxHeight += linePanel.getHeight()/CELL_SIZE;
                        mainPanel.setFilled(linePanel);

                        linePanel = new HLinePanel(mainPanel);
                        isNew = true;
                        mainPanel.repaint();
                        break;
                    }
                }
                if (!isNew) {
                    linePanel.setBounds(linePanel.getX(), linePanel.getY() + CELL_SIZE, linePanel.getWidth(), linePanel.getHeight());
                }
            }
        });

    }
}
