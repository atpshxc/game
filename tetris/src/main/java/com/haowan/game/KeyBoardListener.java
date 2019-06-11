package com.haowan.game;

import com.haowan.game.panel.DropDownPanel;
import com.haowan.game.panel.MainPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.haowan.game.Constant.CELL_SIZE;
import static com.haowan.game.Constant.P_GAP;

public class KeyBoardListener implements KeyListener {
    private DropDownPanel dropDownPanel;
    private MainPanel mainPanel;

    public KeyBoardListener(DropDownPanel dropDownPanel, MainPanel mainPanel) {
        this.dropDownPanel = dropDownPanel;
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
            for (int i=dropDownPanel.getX(); i<dropDownPanel.getWidth() + dropDownPanel.getX(); i += CELL_SIZE) {
                if (mainPanel.getFilledValue((i-P_GAP)/CELL_SIZE, (dropDownPanel.getY()+dropDownPanel.getHeight()-P_GAP)/CELL_SIZE) == 1) {
                    return;
                }
            }
            dropDownPanel.setBounds(dropDownPanel.getX(), dropDownPanel.getY() + CELL_SIZE, dropDownPanel.getWidth(), dropDownPanel.getHeight());
        } else if (e.getKeyCode() == 37) {//left
            if (dropDownPanel.getX()<=P_GAP) return;
            int[][] cells = dropDownPanel.getCells();
            for (int i=0; i<cells[0].length; i++) {
                if (cells[0][i] == 1 && mainPanel.getFilledValue((dropDownPanel.getX()-P_GAP)/CELL_SIZE-1,(dropDownPanel.getY()-P_GAP)/CELL_SIZE) == 1) return;
            }
            dropDownPanel.setBounds(dropDownPanel.getX() - CELL_SIZE, dropDownPanel.getY(), dropDownPanel.getWidth(), dropDownPanel.getHeight());
        } else if (e.getKeyCode() == 39) {//right
            if (dropDownPanel.getX() >= mainPanel.getWidth() + P_GAP -dropDownPanel.getWidth() -CELL_SIZE) return;
            int[][] cells = dropDownPanel.getCells();
            for (int i=0; i<cells[0].length; i++) {
                if (cells[cells[0].length-1][i] == 1 && mainPanel.getFilledValue((dropDownPanel.getX()+dropDownPanel.getWidth()-P_GAP)/CELL_SIZE,(dropDownPanel.getY()-P_GAP)/CELL_SIZE) == 1) return;
            }
            dropDownPanel.setBounds(dropDownPanel.getX() + CELL_SIZE, dropDownPanel.getY(), dropDownPanel.getWidth(), dropDownPanel.getHeight());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
