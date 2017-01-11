package com.imooc.UtilEntity;

import com.imooc.model.Message;

/**
 * 分页对应的实体类
 */
public class Page {
	/**
	 * 总条数
	 */
	private int totalNumber;
	/**
	 * 页面传来的值，当前第几页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 每页显示条数
	 */
	private int everyPageNumber = 5;
	/**
	 * 数据库中limit的参数，从第几条开始取
	 */
	private int dbIndex;
	/**
	 * 数据库中limit的参数，一共取多少条
	 */
	private int dbNumber;
	/**
	 * 引入需要的另一个参数
	 */
	private Message message;
	/**
	 * 根据当前对象中属性值计算并设置相关属性值
	 */
	public void count() {
		// 计算总页数
		int pageCounttemp = this.totalNumber / this.everyPageNumber;//取整
		int plus = (this.totalNumber % this.everyPageNumber) == 0 ? 0 : 1;//有余数则加一页
		pageCounttemp = pageCounttemp + plus;//总页数
		if(pageCounttemp <= 0) {
			pageCounttemp = 1;//若查询不到数据，总页数为1
		}
		this.pageCount = pageCounttemp;
		
		// 显示的页数在总页数之外情况下，设置当前页数
		if(this.pageCount < this.currentPage) {
			this.currentPage = this.pageCount;// 总页数小于当前页数，应将当前页数设置为总页数
		}
		if(this.currentPage < 1) {
			this.currentPage = 1;// 当前页数小于1设置为1
		}
		
		// 设置limit的参数；需要传入当前页之前页所有 的条数；以及每页显示的数量，这样才能设置当前页该显示哪几条数据。
		this.dbIndex = (this.currentPage - 1) * this.everyPageNumber;//
		this.dbNumber = this.everyPageNumber;//数据库中的每页显示数量
	}
	
	public Page() {
		super();
	}
	
	public Page(int totalNumber, int currentPage, int pageCount,
			int everyPageNumber, int dbIndex, int dbNumber) {
		super();
		this.totalNumber = totalNumber;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.everyPageNumber = everyPageNumber;
		this.dbIndex = dbIndex;
		this.dbNumber = dbNumber;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getEveryPageNumber() {
		return everyPageNumber;
	}

	public void setEveryPageNumber(int everyPageNumber) {
		this.everyPageNumber = everyPageNumber;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	

}

