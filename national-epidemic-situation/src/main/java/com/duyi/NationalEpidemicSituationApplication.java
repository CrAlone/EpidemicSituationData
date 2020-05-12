package com.duyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cr
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NationalEpidemicSituationApplication {
	public static void main(String[] args) {
		SpringApplication.run(NationalEpidemicSituationApplication.class, args);
	}

}
