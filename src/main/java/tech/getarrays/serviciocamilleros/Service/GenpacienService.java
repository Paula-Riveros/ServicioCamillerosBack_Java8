package tech.getarrays.serviciocamilleros.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.serviciocamilleros.Model.Genpacien;
import tech.getarrays.serviciocamilleros.Repo.GenpacienRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenpacienService {

    @Autowired
    GenpacienRepo genpacienRepo;

    public List<Genpacien> list() {
        return genpacienRepo.findAll();
    }

    public Optional<Genpacien> getOne(int oid) {
        return genpacienRepo.findById(oid);
    }

    public Optional<Genpacien> getByPacnumdoc(String pacnumdoc) {
        return genpacienRepo.findByPacnumdoc(pacnumdoc);
    }

    public boolean existsById(int oid) {
        return genpacienRepo.existsById(oid);
    }

    public boolean existsByPacnumdoc(String pacnumdoc) {
        return genpacienRepo.existsByPacnumdoc(pacnumdoc);
    }

}

