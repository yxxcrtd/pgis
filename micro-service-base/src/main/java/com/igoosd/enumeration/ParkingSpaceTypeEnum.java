package com.igoosd.enumeration;

public enum ParkingSpaceTypeEnum {


    INDOOR(1, "室内"), OUTDOOR(2, "室外"), LANE_OCCUPY(3, "占道"), MACHINE(4, "机械");


    private int value;
    private String name;

    ParkingSpaceTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ParkingSpaceTypeEnum getEnumByValue(Integer value){
        for (ParkingSpaceTypeEnum myEnum :ParkingSpaceTypeEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
