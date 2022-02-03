package com.pheonix.pheonix;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class PheonixApplicationTests {
    @Value("${test.property.name}")
	private String testName;

    @Autowired
    private DataSource dataSource;


    @Test
	void valueExists() {
		assertThat(testName);
		log.info(testName);
	}
	@Test
	void applicationCanBeConnectToDataBaseTest(){
    	assertThat(dataSource).isNotNull();
		Connection connection;
		try{
			connection = dataSource.getConnection();
			assertThat(connection).isNotNull();
			log.info("connection -> {}", connection.getSchema());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


}
