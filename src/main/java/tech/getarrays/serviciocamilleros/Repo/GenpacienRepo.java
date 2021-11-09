package tech.getarrays.serviciocamilleros.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.serviciocamilleros.Model.Genpacien;

import java.util.Optional;

@Repository
public interface GenpacienRepo extends JpaRepository<Genpacien, Integer> {
    Optional<Genpacien> findByPacnumdoc(String pacnumdoc);
    boolean existsByPacnumdoc(String pacnumdoc);
}
