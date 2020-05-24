package com.duyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cr
 */
@SpringBootApplication
@MapperScan("com.duyi.mapper")
//打开对定时任务的使用
@EnableScheduling
public class NationalEpidemicSituationApplication {
	public static void main(String[] args) {
		SpringApplication.run(NationalEpidemicSituationApplication.class, args);
	}
}
