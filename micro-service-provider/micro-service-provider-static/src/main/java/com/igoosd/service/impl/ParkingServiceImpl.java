package com.igoosd.service.impl;

import com.igoosd.domain.Parking;
import com.igoosd.repository.ParkingRepository;
import com.igoosd.repository.dao.ParkingDao;
import com.igoosd.service.ParkingService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingServiceImpl extends AbsCommonService<Parking, Long> implements ParkingService {


    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingDao parkingDao;


    @Override
    public Page<Parking> findPageList(Parking parking, Pageable pageable) {
        return parkingDao.findPage(parking, pageable);
    }


    @Override
    public int changeLotRemain(int exitNum, int enterNum, Long id) {
        return parkingRepository.changeLotRemain(exitNum, enterNum, id);
    }

    @Override
    public List<Parking> getParkingListByInducePlateId(Long inducePlateId) {
        return parkingDao.findParkingListByInducePlateId(inducePlateId);
    }

    @Override
    protected JpaRepository<Parking, Long> getRepository() {
        return this.parkingRepository;
    }
}
