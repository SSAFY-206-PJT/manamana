package com.webtoon.manamana.config;

import com.webtoon.manamana.auth.TokenAuthenticationFilter;
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

//    private final TokenAuthenticationFilter tokenAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //세션사용 x
                .csrf().disable()//csrf 미사용 - rest api는 stateless 형태이므로 필요없음
                .headers().frameOptions().disable().and()
                .formLogin().disable() //로그인 폼 미사용
                .httpBasic().disable();
//                exceptionHandling().authenticationEntryPoint(new RestAuth) //인증핸들러 등록
        //허용 url
        http
                .authorizeRequests()
                .antMatchers("/auth/**","/oauth2/**", "/token/**").permitAll()
                .anyRequest().authenticated().and() //나머지는 모두 인증 필요.
//                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login();
//                .successHandler()
//                .userInfoEndpoint().userService(oAuth2UserService);
//                .authorizationEndpoint().baseUri("/oauth2/authorization").and() //소셜 로그인 url
//                .authorizationRequestRepository().and //인증요청을 쿠키에 저장하고 검색.
//                .redirectionEndpoint().baseUri("/oauth2/callback/*"); //소셜 인증후 redirect url
//                .userInfoEndpoint().userService().and() //소셜의 회원정보를 받아서 가공처리
//                .successHandler() //인증성공시에 핸들러
//                .failureHandler(); //실패시 핸들러.

        //필터 추가
//        http
//                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);



        //핸들러 등록



        return http.build();
    }

}