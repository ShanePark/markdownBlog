package com.shanep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shanep.model.Board;
import com.shanep.service.BoardService;

@Controller
public class UrlController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value={"/","board"})
	public ModelAndView boardList(ModelAndView mav) {
		mav.setViewName("page/board");
		return mav;
	}
	
	@GetMapping("board/{boardno}")
	public ModelAndView board(
			ModelAndView mav
			, @PathVariable int boardno) {
		mav.setViewName("page/view");
		Board board = (Board) boardService.retrieveBoard(boardno).getPayload();
		mav.addObject(board);
		return mav;
	}
	@GetMapping("board/{boardno}/delete")
	public ModelAndView delete(
			ModelAndView mav
			, @PathVariable int boardno) {
		mav.setViewName("redirect:/");
		boardService.deleteBoard(boardno);
		return mav;
	}
	
	@GetMapping("board/write")
	public ModelAndView write(ModelAndView mav) {
		mav.setViewName("page/write");
		return mav;
	}
	@PostMapping("board/write")
	public ModelAndView writeView(
			ModelAndView mav
			,@ModelAttribute Board board) {
		mav.setViewName("redirect:/");
		boardService.createBoard(board);
		return mav;
	}
	
	@GetMapping("board/{boardno}/edit")
	public ModelAndView editView(
			ModelAndView mav
			, @PathVariable int boardno) {
		mav.setViewName("page/edit");
		Board board = (Board) boardService.retrieveBoard(boardno).getPayload();
		mav.addObject(board);
		return mav;
	}
	@PostMapping("board/{boardno}/edit")
	public ModelAndView edit(
			ModelAndView mav
			, @PathVariable int boardno
			, @ModelAttribute Board board) {
		mav.setViewName(String.format("redirect:/board/%d", boardno));
		boardService.updateBoard(board);
		return mav;
	}
}
