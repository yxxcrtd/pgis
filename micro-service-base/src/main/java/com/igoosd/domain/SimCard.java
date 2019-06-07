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

import lombok.Data;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * @see
 *
 * @date	2017-10-30 15:22:34 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
@Data
@Entity
@Table(name = "t_sim_card")
@ToString
public class SimCard{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 手机号 **/
	private String phoneNumber;
	
	/** 电信运营商类型 1：移动 2联通 3电信 **/
	private Long operatorType;
	
	/** 网络模式 **/
	private Integer networkMode;
	
	/** 设备状态 1：正常 0 异常 **/
	private Integer deviceStatus;
	
	/** 描述 **/
	private String remark;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

}

