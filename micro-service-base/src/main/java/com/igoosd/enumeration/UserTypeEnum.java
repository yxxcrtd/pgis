package com.igoosd.enumeration;

/**
 * 用户类型枚举
 */
public enum UserTypeEnum {


    SUPER_ADMIN(1,"超级管理员"),PARKING_SPACE_ADMIN(2,"停车场管理员"),;

    private int value;
    private String name;

    UserTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static UserTypeEnum getEnumByValue(Integer value){
        for (UserTypeEnum myEnum :UserTypeEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
