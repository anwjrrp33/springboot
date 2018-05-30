package com.example.db;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class DataSourceTests {
	
	@Autowired
	DataSource ds;
	
	@Test
	public void testConnection() throws Exception{
		
		Connection con = ds.getConnection();
		
		log.info("con: " + con);
		
		con.close();
	}
}
