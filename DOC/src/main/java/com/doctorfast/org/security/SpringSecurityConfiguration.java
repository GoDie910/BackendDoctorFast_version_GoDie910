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
                .antMatchers("/usuario/roles").hasRole("ADMINISTRADOR")
                .antMatchers("/usuario/roles").hasRole("DOCTOR")
                .antMatchers("/doctor/calificar").hasRole("PACIENTE")
                .antMatchers("/login","/api/auth","/api/auth/login", "/usuario/pacientes",
                        "/usuario/registroPaciente","/usuario/registroDoctor","/usuario/prueba","/prueba","/list",
                        "/usuario/list","/doctor/list","/doctor/edit/**","/doctor/detail/**","/edit/**",
                        "/doctor/list","/doctor/doctor-numero","/doctor/listEspecialidad",
                        "/paciente/list","/paciente/paciente-numero",
                        "/usuario/registroAdmin", "/admin/list", "/admin/edit/**", "/admin/especialidades",
                        "/cita/cita-numero","/cita/list", "/doctor/por_distrito/**", "/doctor/por_rating/**",
                        "/usuario/cambiarpassword","/doctor/ratingpromedio/**").permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
