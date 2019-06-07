package com.igoosd.enumeration;

/**
 * 运营商枚举
 */
public enum OperatorTypeEnum {

    CHINA_MOBILE(1,"中国移动"),CHINA_UNICOM(2,"中国联通"),CHINA_TELECOM(3,"中国电信");

    private int value;
    private String name;

    OperatorTypeEnum(int value, String name) {
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
