package com.example;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Board;
import com.example.domain.QBoard;
import com.example.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardRepositoryTests {
	
	@Setter(onMethod_={@Autowired})
	BoardRepository repo;
	
	@Test
	public void testQuerydsl1() {
		
		String type = "t";
		String keyword = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard board = QBoard.board;
		
		if(type.equals("t")) {
			
			builder.and(board.title.like("%" + keyword + "%"));
		}
		
		builder.and(board.bno.gt(0L));
		
		//repo.findAll(builder).iterator().forEachRemaining(v -> log.info("" + v));
		
		Pageable page = PageRequest.of(0,10, Direction.DESC, "bno");
		
		Page<Board> list = repo.findAll(builder, page);
		
		log.info("TOTAL: " + list.getTotalPages());
		
		list.forEach(v -> log.info("" + v));
	}
	
	@Test
	public void testList3() {
		
		Pageable param = PageRequest.of(0, 20);
		
		Page<Board> result = repo.getList(param);
		
		log.info("" + result);
	}
	
	@Test
	public void testList2() {
		
		Pageable param = PageRequest.of(0, 20, Sort.Direction.DESC);
		
		Page<Board> result = repo.findByTitleContains("1", param);
		
		log.info("result: " + result);
		
		log.info("Total Pages: " + result.getTotalPages());
	}
	
	@Test
	public void testList() {
		
		Pageable pageable = PageRequest.of(0, 10);
		
		List<Board> list = repo.findByBnoGreaterThanOrderByBnoDesc(0L, pageable);
		
		list.forEach(vo -> log.info("vo: " + vo));
	}
	
	@Test
	public void testRead() {
		
		log.info("repo.getClass().getName(): " + repo.getClass().getName());
		
		Optional<Board> result = repo.findById(10L);
		
		result.ifPresent(vo -> log.info("vo: " + vo));
	}
	
	@Test
	public void testInsert() {
		
		for(int i = 0; i < 100; i++) {
			
			Board vo = new Board();
			vo.setTitle("제목: " + i);
			vo.setContent("내용: " + i);
			vo.setWriter("user: " + i);
			
			repo.save(vo);
		}
	}
}