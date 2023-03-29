package com.webtoon.manamana.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "login_providers")
public class LoginProvider {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
