package com.igoosd.enumeration;

public enum  ColorTypeEnum {

    RED(1, "红色"), GREEN(2, "绿色"),YELLOW(3,"黄色");

    private int value;
    private String name;

    ColorTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ColorTypeEnum getEnumByValue(Integer value) {
        for (ColorTypeEnum myEnum : ColorTypeEnum.values()) {
            if (myEnum.getValue() == value) {
                return myEnum;
            }
        }
        return null;
    }
}
