package com.manamana.crawling.config.response;

import com.manamana.crawling.config.response.exception.CustomExceptionStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    /**
     * 요청 성공 응답 - 응답 데이터가 없는 경우.
     * @return
     */
    public CommonResponse getSuccessResponse(){
        CommonResponse response = new CommonResponse();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage("요청에 성공했습니다.");

        return response;
    }

    public <T> DataResponse<T> getDataResponse(T data, CustomSuccessStatus status){
        DataResponse<T> response = new DataResponse<>();
        response.setSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());
        response.setResult(data);

        return response;
    }

    public CommonResponse getExceptionResponse(CustomExceptionStatus status){

        CommonResponse response = new CommonResponse();
        response.setSuccess(status.isSuccess());
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());

        return response;

    }


}
