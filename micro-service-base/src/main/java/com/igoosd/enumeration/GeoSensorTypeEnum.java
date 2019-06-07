package com.igoosd.enumeration;

public enum GeoSensorTypeEnum {

    FLOW(1,"流量地磁"),BERTH(2,"泊位地磁");

    private int value;
    private String name;

    GeoSensorTypeEnum(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static GeoSensorTypeEnum getEnumByValue(Integer value){
        for (GeoSensorTypeEnum myEnum :GeoSensorTypeEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
