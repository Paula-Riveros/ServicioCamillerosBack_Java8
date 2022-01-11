/*package tech.getarrays.serviciocamilleros.util;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.stereotype.Component;
 import tech.getarrays.serviciocamilleros.Security.entity.Rol;
 import tech.getarrays.serviciocamilleros.Security.enums.RolNombre;
 import tech.getarrays.serviciocamilleros.Security.service.RolService;

 @Component
 public class CreateRoles implements CommandLineRunner {

 @Autowired
 RolService rolService;
 @Override
 public void run(String... args) throws Exception {
 Rol rolSuper = new Rol(RolNombre.ROLE_SUPERADMIN);
 Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
 Rol rolUser = new Rol(RolNombre.ROLE_USER);
 rolService.save(rolSuper);
 rolService.save(rolAdmin);
 rolService.save(rolUser);
 }
 }*/

/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */