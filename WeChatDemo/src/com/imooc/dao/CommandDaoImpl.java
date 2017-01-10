package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.db.DBAccess;
import com.imooc.model.Command;



public class CommandDaoImpl{
	DBAccess dbAccess = new DBAccess();
	SqlSession sqlSession=null;

	public List<Command> queryCommandList(String name,String description) {
		
		List<Command> commandlist=new ArrayList<Command>();
		try {
			sqlSession=dbAccess.getSqlSession();//通过公共类dbAccess去获得一次会话sqlsession
			/**
			 * 由于mybatis的sql配置只接收一个参数，
			 * 为了解决查询时传入两个条件的情况，就对这两个条件进行封装；
			 * 这样就可以传入一个对象进行查询
			 */
			Command command=new Command();
			command.setName(name);
			command.setDescription(description);
			CommandDao commandDao= sqlSession.getMapper(CommandDao.class);
			commandlist=commandDao.queryCommandList(command);//hbm.xml中已经配置好的方法，sqlsession执行查询操作
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*if(sqlSession!=null){
				sqlSession.close();
			}*/
			sqlSession.close();
		}
		return commandlist;
	}
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id) {
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			
			sqlSession.delete("Message.deleteOne", id);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 单条删除
	 */
	public void deleteBatch(List<Integer> list) {
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			sqlSession.delete("Message.deleteBatch", list);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}

}
