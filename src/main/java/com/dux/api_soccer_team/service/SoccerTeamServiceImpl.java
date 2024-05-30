package com.dux.api_soccer_team.service;

import com.dux.api_soccer_team.dto.SoccerTeam;
import com.dux.api_soccer_team.model.SoccerTeamModel;
import com.dux.api_soccer_team.repository.SoccerTeamRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SoccerTeamServiceImpl implements SoccerTeamService{

    private final SoccerTeamRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public SoccerTeamServiceImpl(SoccerTeamRepository repository, ModelMapper modelMapper){
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SoccerTeam getSoccerTeamById(Integer id) {
        SoccerTeamModel soccerTeam = repository.findById(id).orElseThrow();
        return modelMapper.map(soccerTeam, SoccerTeam.class);
    }

    @Override
    public SoccerTeam getSoccerTeamByNombre(String name) {
        SoccerTeamModel soccerTeam = repository.findByNombre(name);
        if(soccerTeam != null) {
            return modelMapper.map(soccerTeam, SoccerTeam.class);
        }
        throw new NoSuchElementException();
    }

    @Override
    public SoccerTeam updateSoccerTeam(Integer id, SoccerTeam soccerTeam) {

        SoccerTeam soccerTeamToUpdate = getSoccerTeamById(id);
        soccerTeamToUpdate
                .id(id)
                .liga(soccerTeam.getLiga())
                .pais(soccerTeam.getPais())
                .nombre(soccerTeam.getNombre());
        return addSoccerTeam(soccerTeamToUpdate);
    }

    @Override
    public SoccerTeam addSoccerTeam(SoccerTeam soccerTeam) {
        SoccerTeamModel model = modelMapper.map(soccerTeam, SoccerTeamModel.class);
        SoccerTeamModel savedModel = repository.save(model);
        return modelMapper.map(savedModel, SoccerTeam.class);
    }

    @Override
    public List<SoccerTeam> getAllSoccerTeams() {
        return repository.findAll().stream().map(equipo -> modelMapper.map(equipo, SoccerTeam.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteSoccerTeam(Integer id) {

    }
}
