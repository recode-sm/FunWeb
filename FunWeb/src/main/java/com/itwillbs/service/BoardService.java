package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

public interface BoardService {

	void insertBoard(BoardDTO boardDTO);

	List<BoardDTO> getBoardList(PageDTO pageDTO);

	int getBoardCount();

}
