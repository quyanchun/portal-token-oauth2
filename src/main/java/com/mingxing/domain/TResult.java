package com.mingxing.domain;

import com.mingxing.exception.PortalException;
import com.mingxing.utils.JacksonUtil;

import java.io.Serializable;
import java.util.Objects;

public class TResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;
    private int code;
    private String msg;
    private T data;

    public TResult() {
    }

    public TResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public T getData(Class<T> clazz) {
        return JacksonUtil.convertObj(data,clazz);
    }

    public T checkAndGetData(Class<T> clazz) {
        checkCode();
        return getData(clazz);
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean checkCode() {
        if (!Objects.equals(200, this.code))
            throw new PortalException(this.code,this.msg);
        return true;
    }

    public static TResult ok() {
        return new TResult(200, "ok", (Object) null);
    }

    public static TResult ok(String msg) {
        return new TResult(200, msg, (Object) null);
    }

    public static TResult ok(Object data) {
        return new TResult(200, "ok", data);
    }

    public static TResult code(int code) {
        return new TResult(code, (String) null, (Object) null);
    }

    public static TResult data(Object data) {
        return new TResult(200, "ok", data);
    }

    public static TResult error() {
        return new TResult(500, "error", (Object) null);
    }

    public static TResult error(String msg) {
        return new TResult(500, msg, (Object) null);
    }
    public static TResult error(int code,String msg) {
        return new TResult(code, msg, (Object) null);
    }
    public static TResult get(int code, String msg, Object data) {
        return new TResult(code, msg, data);
    }


}
