package com.imooc.dao;

import java.util.List;

import com.imooc.model.Command;

public interface CommandDao {
	public List<Command> queryCommandList(Command command);
	public void insertCommand(Command command);
}
