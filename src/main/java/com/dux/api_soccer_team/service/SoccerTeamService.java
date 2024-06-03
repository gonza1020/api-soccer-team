package com.dux.api_soccer_team.service;

import com.dux.api_soccer_team.dto.SoccerTeamResponse;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface SoccerTeamService {

    SoccerTeamResponse getSoccerTeamById(Integer id) throws BadRequestException;
    SoccerTeamResponse getSoccerTeamByNombre(String name);
    SoccerTeamResponse updateSoccerTeam(Integer id, SoccerTeamResponse soccerTeamResponse);
    SoccerTeamResponse addSoccerTeam(SoccerTeamResponse soccerTeamResponse);
    List<SoccerTeamResponse> getAllSoccerTeams();
    void deleteSoccerTeam(Integer id);
}
