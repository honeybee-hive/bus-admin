package com.github.bus.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * mybatis基础Dao
 */
public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 按主键查找对象
	 */
	public abstract T getById(PK pk);

	/**
	 * 获取总记录数
	 * @param params map中必须传入: 
	 * 		response - 查询条件的对象<T>
	 * @return
	 */
	public abstract int getTotalCount(Map<String, Object> params);
	
	/**
	 * 查询一个对象集合
	 * @param obj 查询条件的对象
	 * @return
	 */
	public abstract List<T> findList(T obj);

	/**
	 * 分页
	 * 必须传入两个对象：
	 * 		response - 查询条件的对象<T>
	 * 		pager - 分页对象
	 * @param params
	 * @return
	 */
	public abstract List<T> findPageList(Map<String, Object> params);

	/**
	 * 保存
	 */
	public abstract int save(T obj);
	
	/**
	 * 更新
	 */
	public abstract int update(T obj);

	/**
	 * 删除
	 */
	public abstract int delete(PK pk);

}
	
