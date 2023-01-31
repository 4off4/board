package com.board.dao;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.board.mappers.board";

	//게시판 목록 
	@Override
	public List list() throws Exception {
		//매퍼에 전송하기 
		return sqlSession.selectList(namespace + ".list");
	}
	
	// 게시물 작성 
	@Override
	public void write(BoardDTO vo) throws Exception {
		//매퍼에 전송하기 
		sqlSession.insert(namespace + ".write", vo);
	}

	// 게시물 조회 
	@Override
	public BoardDTO view(int seq) throws Exception {
		//매퍼에 전송하기 
		return sqlSession.selectOne(namespace + ".view", seq);
	}

	// 게시물 수정 
	@Override
	public void modify(BoardDTO vo) throws Exception {
		//매퍼에 전송하기 
		sqlSession.update(namespace + ".modify", vo);
	}
	
	// 게시물 삭제 
	@Override
	public void delete(int seq) throws Exception {
		//매퍼에 전송하기 
		sqlSession.delete(namespace + ".delete", seq);
	}

	// 게시물 총 개수 
	@Override
	public int count() throws Exception {
		//매퍼에 전송하기 
		return sqlSession.selectOne(namespace + ".count");
	}

	// 게시물 목록 + 페이징
	@Override
	public List<BoardDTO> listPage(int displayPost, int postNum) throws Exception {
		HashMap data = new HashMap();
		  
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		//매퍼에 전송하기 
		return sqlSession.selectList(namespace + ".listPage", data);
	}
	
	// 게시물 목록 + 페이징 + 검색
	@Override
	public List<BoardDTO> listPageSearch(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		  
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		  
		return sqlSession.selectList(namespace + ".listPageSearch", data);
	}

}