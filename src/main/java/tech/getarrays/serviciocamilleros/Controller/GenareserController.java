package tech.getarrays.serviciocamilleros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.serviciocamilleros.Dto.Mensaje;
import tech.getarrays.serviciocamilleros.Model.Genareser;
import tech.getarrays.serviciocamilleros.Service.GenareserService;

import java.util.List;

@RestController
@RequestMapping("/genareser")
@CrossOrigin(origins = "*")
public class GenareserController {

    @Autowired
    GenareserService genareserService;

    @GetMapping("/lista")
    public ResponseEntity<List<Genareser>> lista() {
        List<Genareser> list = genareserService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Genareser> getById(@PathVariable("id") int id) {
        if(!genareserService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Genareser genareser = genareserService.getOne(id).get();
        return new ResponseEntity<>(genareser, HttpStatus.OK);
    }
}
