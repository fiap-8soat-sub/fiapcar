package com.fiap.fiapcar.adapter.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    private final CognitoProperties props;
    public SecurityConfiguration(CognitoProperties props) { this.props = props; }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String jwk = "https://cognito-idp." + props.region()
                + ".amazonaws.com/" + props.userPoolId() + "/.well-known/jwks.json";

        http.csrf(csrf -> csrf.disable())
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/car/**").permitAll()
                        .requestMatchers("/brand/**").permitAll()
                        .requestMatchers("/customer/**").permitAll()
                        .requestMatchers("/sale/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwkSetUri(jwk)));

        return http.build();
    }
}

