package com.example.carsalesserver.security.config;


import com.example.carsalesserver.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTTokenHelper jwtTokenHelper;
    private final AuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/api/v*/*/**")
//                    .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin();
//
//        http.httpBasic();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/api/v1/auth/userinfo").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/auth/userinfo").authenticated()
                .antMatchers("/api/v*/*/**").permitAll()
//                .antMatchers("/api/v*/*/**").access("hasRole('ADMIN') or hasRole('USER')")
//                .antMatchers("/api/v*/*/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(new JWTAuthenticationFilter(appUserService, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class);

//        http.csrf().disable().cors().and().headers().frameOptions().disable();

//        http.cors();

//        http.csrf().disable().headers().frameOptions().disable();

        http.csrf().disable();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Cata").password(bCryptPasswordEncoder.encode("password")).authorities("USER", "ADMIN");
        auth.authenticationProvider(daoAuthenticationProvider());
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }


}
