package mini_music_streaming.music_streaming.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(
        HttpSecurity http)

        throws Exception
    {
        http

        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(

        auth -> auth

        // Public
        .requestMatchers(
            "/auth/register",
            "/auth/login",
             "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
        "/v3/api-docs")

        .permitAll()

        // Admin only
        .requestMatchers(
            "/user/**")

        .hasRole("ADMIN")

        .requestMatchers(
            "/track/**")

        .hasRole("ADMIN")

        .requestMatchers(
            "/playlist/**")

        .hasRole("ADMIN")

        // Everything else requires login
        .anyRequest()

        .authenticated()
        )

        .sessionManagement(

        session ->
            session.sessionCreationPolicy(

                SessionCreationPolicy
                .STATELESS))

        .addFilterBefore(

        jwtFilter,

        UsernamePasswordAuthenticationFilter
        .class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception
    {
        return config
                .getAuthenticationManager();
    }
}
