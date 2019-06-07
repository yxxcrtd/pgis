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
 * @date	2017-10-30 15:10:49 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
@Data
@Entity
@Table(name = "t_program")
@ToString
public class Program{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 名称 **/
	private String name;
	
	/** 节目内容 文本 **/
	private String content;
	
	/** 创建节目人 **/
	private Long userId;
	
	/** 节目状态 0 待审核 -1审核失败 1待发布（审核成功） -2发布失败 2 发布成功 **/
	private Integer status;
	
	/** 发布时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date releaseTime;
	
	/**  描述 **/
	private String remark;
	
	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 修改时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 显示效果 0：单屏静态显示 1：单行滚动显示 3 多屏轮播 **/
	private Integer showType;

	/** 滚动的时间间隔 单位 ms**/
	private Integer  interval;

	/** 每次滚动移动的像素个数 **/
	private Integer step;

	private Integer  width;

	private Integer height;

	@Transient
	private String username;

}

