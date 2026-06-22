package com.sena.database_connection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.repository.AmbienteRepository;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    public Ambiente guardar(Ambiente ambiente) {
        return ambienteRepository.save(ambiente);
    }

    public List<Ambiente> listarTodos() {
        return ambienteRepository.findAll();
    }
}
