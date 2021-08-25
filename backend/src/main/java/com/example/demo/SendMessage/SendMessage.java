package com.example.demo.SendMessage;

import com.example.demo.vo.Enum.StatusEnum;
import lombok.*;

@Getter
@Setter
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
