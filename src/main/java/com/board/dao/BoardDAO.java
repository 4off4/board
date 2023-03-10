package com.board.dao;
import java.util.List;
import com.board.domain.BoardDTO;

public interface BoardDAO {
	// 게시판 목록 
	public List list() throws Exception; 
	// 게시물 작성
	public void write(BoardDTO vo) throws Exception;
	// 게시물 조회 
	public BoardDTO view(int seq) throws Exception;
	// 게시물 수정
	public void modify(BoardDTO vo) throws Exception;
	// 게시물 삭제
	public void delete(int seq) throws Exception;
	// 게시물 총 개수
	public int count() throws Exception;
	// 게시물 목록 + 페이징
	public List<BoardDTO> listPage(int displayPost, int postNum) throws Exception;
	// 게시물 목록 + 페이징 + 검색
	public List<BoardDTO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception;
	// 게시물 총 갯수 + 검색 적용
	public int searchCount(String searchType, String keyword) throws Exception;
}
