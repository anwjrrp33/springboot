package com.example.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	@Query(value="select bno, title, content, writer from tbl_board where bno > 0 limit 0, 10 ", nativeQuery=true)
	public List<Object[]> getListNative(Pageable pageable);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC ")
	public Page<Board> getList(Pageable pageable);
	
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	public Page<Board> findByTitleContains(String keyword, Pageable pageable);
	
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable pageable);
}
