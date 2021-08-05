package com.shanep.service;

import com.shanep.model.Board;
import com.shanep.model.Result;

public interface BoardService {
	public Result createBoard(Board board);
	public Result retrieveBoardList();
	public Result retrieveBoard(int boardno);
	public Result updateBoard(Board board);
	public Result deleteBoard(int boardno);
}
