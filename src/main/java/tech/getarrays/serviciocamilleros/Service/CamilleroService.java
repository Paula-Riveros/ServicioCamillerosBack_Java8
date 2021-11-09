package tech.getarrays.serviciocamilleros.Service;

import org.springframework.stereotype.Service;
import tech.getarrays.serviciocamilleros.Model.Camillero;
import tech.getarrays.serviciocamilleros.Repo.CamilleroRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CamilleroService {

    private CamilleroRepo camilleroRepo;

    public CamilleroService(CamilleroRepo camilleroRepo) {
        this.camilleroRepo = camilleroRepo;
    }

    public List<Camillero> list() { return camilleroRepo.findAll(); }

    public Optional<Camillero> getOne(int idCamillero) {
        return camilleroRepo.findById(idCamillero);
    }

    public void save(Camillero camillero){
        camilleroRepo.save(camillero);
    }

    public Camillero update(Camillero camillero){
        return camilleroRepo.save(camillero);
    }

    public boolean existsById(int idCamillero) {
        return camilleroRepo.existsById(idCamillero);
    }

    public boolean delete(int idCamillero) {
        if (getOne(idCamillero).isPresent()) {
            camilleroRepo.deleteById(idCamillero);
            return true;
        } else {
            return false;
        }
    }


}