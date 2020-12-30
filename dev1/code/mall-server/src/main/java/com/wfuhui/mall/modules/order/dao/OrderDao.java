package com.wfuhui.mall.modules.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.order.entity.OrderEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;

/**
 * 订单
 */
@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

	void updateByOrderNumber(OrderEntity order);
	
}
