package com.igoosd.enumeration;

public enum NetworkModeEnum {

    NET_3G(1,"3G"),NET_4G(2,"4G");

    private int value;
    private String name;

    NetworkModeEnum(int value, String name) {
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
