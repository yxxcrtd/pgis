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
@Table(name = "t_parking")
@ToString
public class Parking{
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 名称 **/
	private String name;
	
	/** 编码 **/
	private String code;
	
	/** 类型 1：室内 2室外 3占道 4 机械 **/
	private Integer type;
	
	/** 接入方式 **/
	private Integer accessMode;
	
	/** 车位总数 **/
	private Integer lotTotalCount;
	
	/** 剩余车位数 **/
	private Integer lotRemainCount;
	
	/** 创建时间 **/
	private String remark;
	
	/** 位置 **/
	private String location;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 所属单位名称
	 */
	private String unitName;

	/**
	 * 关联 文字信息屏ID
	 */
	private Long mediaScreenId;

	@Transient
	private String mediaScreenName;
}

