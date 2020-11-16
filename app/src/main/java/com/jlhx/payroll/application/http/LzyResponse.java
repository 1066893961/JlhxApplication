package com.jlhx.payroll.application.http;

import java.io.Serializable;

/**
     * explain  返回有数据类型
     * created by 张国亮.
     * created date 2017/4/28 13:59.
     */


public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;


    public int code;    //0表示成功，1失败
    public String msg;       //日志消息
//    public int pageNum;      //当前页数
//    public int pageSize;    //页面大小
//    public int pages;   //总页数
    public Page page;       //分页的参数
    public T data;          //数据对象结构
}