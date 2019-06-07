package com.igoosd.http.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RealTimeVo {

    private String _type;
    private String _id;
    private String timestamp;
    private String desc;//描述
}
