package com.webtoon.manamana.user.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserAlarmController {

    /*유저가 받은 알람*/
    @GetMapping("/{user-id}/webtoon/alarm")
    public

    /*유저가 받은 알람 제거*/
    @GetMapping("/{user-id}/webtoon/{webtoon-id}/alarm")
    public

}
