package com.igoosd.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 接口日志
 */
@Data
@Entity
@Table(name = "t_log")
public class Log {

    /** 主键Id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 用户名 */
    private String username;

    /** 请求URL */
    private String url;

    /** HTTP请求方法 */
    private String httpMethod;

    /** 请求IP地址 */
    private String ip;

    /** 请求类 */
    private String classMethod;

    /** 请求参数 */
    private String args;

    /** 相应数据 */
    private String response;

    /** 相应时间（单位：毫秒） */
    private long spendTime;

    /** 创建时间 */
    private Date createTime = new Date();

}
