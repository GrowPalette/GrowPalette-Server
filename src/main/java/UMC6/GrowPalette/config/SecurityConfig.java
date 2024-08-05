package UMC6.GrowPalette.config;

import UMC6.GrowPalette.domain.user.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final UserDetailService userService;

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**");
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 비활성화 (개발 및 테스트 환경에서만)
                .authorizeRequests() // 인증 및 인가 설정
                .requestMatchers(
                        "/users/login",    // 로그인 API 허용
                        "/users/sign_up",  // 회원가입 API 허용
                        "/swagger-ui/**",  // Swagger UI 허용
                        "/v3/api-docs/**", // API 문서 허용
                        "/swagger-ui.html" // Swagger UI HTML 허용
                ).permitAll() // 지정된 경로들은 모두 인증 없이 접근 가능
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않고 JWT 기반 인증 사용
                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/users/logout") // 로그아웃 처리 URL
                .logoutSuccessUrl("/users/login?logout=true") // 로그아웃 성공 후 이동할 페이지
                .invalidateHttpSession(true) // 세션 무효화
                .permitAll()
                .and()
                .headers()
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, OPTIONS"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Authorization, Content-Type"));

        return http.build();
    }


    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}