package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.SerialStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponseDTO {

    private int id;
    private String status;

    @Builder
    public StatusResponseDTO(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public static StatusResponseDTO createDTO(SerialStatus serialStatus){
        return StatusResponseDTO.builder()
                .id(serialStatus.getId())
                .status(serialStatus.getStatus()).build();
    }
}
