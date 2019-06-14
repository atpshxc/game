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
            case 2:
                return new SBox();
            case 3:
                return new TBox();
            case 4:
                return new T1Box();
            case 5:
                return new T2Box();
            case 6:
                return new T3Box();
            case 7:
                return new ZBox();
            case 8:
                return new Z1Box();
            case 9:
                return new Z2Box();
            case 10:
                return new Z3Box();
        }
        return new HBox();
    }
}
