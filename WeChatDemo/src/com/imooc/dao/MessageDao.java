package com.imooc.dao;

import java.util.List;
import java.util.Map;

import com.imooc.UtilEntity.Page;
import com.imooc.model.Message;

public interface MessageDao {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map<String, Object> map);
	/**
	 * 另一种分页实现，利用拦截器实现，此查询方法在数据库中不进行limit拼接
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListbyPage(Map<String, Object> map);
	
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message);
	public void deleteOne(int id);
	public void deleteBatch(List<Integer> list);
}
