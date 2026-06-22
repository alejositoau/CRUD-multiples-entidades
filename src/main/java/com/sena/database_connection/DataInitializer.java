package com.sena.database_connection;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.model.EstadoReserva;
import com.sena.database_connection.model.Reserva;
import com.sena.database_connection.model.TipoAmbiente;
import com.sena.database_connection.repository.AmbienteRepository;
import com.sena.database_connection.repository.ReservaRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AmbienteRepository ambienteRepository;
    private final ReservaRepository reservaRepository;

    public DataInitializer(AmbienteRepository ambienteRepository, ReservaRepository reservaRepository) {
        this.ambienteRepository = ambienteRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ambienteRepository.count() == 0) {

            Ambiente sala1 = new Ambiente();
            sala1.setNombre("Sala de Sistemas 101");
            sala1.setTipo(TipoAmbiente.SALA);
            sala1.setCapacidad(30);
            sala1.setActivo(true);
            ambienteRepository.save(sala1);

            Ambiente labBio = new Ambiente();
            labBio.setNombre("Laboratorio de Biotecnología");
            labBio.setTipo(TipoAmbiente.LABORATORIO);
            labBio.setCapacidad(25);
            labBio.setActivo(true);
            ambienteRepository.save(labBio);

            Ambiente auditorio = new Ambiente();
            auditorio.setNombre("Auditorio Principal Miguel de Cervantes");
            auditorio.setTipo(TipoAmbiente.AUDITORIO);
            auditorio.setCapacidad(100);
            auditorio.setActivo(true);
            ambienteRepository.save(auditorio);

            Ambiente salaInactiva = new Ambiente();
            salaInactiva.setNombre("Sala de Idiomas 204 (En Mantenimiento)");
            salaInactiva.setTipo(TipoAmbiente.SALA);
            salaInactiva.setCapacidad(20);
            salaInactiva.setActivo(false); 
            ambienteRepository.save(salaInactiva);

            System.out.println(">> ¡Ambientes de prueba cargados exitosamente! ");

            LocalDateTime manana = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);

            Reserva reservaPrevia = new Reserva();
            reservaPrevia.setAmbiente(sala1);
            reservaPrevia.setNombreInstructor("Instructor Carlos Restrepo");
            reservaPrevia.setFechaInicio(manana);
            reservaPrevia.setFechaFin(manana.plusHours(3));
            reservaPrevia.setNumeroAprendices(25);
            reservaPrevia.setEstado(EstadoReserva.ACTIVA);
            reservaRepository.save(reservaPrevia);

            System.out.println(">> ¡Reservas iniciales cargadas exitosamente! ");
        }
    }
}