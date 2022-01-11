package tech.getarrays.serviciocamilleros.Security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.serviciocamilleros.Security.entity.Usuario;
import tech.getarrays.serviciocamilleros.Security.repository.UsuarioRepository;
import tech.getarrays.serviciocamilleros.exception.ObjectNotFoundException;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findUsuariosByUsername(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(()-> new ObjectNotFoundException("El usuario "+nombreUsuario+" no se ha encontrado"));
    }

}