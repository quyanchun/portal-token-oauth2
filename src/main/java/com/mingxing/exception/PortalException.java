package com.mingxing.exception;


/**
 * 异常
 *
 * @author deliver
 */
public class PortalException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    /**
     * 错误提示
     */
    private String message;
    private Integer code;

    /**
     * 错误明细，内部调试错误
     *
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public PortalException()
    {
    }

    public PortalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public PortalException(String message)
    {
        this.code = 500;
        this.message = message;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
