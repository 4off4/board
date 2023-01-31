package com.board.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.board.dao.BoardDAO;
import com.board.domain.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
			 
	// 게시판 목록 
	@Override
	public List list() throws Exception {
		return dao.list();
	}
 
	// 게시물 작성 
	@Override
	public void write(BoardDTO vo) throws Exception {
		dao.write(vo);	
	}

	// 게시물 조회 
	@Override
	public BoardDTO view(int seq) throws Exception {
		return dao.view(seq);
	}

	// 게시물 수정 
	@Override
	public void modify(BoardDTO vo) throws Exception {
		dao.modify(vo);	
	}

	// 게시물 삭제 
	@Override
	public void delete(int seq) throws Exception {
		dao.delete(seq);
	}

	// 게시물 총 개수 
	@Override
	public int count() throws Exception {
		return dao.count();
	}

	// 게시물 목록 + 페이징
	@Override
	public List<BoardDTO> listPage(int displayPost, int postNum) throws Exception {
		return dao.listPage(displayPost, postNum);
	}

	// 게시물 목록 + 페이징 + 검색
	@Override
	public List<BoardDTO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		 return  dao.listPageSearch(displayPost, postNum, searchType, keyword);
	}

}