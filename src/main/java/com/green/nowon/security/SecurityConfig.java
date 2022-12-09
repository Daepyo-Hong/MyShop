package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    //DB의 인증정보를 이용해서 인증처리하는 service 커스터마이징한 빈
    @Bean
    MyUserDetailsService customUserDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/css/**").permitAll()
                        .antMatchers("/","/member/signup").permitAll()
                        .antMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->form
                        .loginPage("/member/login")             //[GET]
                        .loginProcessingUrl("/member/login")    //[POST] form태그의 action
                        .usernameParameter("email")         //username -> email
                        .passwordParameter("pass")          //password -> pass
                        .permitAll()

                )
                .csrf(csrf->csrf.disable())
        ;
        return http.build();
    }
}
