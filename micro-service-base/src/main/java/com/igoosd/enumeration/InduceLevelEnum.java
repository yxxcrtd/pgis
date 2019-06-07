package com.igoosd.enumeration;

public enum InduceLevelEnum {

    LEVEL_1(1, "一级诱导牌"), LEVEL_2(2, "二级诱导牌"), LEVEL_3(3, "三级诱导牌"), LEVEL_4(4, "全点阵LED信息屏");

    private int value;
    private String name;

    InduceLevelEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static InduceLevelEnum getEnumByValue(Integer value) {
        for (InduceLevelEnum myEnum : InduceLevelEnum.values()) {
            if (myEnum.getValue() == value) {
                return myEnum;
            }
        }
        return null;
    }
}
