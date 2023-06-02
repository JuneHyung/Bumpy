package com.bump.bumpy.security;

import com.bump.bumpy.security.principal.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final PrincipalDetailsService principalDetailsService;

    @Value("${cors.origins[0]}")
    private String origin;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin()
                    .loginProcessingUrl("/auth/login")
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .successHandler((request, response, authentication) -> {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("{\"message\":\"로그인 성공\", \"code\":true}");
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
                    .failureHandler((request, response, exception) -> {
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().write("{\"message\":\"로그인에 실패하였습니다.\", \"code\":false}");
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/auth/logout")
                    .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                    .deleteCookies("JSESSIONID")
                .and()

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))

                // 경로 접근 처리
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/signup/**").permitAll()
                        .antMatchers("/swagger*/**", "/webjars/**", "/v3/**", "/document/**").permitAll()
                        .anyRequest().permitAll()
//                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // AuthenticationManager 설정 - userDetails구현체, bCryptPasswordEncoder 적용
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(principalDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedOriginPatterns(List.of(origin));
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
