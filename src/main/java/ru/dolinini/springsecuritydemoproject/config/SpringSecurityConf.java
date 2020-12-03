package ru.dolinini.springsecuritydemoproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.dolinini.springsecuritydemoproject.model.Permission;
import ru.dolinini.springsecuritydemoproject.model.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityConf  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/auth/login");



    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                                User.builder()
                                    .username("admin")
                                    .password(passwordEncoder().encode("12345"))
                                    .authorities(Role.ADMIN.getAuthorities())
                                    .build(),

                                User.builder()
                                    .username("user")
                                    .password(passwordEncoder().encode("1234"))
                                    .authorities(Role.USER.getAuthorities())
                                    .build()
        );
    }
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
