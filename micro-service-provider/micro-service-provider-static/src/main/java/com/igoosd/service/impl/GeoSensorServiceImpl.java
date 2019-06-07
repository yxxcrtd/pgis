package com.igoosd.service.impl;

import com.igoosd.domain.GeoSensor;
import com.igoosd.repository.GeoSensorRepository;
import com.igoosd.repository.dao.GeoSensorDao;
import com.igoosd.service.GeoSensorService;
import com.igoosd.util.AbsCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GeoSensorServiceImpl extends AbsCommonService<GeoSensor, Long> implements GeoSensorService {


    @Autowired
    private GeoSensorRepository geoSensorRepository;
    @Autowired
    private GeoSensorDao geoSensorDao;

    @Override
    public Page<GeoSensor> findPageList(GeoSensor sensor, Pageable pageable) {
        return geoSensorDao.findPage(sensor,pageable);
    }

    @Override
    protected JpaRepository<GeoSensor, Long> getRepository() {
        return this.geoSensorRepository;
    }
}
