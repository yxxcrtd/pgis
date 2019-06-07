package com.igoosd.domain.vo;

import com.igoosd.util.Constants;
import lombok.Data;

@Data
public class ResultMsg<T> {

    private int code;

    private String message;

    private T data;

    private ResultMsg(){
    }

    public  static final <T> ResultMsg<T> resultSuccess(String message, T data){
        ResultMsg<T> resultVo = new ResultMsg<>();
        resultVo.setCode(Constants.INT_200);
        resultVo.setMessage(message);
        resultVo.setData(data);
        return resultVo;
    }

    public  static final <T> ResultMsg<T> resultSuccess(String message){
        return  resultSuccess(message,null);
    }

    public static final <T> ResultMsg<T> resultSuccess(T t){
        return resultSuccess(null,t);
    }

    public static final <T> ResultMsg<T> resultSuccess(){
        return resultSuccess(null,null);
    }

    public static final <T> ResultMsg<T> resultFail(int code, String message){
        ResultMsg<T> resultVo = new ResultMsg<T>();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }

    public static final <T> ResultMsg<T> resultFail(String message){
        return resultFail(Constants.INT_MINUS_200,message);
    }

    public boolean checkSuccess(){
        return this.getCode() == Constants.INT_200;
    }
}
