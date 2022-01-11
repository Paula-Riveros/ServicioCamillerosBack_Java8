package tech.getarrays.serviciocamilleros.Security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.serviciocamilleros.Dto.Mensaje;
import tech.getarrays.serviciocamilleros.Security.dto.JwtDto;
import tech.getarrays.serviciocamilleros.Security.dto.LoginUsuario;
import tech.getarrays.serviciocamilleros.Security.dto.NuevoUsuario;
import tech.getarrays.serviciocamilleros.Security.entity.Rol;
import tech.getarrays.serviciocamilleros.Security.entity.Usuario;
import tech.getarrays.serviciocamilleros.Security.enums.RolNombre;
import tech.getarrays.serviciocamilleros.Security.jwt.JwtProvider;
import tech.getarrays.serviciocamilleros.Security.service.RolService;
import tech.getarrays.serviciocamilleros.Security.service.UsuarioService;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    // Crear nuevo usuario
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        if (nuevoUsuario.getRoles().contains("superadmin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUPERADMIN).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    // Iniciar Sesion
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Usuario o contraseña incorrecta"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //int cambioClave = usuarioService.findUsuariosByUsername(userDetails.getUsername()).getCambioClave();
        // , cambioClave
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    // Refrescar token
    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        // , jwtDto.getCambioClave()
        JwtDto jwt = new JwtDto(token, jwtDto.getNombreUsuario(), jwtDto.getAuthorities());
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @GetMapping("/find/username={username}")
    public ResponseEntity<Usuario> findUsuariosByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(usuarioService.findUsuariosByUsername(username));
    }

    /*@PutMapping("/usuario/cambiar-clave/old={oldPassword}/new={newPassword}")
    public ResponseEntity<Mensaje> cambiarClave(@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword, Principal user) {
        String nombreUsuario = user.getName();
        System.out.println(nombreUsuario);
        Mensaje mensaje;
        Usuario currentUser = usuarioService.findUsuariosByUsername(nombreUsuario);
        if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            currentUser.setCambioClave(1);
            usuarioService.save(currentUser);
            mensaje = new Mensaje("Cambio de contraseña exitoso");
        }
        else{
            mensaje = new Mensaje("Contraseña actual incorrecta");
        }
        return new ResponseEntity<>(mensaje,HttpStatus.OK);
    }

    @PutMapping("/reset-clave/username={username}/reset={newPassword}")
    public ResponseEntity<Mensaje> resetClave(@PathVariable("newPassword") String newPassword, @PathVariable("username") String username){
        Usuario currentUser = usuarioService.findUsuariosByUsername(username);
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        currentUser.setCambioClave(0);
        usuarioService.save(currentUser);
        return new ResponseEntity<>(new Mensaje("Clave Reseteada"),HttpStatus.OK);
    }*/

}