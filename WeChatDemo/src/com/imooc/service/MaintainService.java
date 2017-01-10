package com.imooc.service;

import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.MessageDao;

/**
 * 删除方法
 * @author c_huangjingjing
 *
 */
public class MaintainService {
	/**
	 * 传入int类型，id，进行单条删除
	 * @param id
	 */
	public void deleteOne(String id) {
		if(id != null && !"".equals(id.trim())) {
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 传入一串id，进行多条删除
	 * @param ids
	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		
			List<Integer> list = new ArrayList<Integer>();//把前端接收的一串id数组转化为list
			for(String id : ids) {
				list.add(Integer.valueOf(id));
			
			messageDao.deleteBatch(list);//转化完后，调用dao层方法
		}
	}
}
