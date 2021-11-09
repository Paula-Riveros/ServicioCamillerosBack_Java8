package tech.getarrays.serviciocamilleros.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.serviciocamilleros.Dto.CamilleroDto;
import tech.getarrays.serviciocamilleros.Dto.Mensaje;
import tech.getarrays.serviciocamilleros.Model.Camillero;
import tech.getarrays.serviciocamilleros.Service.CamilleroService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/camillero")
@CrossOrigin(origins = "*")
public class CamilleroController {
    private CamilleroService camilleroService;

    public CamilleroController(CamilleroService camilleroService) {
        this.camilleroService = camilleroService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<Camillero>> lista() {
        List<Camillero> camilleros = camilleroService.list();
        return new ResponseEntity<>(camilleros, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Camillero> getById(@PathVariable("id") int idCamillero) {
        if (!camilleroService.existsById(idCamillero))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Camillero camillero = camilleroService.getOne(idCamillero).get();
        return new ResponseEntity<>(camillero, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CamilleroDto camilleroDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o inválidos"), HttpStatus.BAD_REQUEST);
        if (camilleroService.existsById(camilleroDto.getIdCamillero()))
            return new ResponseEntity(new Mensaje("Ya existe un camillero con ese número de documento"), HttpStatus.BAD_REQUEST);

        Camillero camillero = new Camillero(camilleroDto.getIdCamillero(), camilleroDto.getNombreCamillero(), camilleroDto.isEstadoCamillero());
        camilleroService.save(camillero);
        return new ResponseEntity<>(new Mensaje("Camillero guardado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody CamilleroDto camilleroDto) {
        if(!camilleroService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);

        if (StringUtils.isBlank(camilleroDto.getNombreCamillero()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Camillero camillero = camilleroService.getOne(id).get();
        camillero.setNombreCamillero(camilleroDto.getNombreCamillero());
        camillero.setEstadoCamillero(camilleroDto.isEstadoCamillero());
        camilleroService.save(camillero);
        return new ResponseEntity<>(new Mensaje("Camillero actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPERADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Camillero> delete(@PathVariable("id") int id) {
        if (camilleroService.delete(id)) {
            return new ResponseEntity(new Mensaje("Camillero eliminado"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
