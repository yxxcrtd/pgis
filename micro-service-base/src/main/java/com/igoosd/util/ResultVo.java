package com.igoosd.util;

import lombok.Data;

@Data
public class ResultVo {

    private boolean success;
    private String message;
    private Object data;

    public static ResultVo getSuccessResult() {
        ResultVo vo = new ResultVo();
        vo.setSuccess(true);
        return vo;
    }

    public static <T> ResultVo getSuccessResult(T data) {
        ResultVo vo = new ResultVo();
        vo.setSuccess(true);
        vo.setData(data);
        return vo;
    }

    public static ResultVo getFailResult(String message) {
        ResultVo vo = new ResultVo();
        vo.setSuccess(false);
        vo.setMessage(message);
        return vo;
    }

}
