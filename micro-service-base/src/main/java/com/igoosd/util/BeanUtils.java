package com.igoosd.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class BeanUtils {


    private static Mapper mapper = new DozerBeanMapper();

    public static <S,T> T map(S src,Class<T> t){
       return  mapper.map(src,t);
    }
}
