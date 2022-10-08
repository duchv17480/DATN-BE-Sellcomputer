package com.example.udpm14sellcomputerpartsbackend.payload.response;

import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse <T>{

    private Integer status;
    private String message;
    private T data;

    public static <T> DefaultResponse <T> success(T body){
        DefaultResponse<T> response  = new DefaultResponse<>();
        response.setStatus(ResponseStatusContants.SUCCESS.getCode());
        response.setMessage(ResponseStatusContants.SUCCESS.getMessage());
        response.setData(body);

        return response;
    }



}
