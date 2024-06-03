package com.dux.api_soccer_team.service;

import com.dux.api_soccer_team.dto.SoccerTeamResponse;
import com.dux.api_soccer_team.model.SoccerTeamModel;
import com.dux.api_soccer_team.repository.SoccerTeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SoccerTeamServiceImplTest {

    @Mock
    private SoccerTeamRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SoccerTeamServiceImpl service;

    private SoccerTeamModel soccerTeamModel;
    private SoccerTeamResponse soccerTeamResponse;

    @BeforeEach
    void setUp() {
        soccerTeamModel = new SoccerTeamModel();
        soccerTeamModel.setId(1);
        soccerTeamModel.setNombre("Team A");
        soccerTeamModel.setLiga("Old Liga");
        soccerTeamModel.setPais("Old Pais");

        soccerTeamResponse = new SoccerTeamResponse();
        soccerTeamResponse.setId(1);
        soccerTeamResponse.setNombre("Team A");
        soccerTeamResponse.setLiga("Old Liga");
        soccerTeamResponse.setPais("Old Pais");
    }

    @Test
    void testGetSoccerTeamById() {
        when(repository.findById(1)).thenReturn(Optional.of(soccerTeamModel));
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);

        SoccerTeamResponse result = service.getSoccerTeamById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Team A", result.getNombre());

        verify(repository).findById(1);
        verify(modelMapper).map(soccerTeamModel, SoccerTeamResponse.class);
    }

    @Test
    void testGetSoccerTeamByNombre() {
        when(repository.findByNombre("Team A")).thenReturn(soccerTeamModel);
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);

        SoccerTeamResponse result = service.getSoccerTeamByNombre("Team A");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Team A", result.getNombre());

        verify(repository).findByNombre("Team A");
        verify(modelMapper).map(soccerTeamModel, SoccerTeamResponse.class);
    }

    @Test
    void testGetSoccerTeamByNombre_NotFound() {
        when(repository.findByNombre("Team A")).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> {
            service.getSoccerTeamByNombre("Team A");
        });

        verify(repository).findByNombre("Team A");
    }

    @Test
    void testUpdateSoccerTeam() {
        SoccerTeamResponse updatedResponse = new SoccerTeamResponse();
        updatedResponse.setId(1);
        updatedResponse.setNombre("Team A Updated");
        updatedResponse.setLiga("New Liga");
        updatedResponse.setPais("New Pais");

        SoccerTeamModel updatedSoccerTeamModel = new SoccerTeamModel();
        updatedSoccerTeamModel.setId(1);
        updatedSoccerTeamModel.setNombre("Team A Updated");
        updatedSoccerTeamModel.setLiga("New Liga");
        updatedSoccerTeamModel.setPais("New Pais");

        // Mock the behavior of repository and modelMapper
        when(repository.findById(1)).thenReturn(Optional.of(soccerTeamModel));
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);
        when(modelMapper.map(soccerTeamResponse, SoccerTeamModel.class)).thenReturn(soccerTeamModel);
        when(repository.save(any(SoccerTeamModel.class))).thenReturn(updatedSoccerTeamModel);
        when(modelMapper.map(updatedSoccerTeamModel, SoccerTeamResponse.class)).thenReturn(updatedResponse);

        // Invoke the method under test
        SoccerTeamResponse result = service.updateSoccerTeam(1, updatedResponse);

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Team A Updated", result.getNombre());
        assertEquals("New Liga", result.getLiga());
        assertEquals("New Pais", result.getPais());

        // Verify interactions
        verify(repository).findById(1);
        verify(modelMapper).map(soccerTeamModel, SoccerTeamResponse.class);
        verify(modelMapper).map(soccerTeamResponse, SoccerTeamModel.class);
        verify(repository).save(any(SoccerTeamModel.class));
        verify(modelMapper).map(updatedSoccerTeamModel, SoccerTeamResponse.class);
    }

    @Test
    void testAddSoccerTeam() {
        when(modelMapper.map(soccerTeamResponse, SoccerTeamModel.class)).thenReturn(soccerTeamModel);
        when(repository.save(soccerTeamModel)).thenReturn(soccerTeamModel);
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);

        SoccerTeamResponse result = service.addSoccerTeam(soccerTeamResponse);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Team A", result.getNombre());

        verify(modelMapper).map(soccerTeamResponse, SoccerTeamModel.class);
        verify(repository).save(soccerTeamModel);
        verify(modelMapper).map(soccerTeamModel, SoccerTeamResponse.class);
    }

    @Test
    void testGetAllSoccerTeams() {
        List<SoccerTeamModel> soccerTeams = List.of(soccerTeamModel);
        when(repository.findAll()).thenReturn(soccerTeams);
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);

        List<SoccerTeamResponse> result = service.getAllSoccerTeams();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Team A", result.get(0).getNombre());

        verify(repository).findAll();
        verify(modelMapper).map(soccerTeamModel, SoccerTeamResponse.class);
    }

    @Test
    void testDeleteSoccerTeam() {
        // Configure mocks
        when(repository.findById(1)).thenReturn(Optional.of(soccerTeamModel));
        when(modelMapper.map(soccerTeamModel, SoccerTeamResponse.class)).thenReturn(soccerTeamResponse);
        when(modelMapper.map(soccerTeamResponse, SoccerTeamModel.class)).thenReturn(soccerTeamModel);

        // Invoke the method under test
        service.deleteSoccerTeam(1);

        // Verify interactions
        verify(repository).findById(1);
        verify(repository).delete(soccerTeamModel);
    }
}
