package com.imooc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.service.MaintainService;

/**
 * 删除
 * @author c_huangjingjing
 *
 */
@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String[] ids= req.getParameterValues("id");
		if(ids!=null){
			MaintainService maintainService = new MaintainService();
			maintainService.deleteBatch(ids);
			req.getRequestDispatcher("/list.do").forward(req, resp);
		}else{
			req.getRequestDispatcher("/list.do").forward(req, resp);
		}
	}
		
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
