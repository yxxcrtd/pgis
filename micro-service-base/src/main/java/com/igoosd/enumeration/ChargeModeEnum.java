package com.igoosd.enumeration;

/**
 * 收费模式枚举
 */
public enum ChargeModeEnum {


    FREE(1,"免费"),CHARGE(2,"收费");


    private int value;
    private String name;

    ChargeModeEnum(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ChargeModeEnum getEnumByValue(Integer value){
        for (ChargeModeEnum chargeModeEnum :ChargeModeEnum.values()){
            if(chargeModeEnum.getValue() == value){
                return chargeModeEnum;
            }
        }
        return null;
    }
}
