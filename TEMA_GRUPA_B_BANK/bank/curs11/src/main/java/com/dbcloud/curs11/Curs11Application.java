package com.dbcloud.curs11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
@EnableJpaRepositories
public class Curs11Application {

	public static void main(String[] args) {
		SpringApplication.run(Curs11Application.class, args);
	}

}
