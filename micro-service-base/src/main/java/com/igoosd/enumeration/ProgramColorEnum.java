package com.igoosd.enumeration;

public enum ProgramColorEnum {

    RED(1, "red"), GREEN(2, "green"), YELLOW(3, "yellow");

    private int value;
    private String name;

    ProgramColorEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ProgramColorEnum getEnumByValue(Integer value) {
        for (ProgramColorEnum myEnum : ProgramColorEnum.values()) {
            if (myEnum.getValue() == value) {
                return myEnum;
            }
        }
        return null;
    }
}
