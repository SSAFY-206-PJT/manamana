package com.webtoon.manamana.config;

import com.webtoon.manamana.auth.TokenAuthenticationFilter;
import com.webtoon.manamana.auth.exception.TokenAccessDeniedHandler;
import com.webtoon.manamana.auth.exception.TokenAuthenticationEntryPoint;
import com.webtoon.manamana.auth.handler.CustomOAuth2FailureHandler;
import com.webtoon.manamana.auth.handler.CustomOAuth2SuccessHandler;
import com.webtoon.manamana.auth.oauth2.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomOAuth2FailureHandler customOAuth2FailureHandler;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and() //세션사용 x
                .csrf().disable()//csrf 미사용 - rest api는 stateless 형태이므로 필요없음
                .headers().frameOptions().disable()
            .and()
                .formLogin().disable() //로그인 폼 미사용
                .httpBasic().disable();
//                exceptionHandling().authenticationEntryPoint(new RestAuth) //인증핸들러 등록

        //TODO : 테스트를 위해서 잠시 모든 경로 허용하도록.
        //허용 url
        http
                .authorizeRequests()
                .antMatchers("/auth/**","/oauth2/**", "/token/**","/webtoons/list/**").permitAll()
                .anyRequest().authenticated()
            .and() //나머지는 모두 인증 필요.
//                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login()
//            .and()
//                .redirectionEndpoint().baseUri("/oauth2/callback/*") //소셜인증후 리다이렉트
                .userInfoEndpoint().userService(customOAuth2UserService) //소셜의 회원정보를 받아와서 처리.
            .and()
                .successHandler(customOAuth2SuccessHandler) //인증 성공시 처리할 핸들러 - jwt로 토큰만드는 로직
                .failureHandler(customOAuth2FailureHandler); //실패시 처리할 핸들러.

        //필터 추가.
        http
                .addFilterBefore(tokenAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

        //인증, 인가 예외처리 등록
        http
            .exceptionHandling()
                .authenticationEntryPoint(tokenAuthenticationEntryPoint) //인증실패시
                .accessDeniedHandler(tokenAccessDeniedHandler); //인가 실패시.

        return http.build();
    }

}
