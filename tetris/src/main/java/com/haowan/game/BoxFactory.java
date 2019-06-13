package com.haowan.game;

import java.util.Random;

public class BoxFactory {
    private static final Random random = new Random(47);

    public static Box next() {
        int i = random.nextInt(11);
        switch (i) {
            case 0:
                return new HBox();
            case 1:
                return new VBox();
        }
        return new HBox();
    }
}
