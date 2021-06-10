package com.doctorfast.org.security;

import com.doctorfast.org.security.jwt.AuthEntryPointJwt;
import com.doctorfast.org.security.jwt.AuthTokenFilter;
import com.doctorfast.org.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthTokenFilter customJwtAuthenticationFilter;

    @Autowired
    private AuthEntryPointJwt jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/admin/admin/list",
                        "/admin/cita/list",
                        "/admin/cita/cita-numero",
                        "/admin/paciente/list",
                        "/admin/paciente/paciente-numero",
                        "/admin/usuario/roles",
                        "/admin/usuario/list",
                        "/admin/doctor/list",
                        "/admin/doctor/**",
                        "/admin/doctor/detail/**",
                        "/admin/doctor/edit/**",
                        "/admin/doctor/ratingpromedio/**",
                        "/admin/doctor/doctor-numero",
                        "/admin/doctor/por_distrito/**",
                        "/admin/doctor/por_rating/**",
                        "/admin/doctor/por_rating/mejores",
                        "/admin/roles/por-numero"
                ).hasRole("ADMINISTRADOR")
                .antMatchers(
                        "/doctor/perfil/**",
                        "/doctor/citas/disponibles/**",
                        "/doctor/citas/pendientes/**",
                        "/doctor/citas/aceptar/**&**",
                        "/doctor/citas/cancelar/**&**",
                        "/doctor/citas/historial/**",
                        "/doctor/status/**",
                        "/doctor/citas/crear_diagnosticos",
                        "/doctor/citas/crear_prescripciones_medicas",
                        "/doctor/citas/realizar_cita",
                        "/doctor/citas/historial_medico/**",
                        "/doctor/medicamento/list"
                ).hasRole("DOCTOR")
                .antMatchers(
                        "/paciente/doctor/calificar",
                        "/paciente/perfil/**",
                        "/paciente/doctor/disponibles",
                        "/paciente/citas/crear",
                        "/paciente/citas/disponibles/**",
                        "/paciente/citas/pendientes/**",
                        "/paciente/citas/historial/**",
                        "/paciente/citas/historial_medico/**",
                        "/paciente/area_sintoma/list"
                ).hasRole("PACIENTE")
                .antMatchers(
                        "/api/auth/login",
                        "/usuario/registro",
                        "/usuario/registroPaciente",
                        "/usuario/registroDoctor",
                        "/usuario/registroAdmin",
                        "/usuario/ubicacion/obtener/**",
                        "/usuario/ubicacion/actualizar",
                        "/admin/especialidades",
                        "/usuario/cambiarpassword" //revisalo, asegurate que funciona
                        ).permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
