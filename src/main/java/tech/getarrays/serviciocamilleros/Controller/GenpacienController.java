package tech.getarrays.serviciocamilleros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.serviciocamilleros.Dto.Mensaje;
import tech.getarrays.serviciocamilleros.Model.Genpacien;
import tech.getarrays.serviciocamilleros.Service.GenpacienService;

import java.util.List;

@RestController
@RequestMapping("/genpacien")
@CrossOrigin(origins = "*")
public class GenpacienController {

    @Autowired
    GenpacienService genpacienService;

    // Listar pacientes
    @GetMapping("/lista")
    public ResponseEntity<List<Genpacien>> lista() {
        List<Genpacien> list = genpacienService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Ver paciente por id
    @GetMapping("/detail/{id}")
    public ResponseEntity<Genpacien> getById(@PathVariable("id") int id) {
        if(!genpacienService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Genpacien genpacien = genpacienService.getOne(id).get();
        return new ResponseEntity<>(genpacien, HttpStatus.OK);
    }

    // Ver paciente por numero de documento
    @GetMapping("/detail/pacnumdoc/{pacnumdoc}")
    public ResponseEntity<Genpacien> getByPacnumdoc(@PathVariable("pacnumdoc") String pacnumdoc) {
        if(!genpacienService.existsByPacnumdoc(pacnumdoc))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Genpacien genpacien = genpacienService.getByPacnumdoc(pacnumdoc).get();
        return new ResponseEntity<>(genpacien, HttpStatus.OK);
    }
}
