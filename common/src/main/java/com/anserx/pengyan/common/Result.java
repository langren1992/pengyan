package com.anserx.pengyan.common;



public class Result<T> {


    private Integer code;
    private String msg;
    private T data;


    /**
     * 成功
     *
     * @return 结果视图
     */
    public static Result success() {
        return new Result();
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return 结果视图
     */
    public static Result success(Object data) {
        return new Result(data);
    }

    /**
     * 错误
     *
     * @return 结果视图
     */
    public static Result error() {
        return new Result(ResultEnum.CODE_2.getCode(), ResultEnum.CODE_2.getMsg());
    }

    /**
     * 错误
     *
     * @param resultEnum 结果枚举
     * @return 结果视图
     */
    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMsg());
    }

    /**
     * 调用服务的错误
     *
     * @param serviceName 服务名
     * @return 结果视图
     */
    public static Result hystrixError(String serviceName) {
        ResultEnum resultEnum = ResultEnum.CODE_3;
        String msg = resultEnum.getMsg().replace("xxx", serviceName);
        return new Result(resultEnum.getCode(), msg);
    }

    /**
     * 自定义错误消息
     *
     * @param msg 错误消息
     * @return 结果视图
     */
    public static Result error(String msg) {
        return new Result(ResultEnum.CODE_2.getCode(), msg);
    }

    private Result() {
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = ResultEnum.CODE_1.getMsg();
    }

    private Result(T data) {
        this.data = data;
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = ResultEnum.CODE_1.getMsg();
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
