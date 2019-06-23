package com.hansalser.cloudservice.Common;

/**
 * @author chenglin.luo
 * @date 2019/6/23 13:34
 */
public class ResponseMessage<T> {

    private Integer code;
    private String msg;
    private T data;

    public static ResponseMessage success(Object object) {
        ResponseMessage message = new ResponseMessage();
        message.setCode(Code.OPERATION_SUCCESS);
        message.setMsg("OPERATION SUCCESS");
        message.setData(object);
        return message;
    }

    public static ResponseMessage success() {
        return success(null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public void setData(T data) {
        this.data = data;
    }
}
