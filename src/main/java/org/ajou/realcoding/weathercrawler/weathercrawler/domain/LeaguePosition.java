package org.ajou.realcoding.weathercrawler.weathercrawler.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LeaguePosition {

    private String queueType;
    @Id
    private String summonerName;
    private boolean hotStreak;
    private int wins;
    private boolean veteran;
    private int losses;
    private String rank;
    private String tier;
    private boolean inactive;
    private boolean freshBlood;
    private String leagueId;
    private String summonerId;
    private int leaguePoints;

}

