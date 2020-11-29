package ru.dolinini.springsecuritydemoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.dolinini.springsecuritydemoproject.model.Role;

@Configuration
@EnableWebSecurity
public class SpringSecurityConf  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();



    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                                User.builder()
                                    .username("admin")
                                    .password(passwordEncoder().encode("12345"))
                                    .roles(Role.ADMIN.name())
                                    .build(),

                                User.builder()
                                    .username("user")
                                    .password(passwordEncoder().encode("1234"))
                                    .roles(Role.USER.name())
                                    .build()
        );
    }
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
