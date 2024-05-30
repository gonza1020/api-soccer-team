package com.dux.api_soccer_team.repository;

import com.dux.api_soccer_team.model.SoccerTeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoccerTeamRepository extends JpaRepository<SoccerTeamModel, Integer> {

    SoccerTeamModel findByNombre(String nombre);
}
