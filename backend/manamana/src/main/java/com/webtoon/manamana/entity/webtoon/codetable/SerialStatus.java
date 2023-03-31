package com.webtoon.manamana.entity.webtoon.codetable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "serial_status")
public class SerialStatus {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;

}
