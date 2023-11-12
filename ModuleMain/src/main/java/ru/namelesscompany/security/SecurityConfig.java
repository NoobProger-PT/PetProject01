package ru.namelesscompany.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServ userDetailsServ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/public/**").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/forAll/**").permitAll()
                .antMatchers(HttpMethod.POST, "/private/users").permitAll()
                .antMatchers("/private/users/**").hasAnyRole("USER", "SUBADMIN", "OVERLORD")
                .antMatchers("/private/subAdmin/**").hasAnyRole("SUBADMIN", "OVERLORD")
                .antMatchers("/admin/mpaa", "/admin/mpaa/**").hasAnyRole("SUBADMIN", "OVERLORD")
                .antMatchers("/admin/genre", "/admin/genre/**").hasAnyRole("SUBADMIN", "OVERLORD")
                .antMatchers(HttpMethod.GET, "/private/mpaa/**").hasAnyRole("SUBADMIN", "USER", "OVERLORD")
                .antMatchers(HttpMethod.GET,"/private/genre/**").hasAnyRole("SUBADMIN", "USER", "OVERLORD")
                .antMatchers("/admin/films", "/admin/films/**").hasAnyRole("SUBADMIN", "OVERLORD")
                .antMatchers(HttpMethod.GET, "/private/films/**").hasAnyRole("SUBADMIN", "USER", "OVERLORD")
                .antMatchers("/**").hasRole("OVERLORD")
                .and()
                .csrf().disable()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsServ);
        return authenticationProvider;
    }
}
