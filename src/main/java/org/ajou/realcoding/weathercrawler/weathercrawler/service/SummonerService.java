package org.ajou.realcoding.weathercrawler.weathercrawler.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.weathercrawler.api.LOLApiClient;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.Summoner;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.LeaguePosition;
import org.ajou.realcoding.weathercrawler.weathercrawler.repository.SummonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SummonerService {

    List<String> names = null;
    @Autowired
    ObjectMapper objectmapper;
    @Autowired
    LOLApiClient LOLApiClient;

    @PostConstruct
    public List<String> loadAvailableSummonerNamesFromFile() throws IOException {
        File file = new File ("lolapi");

        return objectmapper.readValue(file, new TypeReference<List<String>>(){} );
    }

    public List<String> getAvailableSummonerName(){
        return names;
    }

    public Summoner getSummonerBySummonerName(String encryptedId){
        return summonerRepository.findSummoner(encryptedId);

    }
    public LeaguePosition getLeaguePositionByEncrypted(String encryptedId){
        return summonerRepository.findEncrypted(encryptedId);

    }

    LinkedList<String> namesQueue = new LinkedList();

    @Autowired
    SummonerRepository summonerRepository;

    @Scheduled(fixedDelay = 2000L)
    public void getSummonerPeriodcally() throws IOException{
        if(namesQueue.isEmpty()){
            namesQueue.addAll(loadAvailableSummonerNamesFromFile());
        }
        String summonername = namesQueue.pop();
        namesQueue.addLast(summonername);

        Summoner summoner = LOLApiClient.requestSummoner(summonername);
        summonerRepository.saveSummoner(summoner);
        log.info("Summoner has been inserted successfully, {}", summoner);

        System.out.println("\nencryptedId : " + summoner.getId() + "\n"); //나중에 지워야함

        List<LeaguePosition> encrypted = LOLApiClient.requestLeaguePosition(summoner.getId());

        System.out.println("leagueposition : " + encrypted + "\n"); // 나중에 지워야함

        summonerRepository.saveLeaguePosition(encrypted);
        log.info("League Position has been inserted successfully, {}", encrypted);

    }

}
