package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

//implements CommandLineRunner
@SpringBootApplication
public class DemoApplication  {


//	@Autowired
//	JdbcTemplate jdbcTemplate;
//
//	public void run(String... args) throws Exception {
//		System.out.println("Reading records...");
//		System.out.printf("%-30.30s  %-30.30s%n", "SCHEDULE", "FLOW ID");
//		jdbcTemplate.update(
//      "INSERT INTO QUARTZ VALUES (?, ?)", "schedule id resolution", "id_3");
//
//		jdbcTemplate.query("SELECT * FROM QUARTZ", (rs)-> {
//			System.out.printf("%-30.30s  %-30.30s%n", rs.getString("SCHEDULE"), rs.getString("FLOW_ID"));
//		});
//
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
