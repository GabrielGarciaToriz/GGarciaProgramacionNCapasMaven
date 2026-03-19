package com.digis01.GGarciaProgramacionNCapasMaven.Configuration;

import com.digis01.GGarciaProgramacionNCapasMaven.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/error", "/login", "/perform_login", "/access-denied").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/form").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/form").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/usuario/getEstadoByPais/**",
                                "/usuario/getMunicipioByEstado/**",
                                "/usuario/getColoniabyMunicipio/**",
                                "/usuario/getDireccionByCodigoPostal/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/direccion/editar/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN", "Administrador")
                        .requestMatchers(HttpMethod.POST,
                                "/usuario/editarUsuario",
                                "/usuario/agregarDireccion",
                                "/usuario/modificarDireccion",
                                "/usuario/detail/delete/**",
                                "/usuario/cambiarEstatus",
                                "/usuario/procesarCargaMasiva").hasAnyAuthority("ADMIN", "ROLE_ADMIN", "Administrador")
                        .requestMatchers(HttpMethod.GET, "/usuario/cargar").hasAnyAuthority("ADMIN", "ROLE_ADMIN", "Administrador")
                        .requestMatchers("/usuario/**").authenticated()
                        .anyRequest().authenticated())
                .authenticationProvider(daoAuthenticationProvider)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/usuario", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied"))
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

