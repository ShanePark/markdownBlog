package com.shane.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shane.vo.IssueVO;

@Mapper
public interface IssueDao {
	
	List<IssueVO> issueList();
	
}
