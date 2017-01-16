package com.imooc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import com.imooc.UtilEntity.Page;
import com.imooc.dao.CommandDaoImpl;
import com.imooc.dao.MessageDaoImpl;
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
	
	public List<Message> queryMessageList(String command,String description,Page page) {
		//封装message对象
		Message message=new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
		
		//执行查询总条数的方法，设置完所有的page对象参数
		int totalNumber=messageDaoImpl.count(message);
		page.setTotalNumber(totalNumber);
		page.count();//这样page对象里面的参数全部设置完毕
		
		//由于查询条件不能有两种类型的参数，那么传入的两个对象都需要封装成map对象；
		Map<String, Object> messageAndPage=new HashMap<String, Object>();
		messageAndPage.put("message", message);
		messageAndPage.put("page", page);
		return messageDaoImpl.queryMessageList(messageAndPage);
	}
	/**
	 * 第二种分页的实现，通过拦截器
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListbyPage(String command,String description,Page page) {
		//封装message对象
		Message message=new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
		//由于查询条件不能有两种类型的参数，那么传入的两个对象都需要封装成map对象；
		Map<String, Object> messageAndPage=new HashMap<String, Object>();
		messageAndPage.put("message", message);
		messageAndPage.put("page", page);
		//注意！！！这里page对象的参数并没有设置完全，只有一个当前页，以及页面显示条数，拦截器拦截下来需要进行设置
		return messageDaoImpl.queryMessageListbyPage(messageAndPage);
	}
	
	/**
	 *  查询方法，聊天界面 通过输入的指令 内容   一对多查询
	 * @param command
	 * @return
	 */
	public String queryByCommand(String command) {
		CommandDaoImpl commandDao=new CommandDaoImpl();
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
