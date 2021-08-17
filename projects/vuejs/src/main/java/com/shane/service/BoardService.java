package com.shane.service;

import com.shane.model.Board;
import com.shane.model.Result;

public interface BoardService {
	public Result createBoard(Board board);
	public Result retrieveBoardList();
	public Result retrieveBoard(int boardno);
	public Result updateBoard(Board board);
	public Result deleteBoard(int boardno);
}
