package niesuv.project.film.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import niesuv.project.film.SecurityFilter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@NoArgsConstructor
@Setter
@Getter
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> {session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);});
        http.authorizeHttpRequests(req -> {
            req.requestMatchers("/users/signup").permitAll();
            req.requestMatchers("/users/signin").permitAll();
            req.requestMatchers("films/**").permitAll();
            req.anyRequest().hasAuthority("USER");
        });

        http.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);
        return http.build();
    }
 }
