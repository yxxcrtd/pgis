package com.igoosd.repository;

import com.igoosd.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ParkingRepository extends JpaRepository<Parking,Long>, JpaSpecificationExecutor<Parking> {


    /**
     * 更新剩余车位数
     * @param exitNum
     * @param enterNum
     * @param parkingSpaceId
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Parking set lotRemainCount = lotRemainCount + :exitNum - :enterNum where id=:id and (lotTotalCount - lotRemainCount - :exitNum + :enterNum) >= 0")
    int changeLotRemain(@Param("exitNum") int exitNum, @Param("enterNum") int enterNum, @Param("id") Long parkingSpaceId);

}
