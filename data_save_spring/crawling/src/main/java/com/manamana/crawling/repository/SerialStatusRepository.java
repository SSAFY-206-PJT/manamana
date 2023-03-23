package com.manamana.crawling.repository;

import com.manamana.crawling.entity.webtoon.codetable.SerialStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerialStatusRepository extends JpaRepository<SerialStatus, Integer> {

    List<SerialStatus> findAll();
}
