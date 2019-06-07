package com.igoosd.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "t_flow_info_data")
public class FlowInfoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueKey;
    private Integer enterNum;
    private Integer exitNum;

    private Long parkingSpaceId;

    private String gatewayCode;

    private Date createTime;
}
