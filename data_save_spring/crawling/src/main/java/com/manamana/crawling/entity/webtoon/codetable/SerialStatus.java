package com.manamana.crawling.entity.webtoon.codetable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "setial_status")
public class SerialStatus {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

}
