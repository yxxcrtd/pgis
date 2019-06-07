/**
 * @单位名称：安徽谷声科技有限公司
 *            IGOOSD Technology Co.,Ltd.
 * 		      Copyright (c) 2017 All Rights Reserved.
 * @系统名称：路泊收费系统
 * @工程名称：后台服务
 * @文件名称: 
 * @类路径: 
 */

package com.igoosd.repository;

import com.igoosd.domain.ProgramScreen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @see
 *
 * @date	2017-10-30 15:10:49 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
public interface ProgramScreenRepository extends JpaRepository<ProgramScreen, Long>, JpaSpecificationExecutor<ProgramScreen>{
	
    
}




