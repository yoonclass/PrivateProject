package com.jafa.book_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jafa.book_list.domain.BookAttachVO;
import com.jafa.book_list.domain.BookVO;
import com.jafa.book_list.repository.BookAttachRepository;
import com.jafa.book_list.repository.BookRepository;
import com.jafa.common.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookAttachRepository bookAttachRepository;
	
	@Override
	public List<BookVO> getList(Criteria criteria) {
		log.info("(ServiceImpl)목록을 조회하였습니다 : ");
		return bookRepository.getList(criteria);
	}

	@Transactional
	@Override
	public void register(BookVO book) {
		bookRepository.insertSelectKey(book);
		
		// 첨부파일이 있을 때 ...
		if(book.getAttachList()!=null && !book.getAttachList().isEmpty()) { 
			book.getAttachList().forEach(attachFile->{
				attachFile.setBno(book.getBno());
				bookAttachRepository.insert(attachFile);
			});
		}	
	}

	@Override
	public BookVO get(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 조회하였습니다 : ");
		return bookRepository.read(bno);
	}

	@Override
	public boolean modify(BookVO book) {
		log.info("(ServiceImpl)게시물을 수정하였습니다 : " + book);
		return bookRepository.update(book) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 삭제하였습니다");
		return bookRepository.delete(bno) == 1;
	}

	@Override
	public int totalCount() {
		return bookRepository.getTotalCount();
	}

	@Override
	public List<BookAttachVO> getAttachList(Long bno) {
		return bookAttachRepository.selectByBno(bno);
	}
}