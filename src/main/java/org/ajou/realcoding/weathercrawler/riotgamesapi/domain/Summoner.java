package org.ajou.realcoding.weathercrawler.riotgamesapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Summoner {

    private int profileIconId;
    @Id
    private String name;
    private String puuid;
    private int summonerLevel;
    private String accountId;
    private String id;
    private long revisionDate;

}


