package com.phj.crowd.utils;


/**
 * 请求处理结果的实体类
 * @author 31637
 * @date 2020/7/30 12:00
 */
public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FILED = "FILED";

    private String result;
    private String msg;
    private T data;

    /**
     * 请求处理成功的方法，不附带提示信息
     * @param data 数据
     * @param <E> 泛型
     * @return 处理好的结果
     */
    public static <E> ResultEntity<E> success(E data){
        return new ResultEntity<>(SUCCESS, null, data);
    }

    /**
     * 请求处理成功的方法，附带提示信息
     * @param data 数据
     * @param msg 提示信息
     * @param <T> 泛型
     * @return 处理好的结果
     */
    public static <E> ResultEntity<E> success(E data,String msg){
        return new ResultEntity<>(SUCCESS,msg,data);
    }

    /**
     * 请求处理失败的方法
     * @param msg 消息
     * @return 处理好的结果
     */
    public static <E> ResultEntity<E> fail(String msg){
        return new ResultEntity<>(FILED,msg,null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String msg, T data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
