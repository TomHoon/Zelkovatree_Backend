package com.zelkovatree.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zelkovatree.entity.BoardEntity;

@Mapper
public interface BoardDao {
	
	public int addBoard(BoardEntity bEnt);
}
