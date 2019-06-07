package com.igoosd.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProgramVo {

    private Integer showType;

    private List<String> content;

    private Integer step;

    private Integer interval;

}
