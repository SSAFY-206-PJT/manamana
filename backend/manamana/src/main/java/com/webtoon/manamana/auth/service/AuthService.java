package com.webtoon.manamana.auth.service;

public interface AuthService {

    String reAccessToken(String oldAccessToken, String refreshToken);
}
