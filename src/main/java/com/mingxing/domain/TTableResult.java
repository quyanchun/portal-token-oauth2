package com.mingxing.domain;

import com.mingxing.exception.PortalException;
import com.mingxing.utils.JacksonUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 表格分页数据对象
 */
public class TTableResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    private long total;

    private List<T> rows;

    private int code;

    /**
     * 消息内容
     */
    private String msg;

    public TTableResult() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TTableResult(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public static <T> TTableResult<T> build(List<T> list) {
        TTableResult<T> rspData = new TTableResult<>();
        rspData.setCode(CODE_SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> TTableResult<T> build() {
        TTableResult<T> rspData = new TTableResult<>();
        rspData.setCode(CODE_SUCCESS);
        rspData.setMsg("查询成功");
        return rspData;
    }
    public List<T> checkAndGetData(Class<T> clazz) {
        checkCode();
        return getRows(clazz);
    }
    public boolean checkCode() {
        if (Objects.equals(200, this.code)){
            throw new PortalException(this.code,this.msg);
        }
        return true;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public List<T> getRows(Class<T> clazz) {
        this.rows = JacksonUtil.convertArray(rows, clazz);
        return rows;
    }


    public void setRows(List<T> rows) {
        this.rows = rows;
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


}
