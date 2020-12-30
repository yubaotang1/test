package com.wfuhui.mall.modules.goods.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.goods.entity.GoodsEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;

/**
 * 商品
 */
@Mapper
public interface GoodsDao extends BaseDao<GoodsEntity> {

}
