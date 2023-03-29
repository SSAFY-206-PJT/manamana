package com.webtoon.manamana.auth;

import com.webtoon.manamana.auth.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//
////    private final TokenProvider tokenProvider;
//
////    private final
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//    }
//}
