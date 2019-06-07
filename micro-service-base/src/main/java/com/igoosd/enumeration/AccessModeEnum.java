package com.igoosd.enumeration;

/**
 * 停车场接入模式
 */
public enum AccessModeEnum {

    GEO_SENSOR(1,"地磁"),SYSTEM(2,"系统");

    private int value;
    private String name;

    AccessModeEnum(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public static AccessModeEnum getEnumByValue(Integer value){
        for (AccessModeEnum accessModeEnum :AccessModeEnum.values()){
            if(accessModeEnum.getValue() == value){
                return accessModeEnum;
            }
        }
        return null;
    }
}
