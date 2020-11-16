package com.jlhx.payroll.application.bean;

import java.io.Serializable;

/**
 * 消息推送
 */
public class PushMessageBean implements Serializable{
    private long id;
    private long clueId;
    private String contentType;

    public long getClueId() {
        return clueId;
    }

    public void setClueId(long clueId) {
        this.clueId = clueId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
