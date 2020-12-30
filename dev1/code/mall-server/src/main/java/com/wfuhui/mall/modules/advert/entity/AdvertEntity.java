package com.wfuhui.mall.modules.advert.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Banner
 */
public class AdvertEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer bannerId;
	// 图片地址
	private String picUrl;
	// 是否启用，0：禁用，1：启用
	private Integer enable;

	private String link;
	private Integer sort;

	// 创建时间
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	/**
	 * 获取：
	 */
	public Integer getBannerId() {
		return bannerId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 设置：是否启用，0：禁用，1：启用
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * 获取：是否启用，0：禁用，1：启用
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
