package by.dubrovsky.LibrarySystem.config;

import by.dubrovsky.LibrarySystem.models.User;
import by.dubrovsky.LibrarySystem.repositories.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String sub = (String) map.get("sub");

            User user = userRepository.findBySub(sub).orElseGet(() -> {
                User newUser = new User();

                newUser.setFirstName((String) map.get("given_name"));
                newUser.setSecondName((String) map.get("family_name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setPicture((String) map.get("picture"));
                newUser.setSub((String) map.get("sub"));
                newUser.setCreationDate(LocalDateTime.now());
                newUser.setLocale((String) map.get("locale"));

                return newUser;
            });

            return userRepository.save(user);
        };
    }
}
