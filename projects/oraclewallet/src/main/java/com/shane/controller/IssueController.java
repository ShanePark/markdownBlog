package com.shane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shane.dao.IssueDao;
import com.shane.vo.IssueVO;

@RestController
@RequestMapping("/")
public class IssueController {
	
	@Autowired
	IssueDao dao;
	
	@GetMapping
	public List<IssueVO> list(){
		return dao.issueList();
	}
	
}
