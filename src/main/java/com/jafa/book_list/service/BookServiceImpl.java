package com.jafa.book_list.service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
		List<BookAttachVO> attachList = book.getAttachList();
		
		if(attachList!=null) {
			//기존 파일 삭제
			List<BookAttachVO> delList = attachList.stream().filter(attach ->attach.getBno()!=null).collect(Collectors.toList());
			deleteFiles(delList); //파일 삭제
			delList.forEach(attach->{
				bookAttachRepository.delete(attach.getUuid());//DB기록 삭제
			});
			
			//새로운 파일 삭제
			attachList.stream().filter(attach -> attach.getBno()==null).forEach(attach->{
				book.setBno(book.getBno());
				bookAttachRepository.insert(attach);//DB기록
			});
		}
		return bookRepository.update(book) == 1;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		List<BookAttachVO> attachList = getAttachList(bno);
		if(attachList!=null) {
			deleteFiles(attachList);
			bookAttachRepository.deleteAll(bno);
		}
		log.info("(ServiceImpl)"+ bno + "번 게시물을 삭제하였습니다");
		return bookRepository.delete(bno) == 1;
	}

	@Override
	public int totalCount(Criteria criteria) {
		return bookRepository.getTotalCount(criteria);
	}

	@Override
	public List<BookAttachVO> getAttachList(Long bno) {
		return bookAttachRepository.selectByBno(bno);
	}

	@Override
	public BookAttachVO getAttach(String uuid) {
		return bookAttachRepository.selectByUuid(uuid);
	}
	
	private void deleteFiles(List<BookAttachVO> delList) {
		delList.forEach(book->{
			File file = new File("C:/storage/"+book.getUploadPath(),book.getUuid()+"_"+book.getFileName());
			file.delete();
			if(book.isFileType()) {
				file = new File("C:/storage/"+book.getUploadPath(),"s_"+book.getUuid()+"_"+book.getFileName());
				file.delete();
			}
		});
	}

	@Override
	public List<BookVO> getLatestBooks() {
		int minRow = 0;
		int maxRow = 5;
		return bookRepository.getLatestBooks(minRow, maxRow);
	}
}