package tech.getarrays.serviciocamilleros.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.serviciocamilleros.Model.Servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ServicioRepo extends JpaRepository<Servicio, Long> {
    List<Servicio> findByFecha(LocalDate fecha);
    boolean existsByFecha(LocalDate fecha);


}
