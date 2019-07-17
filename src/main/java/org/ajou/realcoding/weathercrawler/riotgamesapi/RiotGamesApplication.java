package org.ajou.realcoding.weathercrawler.riotgamesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RiotGamesApplication {

	public static void main(String[] args) {

		SpringApplication.run(RiotGamesApplication.class, args);
	}

}
