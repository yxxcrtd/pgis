package com.igoosd.domain.vo;

import lombok.Data;

@Data
public class LotRemainVo {

    private String code;

    private Integer carNum;//剩余车位数

    private Long timestamp;//时间戳
}
