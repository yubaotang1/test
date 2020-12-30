package com.wfuhui.mall.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.mall.modules.member.entity.MemberEntity;
import com.wfuhui.mall.modules.sys.dao.BaseDao;


/**
 * 会员
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {

	MemberEntity queryByOpenid(String openid);

	MemberEntity queryByLoginName(String loginName);
}
