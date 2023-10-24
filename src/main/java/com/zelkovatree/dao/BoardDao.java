package com.zelkovatree.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zelkovatree.entity.BoardEntity;

@Mapper
public interface BoardDao {
	
	public int addBoard(BoardEntity bEnt);
	public List<BoardEntity> getBoard();
}
