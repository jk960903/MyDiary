package com.example.demo.vo;

import com.example.demo.vo.Enum.StatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
public class SendMessage<T> {
    private StatusEnum status;
    private String message;
    private T data;

    public SendMessage(T data ,StatusEnum status , String message){
        this.data =data;
        this.status =status;
        this.message =message;
    }



}
