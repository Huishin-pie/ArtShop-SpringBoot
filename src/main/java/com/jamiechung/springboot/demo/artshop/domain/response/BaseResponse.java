package com.jamiechung.springboot.demo.artshop.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BaseResponse<T> {
    @JsonProperty("info")
    private ResponseInfo returnInfo;
    @JsonProperty("data")
    private T returnData;

    public BaseResponse() {
    }

    public BaseResponse(ResponseInfo returnInfo, T returnData) {
        this.returnInfo = returnInfo;
        this.returnData = returnData;
    }
}
