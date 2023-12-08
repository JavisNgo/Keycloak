package com.example.keycloak3.config;

import com.example.keycloak3.jwt.JwtAuthConverter;
import com.example.keycloak3.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtAuthConverter jwtAuthConverter;

    public static final String[] ADMIN_URL = {
            "/api/admin",
            "/api/admin/demo"
    };
    public static final String[] MANAGER_URL = {
            "/api/manager",
            "/api/staff"
    };
    public static final String[] WHITE_LIST_URL = {
            "/api/register",
            "/api/tenant/demo"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(t -> t.disable());
        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers(ADMIN_URL).hasRole("admin")
                    .requestMatchers(MANAGER_URL).hasRole("manager")
                    .requestMatchers(WHITE_LIST_URL).permitAll()
                    .anyRequest().authenticated();
        });
        http.oauth2ResourceServer(t-> {
            //t.jwt(Customizer.withDefaults());
            t.jwt(configuer ->
            configuer.jwtAuthenticationConverter(jwtAuthConverter));
        });
        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public DefaultMethodSecurityExpressionHandler msecurity() {
//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler =
//                new DefaultMethodSecurityExpressionHandler();
//        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
//        return defaultMethodSecurityExpressionHandler;
//    }

//    @Bean
//    public JwtAuthenticationConverter con() {
//        JwtAuthenticationConverter c =new JwtAuthenticationConverter();
//        JwtGrantedAuthoritiesConverter cv = new JwtGrantedAuthoritiesConverter();
//        cv.setAuthorityPrefix(""); // Default "SCOPE_"
//        cv.setAuthoritiesClaimName("roles"); // Default "scope" or "scp"
//        c.setJwtGrantedAuthoritiesConverter(cv);
//        return c;
//    }

}