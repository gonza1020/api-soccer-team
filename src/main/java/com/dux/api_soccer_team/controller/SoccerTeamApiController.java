package com.dux.api_soccer_team.controller;


import com.dux.api_soccer_team.dto.SoccerTeamResponse;
import com.dux.api_soccer_team.service.SoccerTeamServiceImpl;
import jakarta.annotation.Generated;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@RestController
public class SoccerTeamApiController implements SoccerTeamApi {


    private SoccerTeamServiceImpl service;

    @Autowired
    public SoccerTeamApiController(SoccerTeamServiceImpl service) {
    this.service = service;
    }



    @Override
    public ResponseEntity<SoccerTeamResponse> addSoccerTeam(SoccerTeamResponse soccerTeamResponse) {
        return new ResponseEntity<>(service.addSoccerTeam(soccerTeamResponse),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSoccerTeam(Integer id) {
        service.deleteSoccerTeam(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<SoccerTeamResponse>> getAllSoccerTeams() {
        return new ResponseEntity<>(service.getAllSoccerTeams(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeamResponse> getSoccerTeamById(Integer id) throws BadRequestException {

        return new ResponseEntity<>(service.getSoccerTeamById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeamResponse> getSoccerTeamByName(String nombre) {

        return new ResponseEntity<>(service.getSoccerTeamByNombre(nombre), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeamResponse> updateSoccerTeam(Integer id, SoccerTeamResponse soccerTeamResponse) {

        return new ResponseEntity<>(service.updateSoccerTeam(id, soccerTeamResponse), HttpStatus.OK);
    }
}
