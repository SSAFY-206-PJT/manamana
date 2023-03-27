package com.webtoon.manamana.recommand.dto.response;

import com.webtoon.manamana.recommand.dto.request.ApiAuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponseDTO {

    private long id;
    private String name;
    private String imagePath;
    private List<ApiAuthorDTO> authors;

}
