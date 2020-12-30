package com.wfuhui.mall.modules.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.order.entity.OrderGoodsEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;


@Mapper
public interface OrderGoodsDao extends BaseDao<OrderGoodsEntity> {
	
}
