package com.zsm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 返回结果集
 */
@Data
@ApiModel("返回结果集")
public class ResultSet<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("状态码")
    private String statusCode;

    @ApiModelProperty("消息提示")
    private String msg;

    @ApiModelProperty("结果数据")
    private T data;

    private ResultSet()
    {
    }

    /**
     * 请求成功返回
     */
    public static <T> ResultSet<T> success(T t)
    {
        return ResultSet.success("200", t);
    }

    /**
     * 请求成功返回
     */
    public static <T> ResultSet<T> success(String code, T t)
    {
        return gettResultSet(true, code, "success", t);
    }

    /**
     * 请求失败返回
     */
    public static <T> ResultSet<T> fail(String msg)
    {
        return ResultSet.fail("500", msg);
    }

    /**
     * 请求失败返回
     */
    public static <T> ResultSet<T> fail(String code, String msg)
    {
        return gettResultSet(false, code, msg, null);
    }

    private static <T> ResultSet<T> gettResultSet(boolean state, String code, String msg, T t)
    {
        ResultSet<T> rs = new ResultSet<>();
        rs.setSuccess(state);
        rs.setStatusCode(code);
        rs.setMsg(msg);
        rs.setData(t);
        return rs;
    }
}
