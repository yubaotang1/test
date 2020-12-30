package com.wfuhui.mall.modules.goods.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.goods.entity.GoodsPicEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;

/**
 * 商品图片
 */
@Mapper
public interface GoodsPicDao extends BaseDao<GoodsPicEntity> {

	String[] queryByGoodsId(Integer goodsId);

	void deleteByGoodsId(Integer goodsId);
	
}
