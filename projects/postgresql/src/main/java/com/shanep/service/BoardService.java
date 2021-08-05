package com.shanep.service;

import com.shanep.model.Board;
import com.shanep.model.Result;

public interface BoardService {
	public Result updateBoard(Board board);
	public Result deleteBoard(int boardno);
}
