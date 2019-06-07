package com.igoosd.enumeration;


public enum ScreenTypeEnum {

    LOT_COUNT(1,"车位数展示"),MEDIA(2,"多媒体展示"),;

    private int value;
    private String name;

    ScreenTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ScreenTypeEnum getEnumByValue(Integer value){
        for (ScreenTypeEnum myEnum : ScreenTypeEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
