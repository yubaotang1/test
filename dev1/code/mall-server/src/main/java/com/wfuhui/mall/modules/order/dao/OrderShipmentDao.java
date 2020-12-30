package com.wfuhui.mall.modules.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.order.entity.OrderShipmentEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;

/**
 * 订单配送表
 */
@Mapper
public interface OrderShipmentDao extends BaseDao<OrderShipmentEntity> {

	OrderShipmentEntity queryByOrderId(Integer orderId);
	
}
