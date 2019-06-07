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
import java.util.Date;

/**
 * @see
 *
 * @date	2017-10-30 15:10:48 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
@Data
@Entity
@Table(name = "t_induce_plate")
@ToString
public class InducePlate{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 名称 **/
	private String name;
	
	/** 编码 **/
	private String code;
	
	/** 等级 1：一级诱导牌 2二级诱导牌 3 三级诱导牌 **/
	private Integer level;
	
	/** 屏幕名称 x,y **/
	private String location;
	
	/** 描述 **/
	private String remark;
	
	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;



}

