package com.wfuhui.mall.modules.goods.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.goods.entity.CategoryEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;

/**
 * 分类
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {

	
}
