package com.dux.api_soccer_team.controller;


import com.dux.api_soccer_team.dto.SoccerTeam;
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
    public ResponseEntity<SoccerTeam> addSoccerTeam(SoccerTeam soccerTeam) {
        return new ResponseEntity<>(service.addSoccerTeam(soccerTeam),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteSoccerTeam(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<SoccerTeam>> getAllSoccerTeams() {
        return new ResponseEntity<>(service.getAllSoccerTeams(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeam> getSoccerTeamById(Integer id) throws BadRequestException {

        return new ResponseEntity<>(service.getSoccerTeamById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeam> getSoccerTeamByName(String nombre) {

        return new ResponseEntity<>(service.getSoccerTeamByNombre(nombre), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SoccerTeam> updateSoccerTeam(Integer id, SoccerTeam soccerTeam) {

        return new ResponseEntity<>(service.updateSoccerTeam(id, soccerTeam), HttpStatus.OK);
    }
}
