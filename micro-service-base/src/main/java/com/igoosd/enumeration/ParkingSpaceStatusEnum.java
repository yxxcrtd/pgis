package com.igoosd.enumeration;

public enum ParkingSpaceStatusEnum {

    ENABLE(1,"启用"),DISABLE(0,"禁用"),;

    private int value;
    private String name;

    ParkingSpaceStatusEnum(int value,String name){
        this.value = value;
        this.name = name;
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ParkingSpaceStatusEnum getEnumByValue(Integer value){
        for (ParkingSpaceStatusEnum myEnum :ParkingSpaceStatusEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
