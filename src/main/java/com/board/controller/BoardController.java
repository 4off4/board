package com.board.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.domain.Page;
import com.board.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	 private BoardService service;
	
	// 게시물 목록 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Locale locale, Model model) throws Exception {
		  List list = service.list();
		  model.addAttribute("list", list);
	}
	
	// 게시물 작성1
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {}
	
	// 게시물 작성2
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(BoardDTO vo) throws Exception {
	  service.write(vo);
	  
	  // 게시물 작성 이후 게시판 목록으로 이동 
	  return "redirect:/board/list";
	}
	
	// 게시물 조회
	// @RequestParam([문자열])를 이용하면, 주소에 있는 특정한 값을 가져올 수 있습니다. 
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("seq") int seq, Model model) throws Exception {
		BoardDTO vo = service.view(seq);
		model.addAttribute("view", vo);
	}
	
	// 게시물 수정1
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("seq") int seq, Model model) throws Exception {
		BoardDTO vo = service.view(seq);
		model.addAttribute("view", vo);
	}
	
	// 게시물 수정2
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardDTO vo) throws Exception {
	
		// 뷰-> 컨트롤러로 넘어온 데이터(BoardDTO)를 이용하여 수정을 한다.
		service.modify(vo);
	 
		// 현재 시퀀스에 해당하는 조회 페이지로 이동 
		return "redirect:/board/view?seq=" + vo.getSeq();
	}
	
	// 게시물 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("seq") int seq) throws Exception {
	  
	 service.delete(seq);  
	 
	 // 게시물을 삭제하고 게시물 목록 페이지로 이동
	 return "redirect:/board/list";
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("seq") int num) throws Exception {

		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());  

		List<BoardDTO> list = null; 
		list = service.listPage(page.getDisplayPost(), (page.getDisplayPost()+page.getPostNum()));
		
		model.addAttribute("list", list);
		/*
		model.addAttribute("pageNum", page.getPageNum());

		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
	
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());  
		 */
		
		model.addAttribute("page", page);
		model.addAttribute("select", num);
		/*
		// 게시물 총 개수
		int count = service.count();
	  
		// 한 페이지에 출력할 게시물 개수
		int postNum = 10;
	  
		// 하단 페이징 번호 ([ 게시물 총 개수 ÷ 한 페이지에 출력할 개수 ] 올림)
		int pageNum = (int)Math.ceil((double)count/postNum);
	  
		// 출력할 게시물
		int displayPost = (num - 1) * postNum;
		
		// 이 번호의 개수를 알면 현재 페이지의 마지막 번호를 알 수 있고, 현재 페이지의 시작 번호도 알 수 있습니다. 
		// 시작 페이지 = 마지막 페이지 번호 - 한번에 표시할 페이지 번호의 개수 + 1
		// 마지막 페이지 번호 = ((올림)(현재 페이지 번호 / 한번에 표시할 페이지 번호의 개수)) * 한번에 표시할 페이지 번호의 개수
		
		// 한번에 표시할 페이징 번호의 개수
		int pageNum_cnt = 10;

		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		
		// 마지막 번호 재계산
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
		 
		if(endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
	    
		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		
		List list = null;
		list = service.listPage(displayPost, displayPost+postNum);
		// list = service.listPage(displayPost, postNum);
		
		model.addAttribute("list", list);   
		
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 이전 및 다음 
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		// 현재 페이지
		model.addAttribute("select", num);
		*/
	}
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("seq") int num, 
			@RequestParam(value = "searchType", required = false, defaultValue = "content") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {

		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.count());  

		List<BoardDTO> list = null;
		// list = service.listPage(page.getDisplayPost(), (page.getDisplayPost()+page.getPostNum()));
		list = service.listPageSearch(page.getDisplayPost(), (page.getDisplayPost()+page.getPostNum()), searchType, keyword);
		
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
	}	
}