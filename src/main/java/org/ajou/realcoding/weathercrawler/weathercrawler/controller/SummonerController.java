package org.ajou.realcoding.weathercrawler.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.Summoner;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.LeaguePosition;
import org.ajou.realcoding.weathercrawler.weathercrawler.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SummonerController {

    @Autowired
    SummonerService summonerService;


    @GetMapping("/lol/summoner/v4/summoners/by-name")
    public List<String> getAvailableNames() throws IOException {
        return summonerService.getAvailableSummonerName();
    }

    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public Summoner getSummoner(@PathVariable String nickname){
        return summonerService.getSummonerBySummonerName(nickname);
    }

    @GetMapping("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    public LeaguePosition getEncryptedSummoner(@PathVariable String nickname){
        return summonerService.getLeaguePositionByEncrypted(nickname);
    }


}
