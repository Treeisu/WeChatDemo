package com.imooc.service;


import java.util.List;
import java.util.Random;

import com.imooc.dao.CommandDao;
import com.imooc.dao.MessageDao;
import com.imooc.model.Command;
import com.imooc.model.CommandContent;
import com.imooc.model.Iconst;
import com.imooc.model.Message;

/**
 * 后台维护界面 查询数据库中所有数据
 * @author c_huangjingjing
 *
 */
public class QueryService {
	
	public List<Message> queryMessageList(String command,String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
	/**
	 *  查询方法，聊天界面 通过输入的指令 内容
	 * @param command
	 * @return
	 */
	public String queryByCommand(String command) {
		CommandDao commandDao=new CommandDao();
		List<Command> contentList;
		if(Iconst.MORE_COMMAND.equals(command)) {
			contentList = commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();//用于拼接查询结果；每次拼接都会改变result值，所以采用stringbuilder类型。
			if(contentList.size() > 0){
				for(int i = 0; i < contentList.size(); i++) {
					result.append("<br/>");
					result.append("回复" + contentList.get(i).getName() + "可以查看" + contentList.get(i).getDescription());
				}
				return result.toString();//返回拼接好的结果，并转化成string类型
			}else{
				result.append("系统无指令");
				return result.toString();
			}
		}else{//如果指令不是 更多 就执行查询方法；
			contentList = commandDao.queryCommandList(command, null);
			if(contentList.size() > 0) {//查询出来的结果不是空的
				Command c_command=new Command();
				c_command=contentList.get(0);
				List<CommandContent> list = c_command.getContentList();
				int i = new Random().nextInt(list.size());//随机数
				return list.get(i).getContent();	
				
			}else{
				return Iconst.NO_MATCHING_CONTENT;
			}
			
		}
		
	}
	
//	public String queryByCommand(String command) {
//	CommandDao commandDao = new CommandDao();
//	List<Command> commandList;
//	if(Iconst.MORE_COMMAND.equals(command)) {
//		commandList = commandDao.queryCommandList(null, null);
//		StringBuilder result = new StringBuilder();
//		for(int i = 0; i < commandList.size(); i++) {
//			if(i != 0) {
//				result.append("<br/>");
//			}
//			result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
//		}
//		return result.toString();
//	}
//	commandList = commandDao.queryCommandList(command, null);
//	if(commandList.size() > 0) {
//		Command c_command=new Command();
//		c_command=commandList.get(0);
//		List<CommandContent> contentList = c_command.getContentList();
//		int i = new Random().nextInt(contentList.size());
//		return contentList.get(i).getContent();
//	}
//	return Iconst.NO_MATCHING_CONTENT;
//}
}
