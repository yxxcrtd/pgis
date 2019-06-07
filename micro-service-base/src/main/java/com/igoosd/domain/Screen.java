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
@Table(name = "t_screen")
@ToString
public class Screen{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 屏幕名称 **/
	private String name;
	
	/** 屏幕编码 **/
	private String code;
	
	/** 屏幕类型 1：车位数屏 2文字信息屏 **/
	private Integer type;
	
	/** 控制卡ID **/
	private Long controlCardId;
	
	/** 诱导牌ID **/
	private Long inducePlateId;

	/** 屏幕在诱导牌上显示的位置 **/
	private Integer orderNum;

	/** 屏宽 **/
	private Integer width;

	/** 屏高 **/
	private Integer height;
	
	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Transient
	private String inducePlateName;
	@Transient
	private String controlCardCode;

}

