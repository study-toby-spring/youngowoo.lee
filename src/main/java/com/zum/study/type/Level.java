package com.zum.study.type;

import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created by Joeylee on 2017-09-13.
 */
public enum  Level {

    GOLD(3, null),
    SILVER(2, GOLD),
    BASIC(1, SILVER);

    private int level;
    private Level next;

    Level(int level, Level next) {
        this.level = level;
        this.next = next;
    }

    public int intValue() {
        return level;
    }

    public Level next() {
        return next;
    }

    public static Level valueOf(int level) {

        switch (level) {

            case 1: return BASIC;
            case 2: return SILVER;
            case 3: return GOLD;

            default: throw new InvalidStateException("Unknown value : " + level);
        }
    }
}
