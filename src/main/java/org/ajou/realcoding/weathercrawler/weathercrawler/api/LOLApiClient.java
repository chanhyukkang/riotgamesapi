package org.ajou.realcoding.weathercrawler.weathercrawler.api;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.Summoner;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class LOLApiClient {


    private final String api_key = "RGAPI-f354c9d8-b302-47fc-b3b2-9168fc385991";
    private final String summonerURL = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={api_key}";

    private final String encryptedURL = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={api_key}";

    private final ParameterizedTypeReference<List<LeaguePosition>> leaguePositionType = new ParameterizedTypeReference<List<LeaguePosition>>() {};

    @Autowired
    RestTemplate restTemplate;

    public Summoner requestSummoner(String summonerName){
        return restTemplate.exchange(summonerURL, HttpMethod.GET, null, Summoner.class, summonerName, api_key).getBody();
    }
    public List<LeaguePosition> requestLeaguePosition(String Id){
        return restTemplate.exchange(encryptedURL, HttpMethod.GET, null, leaguePositionType, Id, api_key).getBody();
    }

}
