package io.github.donggi.reminder.enums;

import java.util.Map;

import io.github.donggi.reminder.util.EnumUtil;

public enum CommonFlag implements EnumValue {

    /** 꺼짐 */
    OFF(0),
    /** 켜짐 */
    ON(1),;

    private final int value;
    private static final Map<Integer, CommonFlag> MAP = EnumUtil.asMap(OFF);


    public static CommonFlag valueOf(int value) {
        return MAP.get(value);
    }

    public static CommonFlag valueOf(boolean value) {
        return value? ON : OFF;
    }


    private CommonFlag(int value) {
        this.value = value;
    }

    public boolean isOn() {
        return this == ON;
    }

    public boolean isTrue() {
        return this == ON;
    }

    @Override
    public int getValue() {
        return value;
    }

}
