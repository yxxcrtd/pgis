package com.igoosd.enumeration;

/**
 * 设备状态美剧
 */
public enum DeviceStatusEnum {

    NORMAL(1,"正常"),UNUSUAL(2,"异常");


    private int value;
    private String name;

    DeviceStatusEnum(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static DeviceStatusEnum getEnumByValue(Integer value){
        for (DeviceStatusEnum myEnum :DeviceStatusEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
