package com.sena.database_connection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.repository.AmbienteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    @Autowired
    private AmbienteRepository ambienteRepository;

    public List<Ambiente> findAll() {
        return ambienteRepository.findAll();
    }

    public Optional<Ambiente> findById(Long id) {
        return ambienteRepository.findById(id);
    }

    public Ambiente save(Ambiente ambiente) {
        return ambienteRepository.save(ambiente);
    }

    public void deleteById(Long id) {
        ambienteRepository.deleteById(id);
    }
}