package com.example.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="tbl_board")
@Data
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bno;
	
	private String title, content, writer;
	
	@CreationTimestamp
	private Timestamp regdate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
}