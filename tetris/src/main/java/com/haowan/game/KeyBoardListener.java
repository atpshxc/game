package com.haowan.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    private TablePanel mainPanel;

    public KeyBoardListener(TablePanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (mainPanel.isPause()) {
            return;
        }
        if (e.getKeyCode() == 40) {//down
            mainPanel.processDown();
        } else if (e.getKeyCode() == 37) {//left
            mainPanel.processLeft();
        } else if (e.getKeyCode() == 39) {//right
            mainPanel.processRight();
        } else if ( e.getKeyCode() == 32) {//空格
            mainPanel.rotate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
