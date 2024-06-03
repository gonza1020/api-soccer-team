package com.dux.api_soccer_team.service;

import com.dux.api_soccer_team.dto.SoccerTeamResponse;
import com.dux.api_soccer_team.model.SoccerTeamModel;
import com.dux.api_soccer_team.repository.SoccerTeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SoccerTeamResponse getSoccerTeamById(Integer id) {
        SoccerTeamModel soccerTeam = repository.findById(id).orElseThrow();
        return modelMapper.map(soccerTeam, SoccerTeamResponse.class);
    }

    @Override
    public SoccerTeamResponse getSoccerTeamByNombre(String name) {
        SoccerTeamModel soccerTeam = repository.findByNombre(name);
        if(soccerTeam != null) {
            return modelMapper.map(soccerTeam, SoccerTeamResponse.class);
        }
        throw new NoSuchElementException();
    }

    @Override
    public SoccerTeamResponse updateSoccerTeam(Integer id, SoccerTeamResponse soccerTeamResponse) {

        SoccerTeamResponse soccerTeamResponseToUpdate = getSoccerTeamById(id);
        soccerTeamResponseToUpdate
                .id(id)
                .liga(soccerTeamResponse.getLiga())
                .pais(soccerTeamResponse.getPais())
                .nombre(soccerTeamResponse.getNombre());
        return addSoccerTeam(soccerTeamResponseToUpdate);
    }

    @Override
    public SoccerTeamResponse addSoccerTeam(SoccerTeamResponse soccerTeamResponse) {
        SoccerTeamModel model = modelMapper.map(soccerTeamResponse, SoccerTeamModel.class);
        SoccerTeamModel savedModel = repository.save(model);
        return modelMapper.map(savedModel, SoccerTeamResponse.class);
    }

    @Override
    public List<SoccerTeamResponse> getAllSoccerTeams() {
        return repository.findAll().stream().map(equipo -> modelMapper.map(equipo, SoccerTeamResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteSoccerTeam(Integer id) {
        SoccerTeamResponse soccerTeamResponse = getSoccerTeamById(id);
        SoccerTeamModel soccerTeamModel = modelMapper.map(soccerTeamResponse, SoccerTeamModel.class);
        repository.delete(soccerTeamModel);
    }
}
