package tech.getarrays.serviciocamilleros.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.serviciocamilleros.Model.Genareser;
import tech.getarrays.serviciocamilleros.Repo.GenareserRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenareserService {

    @Autowired
    GenareserRepo genareserRepo;

    public List<Genareser> list() {
        return genareserRepo.findAll();
    }

    public Optional<Genareser> getOne(int oid) {
        return genareserRepo.findById(oid);
    }

    public boolean existsById(int oid) {
        return genareserRepo.existsById(oid);
    }
}
