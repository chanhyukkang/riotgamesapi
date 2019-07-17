package org.ajou.realcoding.weathercrawler.riotgamesapi.repository;

import org.ajou.realcoding.weathercrawler.riotgamesapi.domain.Summoner;
import org.ajou.realcoding.weathercrawler.riotgamesapi.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SummonerRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void saveSummoner(Summoner summoner){
        mongoTemplate.save(summoner);
    }

    public void saveLeaguePosition(List<LeaguePosition> leaguePosition){
        for(int i = 0 ; i < leaguePosition.size(); i++) {
            mongoTemplate.save(leaguePosition.toArray()[i]);
        }
    }

    public Summoner findSummoner(String summoner) {
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(summoner)), Summoner.class);
    }

    public LeaguePosition findEncrypted(String encryptedId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("summonerId").is(encryptedId)), LeaguePosition.class);
    }
}




