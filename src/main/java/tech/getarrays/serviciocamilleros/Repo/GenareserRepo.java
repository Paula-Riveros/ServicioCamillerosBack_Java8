package tech.getarrays.serviciocamilleros.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.serviciocamilleros.Model.Genareser;

@Repository
public interface GenareserRepo extends JpaRepository<Genareser, Integer> {
}

