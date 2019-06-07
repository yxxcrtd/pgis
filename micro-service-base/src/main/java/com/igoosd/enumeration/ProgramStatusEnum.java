package com.igoosd.enumeration;

/**
 * 节目状态 0 待审核 -1审核失败 1待发布（审核成功） -2发布失败 2 发布成功
 */
public enum ProgramStatusEnum {
    RELEASE_FAIL(-2,"发布失败"),VERIFY_FAIL(-1, "审核失败"),DRAFT(0, "草稿"), WAITING_VERIFY(1, "待审核"), RELEASE_SUCCESS(2, "已发布"),;

    private int value;
    private String name;

    ProgramStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ProgramStatusEnum getEnumByValue(Integer value){
        for (ProgramStatusEnum myEnum : ProgramStatusEnum.values()){
            if(myEnum.getValue() == value){
                return myEnum;
            }
        }
        return null;
    }
}
