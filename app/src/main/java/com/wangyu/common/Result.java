package com.wangyu.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private boolean success;

    private String code;

    private String message;

    private Object data;

//    private String requestID;

    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("0");
        return result;
    }

    public static Result success(String message){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("0");
        result.setMessage(message);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("0");
        result.setData(data);
        return result;
    }

    public static Result success(String message, Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode("0");
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result success(String code, String message, Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result failed(){
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }

    public static Result failed(String message){
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static Result failed(Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setData(data);
        return result;
    }

    public static Result failed(String message, Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result failed(String code, String message, Object data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
