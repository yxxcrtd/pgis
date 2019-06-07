/**
 * @单位名称：安徽谷声科技有限公司
 *            IGOOSD Technology Co.,Ltd.
 * 		      Copyright (c) 2017 All Rights Reserved.
 * @系统名称：路泊收费系统
 * @工程名称：后台服务
 * @文件名称: 
 * @类路径: 
 */

package com.igoosd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @see
 *
 * @date	2017-10-30 15:10:50 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
@Data
@Entity
@Table(name = "t_repeater")
@ToString
public class Repeater{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 编码 **/
	private String code;
	
	/** 网关ID **/
	private Long gatewayId;
	
	/** 设备状态 1：正常 1异常 **/
	private Integer deviceStatus;
	
	/** 描述 **/
	private String remark;
	
	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	@Transient
	private String gatewayCode;

}

