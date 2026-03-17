package com.digis01.GGarciaProgramacionNCapasMaven.Service;

import com.digis01.GGarciaProgramacionNCapasMaven.DAO.JPA.UsuarioDAOJPAImplementation;
import com.digis01.GGarciaProgramacionNCapasMaven.JPA.UsuarioJPA;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAOJPAImplementation usuarioDAOJPAImplementation;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioJPA usuario = usuarioDAOJPAImplementation.findByUserNameOrEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario: " + username));

        if (usuario.getEstatus() != 1) {
            throw new DisabledException("El usuario no se encuentra activo");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        String rolNombre = usuario.getRol() != null ? usuario.getRol().getNombre() : "USER";
        authorities.add(new SimpleGrantedAuthority(rolNombre));

        String rolNormalizado = "ROLE_" + rolNombre.trim().replace(" ", "_").toUpperCase();
        authorities.add(new SimpleGrantedAuthority(rolNormalizado));

        return User.withUsername(usuario.getUserName())
                .password(usuario.getPassword())
                .authorities(authorities)
                .build();
    }
}

