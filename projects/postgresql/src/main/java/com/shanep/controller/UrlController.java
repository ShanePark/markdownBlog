package com.shanep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shanep.model.Board;
import com.shanep.service.BoardService;

@Controller
public class UrlController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("board")
	public ModelAndView boardList(ModelAndView mav) {
		mav.setViewName("board");
		return mav;
	}
	
	@RequestMapping("board/{boardno}")
	public ModelAndView board(
			ModelAndView mav
			, @PathVariable int boardno) {
		mav.setViewName("view");
		Board board = (Board) boardService.retrieveBoard(boardno).getPayload();
		mav.addObject(board);
		return mav;
	}

}
