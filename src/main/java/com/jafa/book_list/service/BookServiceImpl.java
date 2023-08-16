package com.jafa.book_list.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jafa.book_list.domain.BookVO;
import com.jafa.book_list.repository.BookRepository;
import com.jafa.common.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class BookServiceImpl implements BookService {

	private final BookRepository repository;
	
	@Override
	public List<BookVO> getList(Criteria criteria) {
		log.info("(ServiceImpl)목록을 조회하였습니다 : ");
		return repository.getList(criteria);
	}

	@Override
	public void register(BookVO book) {
		log.info("(ServiceImpl)등록하였습니다 : " + book);
		repository.insertSelectKey(book);
	}

	@Override
	public BookVO get(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 조회하였습니다 : ");
		return repository.read(bno);
	}

	@Override
	public boolean modify(BookVO book) {
		log.info("(ServiceImpl)게시물을 수정하였습니다 : " + book);
		return repository.update(book) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("(ServiceImpl)"+ bno + "번 게시물을 삭제하였습니다");
		return repository.delete(bno) == 1;
	}

	@Override
	public int totalCount() {
		return repository.getTotalCount();
	}

}
