package com.dux.api_soccer_team.service;

import com.dux.api_soccer_team.dto.SoccerTeam;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface SoccerTeamService {

    SoccerTeam getSoccerTeamById(Integer id) throws BadRequestException;
    SoccerTeam getSoccerTeamByNombre(String name);
    SoccerTeam updateSoccerTeam(Integer id, SoccerTeam soccerTeam);
    SoccerTeam addSoccerTeam(SoccerTeam soccerTeam);
    List<SoccerTeam> getAllSoccerTeams();
    void deleteSoccerTeam(Integer id);
}
