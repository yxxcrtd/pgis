package com.igoosd.enumeration;

public enum ControlCardInitStatusEnum {


    SUCCESS(1, "成功"), FAIL(0, "失败"),;

    private int value;
    private String name;

    ControlCardInitStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ControlCardInitStatusEnum getEnumByValue(Integer value) {
        for (ControlCardInitStatusEnum myEnum : ControlCardInitStatusEnum.values()) {
            if (myEnum.getValue() == value) {
                return myEnum;
            }
        }
        return null;
    }
}
