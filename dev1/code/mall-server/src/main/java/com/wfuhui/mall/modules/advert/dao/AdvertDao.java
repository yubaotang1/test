package com.wfuhui.mall.modules.advert.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.advert.entity.AdvertEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;


@Mapper
public interface AdvertDao extends BaseDao<AdvertEntity> {
	
}
