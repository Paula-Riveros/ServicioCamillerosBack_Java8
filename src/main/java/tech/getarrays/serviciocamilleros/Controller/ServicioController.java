package tech.getarrays.serviciocamilleros.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.serviciocamilleros.Dto.Mensaje;
import tech.getarrays.serviciocamilleros.Dto.ServicioDto;
import tech.getarrays.serviciocamilleros.Model.Camillero;
import tech.getarrays.serviciocamilleros.Model.Genareser;
import tech.getarrays.serviciocamilleros.Model.Genpacien;
import tech.getarrays.serviciocamilleros.Repo.CamilleroRepo;
import tech.getarrays.serviciocamilleros.Repo.GenareserRepo;
import tech.getarrays.serviciocamilleros.Repo.GenpacienRepo;
import tech.getarrays.serviciocamilleros.Service.GenpacienService;
import tech.getarrays.serviciocamilleros.Service.ServicioService;
import tech.getarrays.serviciocamilleros.Model.Servicio;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicio")
@CrossOrigin(origins = "*")
public class ServicioController {
    private final ServicioService servicioService;
    private final GenpacienService genpacienService;
    private CamilleroRepo camilleroRepo;
    private GenpacienRepo genpacienRepo;
    private GenareserRepo genareserRepo;

    //
    public ServicioController(ServicioService servicioService, CamilleroRepo camilleroRepo,
                              GenpacienService genpacienService, GenpacienRepo genpacienRepo, GenareserRepo genareserRepo) {
        this.servicioService = servicioService;
        this.genpacienService = genpacienService;
        this.camilleroRepo = camilleroRepo;
        this.genpacienRepo = genpacienRepo;
        this.genareserRepo = genareserRepo;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        List<Servicio> servicios = servicioService.list();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable("id") Long id) {
        if (!servicioService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Servicio servicio = servicioService.getOne(id).get();
        return new ResponseEntity<>(servicio, HttpStatus.OK);
    }

  /*  @GetMapping("/listafecha/{fecha}")
    public ResponseEntity<List<Servicio>> getByFecha(@PathVariable("fecha") LocalDate fecha) {
        if(!servicioService.existsByFecha(fecha))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        List<Servicio> servicios = servicioService.getByFecha(fecha).get();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }*/

    @GetMapping("/listafecha")
    public ResponseEntity<List<Servicio>> getByFecha(@RequestBody ServicioDto servicioDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(servicioDto.getFecha(), formatter);
        if(!servicioService.existsByFecha(date))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        List<Servicio> servicios = servicioService.getByFecha(date);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ServicioDto servicioDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Campos mal puestos o inválidos"), HttpStatus.BAD_REQUEST);
        Optional<Genareser> genareser = genareserRepo.findById(servicioDto.getServicioSolicitado());
        Optional<Genareser> genareser2 = genareserRepo.findById(servicioDto.getDestinoServicio());

        Optional<Genpacien> genpacien = genpacienRepo.findByPacnumdoc(servicioDto.getDocPaciente());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(servicioDto.getFecha(), formatter);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime horaEnvio = LocalTime.parse(servicioDto.getHoraEnvio(), dtf);
        LocalTime horaAsignacion = LocalTime.parse(servicioDto.getHoraAsignacion(), dtf);
        LocalTime horaEjecucion = LocalTime.parse(servicioDto.getHoraEjecucion(), dtf);
        LocalTime horaFinalizacion = LocalTime.parse(servicioDto.getHoraFinalizacion(), dtf);
        LocalTime horaCancelacion = LocalTime.parse(servicioDto.getHoraCancelacion(), dtf);

        //boolean aislamiento = Boolean.parseBoolean(servicioDto.getAislamiento());

        if(genpacien.isPresent()) {
            Servicio servicio = new Servicio(date, genareser.get(), servicioDto.getSolicitante(), genareser2.get(),
                    servicioDto.getTransporte(), servicioDto.getInsumo(), servicioDto.getFamiliar(),
                    servicioDto.isAislamiento(), servicioDto.getObservaciones(), genpacien.get(), null, horaEnvio,
                    horaAsignacion, horaEjecucion, horaFinalizacion, servicioDto.getTiempoTotal(),
                    servicioDto.isCancelado(), servicioDto.getMotivoCancelado(), horaCancelacion);
            servicioService.save(servicio);
            return new ResponseEntity<>(new Mensaje("Servicio guardado"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ServicioDto servicioDto) {
        if(!servicioService.existsById(id))
            return new ResponseEntity<>(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(servicioDto.getFecha()))
            return new ResponseEntity<>(new Mensaje("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);

        Optional<Camillero> camillero = camilleroRepo.findById(servicioDto.getIdCamillero());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime horaAsignacion = LocalTime.parse(servicioDto.getHoraAsignacion(), dtf);
        LocalTime horaEjecucion = LocalTime.parse(servicioDto.getHoraEjecucion(), dtf);
        LocalTime horaFinalizacion = LocalTime.parse(servicioDto.getHoraFinalizacion(), dtf);

        Servicio servicio = servicioService.getOne(id).get();
        servicio.setCamillero(camillero.get());
        servicio.setHoraAsignacion(horaAsignacion);
        servicio.setHoraEjecucion(horaEjecucion);
        servicio.setHoraFinalizacion(horaFinalizacion);
        servicioService.save(servicio);
        return new ResponseEntity<>(new Mensaje("Servicio actualizado"), HttpStatus.OK);
    }

    @PutMapping("/updatetime")
    public ResponseEntity<Servicio> updateTime(@RequestBody Servicio servicio) {
        Servicio updateTime = servicioService.updateTimeServicio(servicio);
        return new ResponseEntity(new Mensaje("Hora guardada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
    @PutMapping("/updatecancelado")
    public ResponseEntity<Servicio> updateCancelado(@RequestBody Servicio servicio) {
        Servicio updateCancelado = servicioService.updateTimeServicio(servicio);
        return new ResponseEntity(new Mensaje("Servicio cancelado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('SUPERADMIN')")
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Servicio> delete(@PathVariable Long id){
        if(servicioService.delete(id)) {
            return new ResponseEntity(new Mensaje("Servicio eliminado"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
