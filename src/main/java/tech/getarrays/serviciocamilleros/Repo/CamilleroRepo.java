package tech.getarrays.serviciocamilleros.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.serviciocamilleros.Model.Camillero;

@Repository
public interface CamilleroRepo extends JpaRepository<Camillero, Integer> {
}
