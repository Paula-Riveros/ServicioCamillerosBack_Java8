package tech.getarrays.serviciocamilleros.Security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {
    private final Usuario usuario;

    public UsuarioPrincipal(Usuario usuario) {
        this.usuario = usuario;
    }
    //private String nombre;
    //private String nombreusuario;
    //private String email;
    //private String password;
    //private Collection<? extends GrantedAuthority> authorities;

    /*public UsuarioPrincipal(String nombre, String nombreusuario, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.nombreusuario = nombreusuario;
        this.password = password;
        this.authorities = authorities;
    }*/

    /*public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getPassword(), authorities);
    }*/

    // authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
    }

    // password
    @Override
    public String getPassword() {
        return this.usuario.getPassword();
    }

    // nombreusuario
    @Override
    public String getUsername() {
        return this.usuario.getNombreUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // nombre
    public String getNombre() {
        return this.usuario.getNombre();
    }

    /*public String getEmail() {
        return email;
    }*/
}
