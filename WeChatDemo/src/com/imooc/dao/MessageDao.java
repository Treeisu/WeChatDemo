package com.imooc.dao;

import java.util.List;

import com.imooc.model.Message;

public interface MessageDao {
	public List<Message> queryMessageList(Message message);
	public void deleteOne(int id);
	public void deleteBatch(List<Integer> list);
}
