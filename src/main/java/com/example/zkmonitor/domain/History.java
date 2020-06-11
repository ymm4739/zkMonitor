package com.example.zkmonitor.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

public class History extends Model {
    private static final Long serialVersionId = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String changeUser;
    private String changeTime;
    private String createTime;
    private String changeSummary;
    private String changeIp;

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeSummary() {
        return changeSummary;
    }

    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }

    public String getChangeIp() {
        return changeIp;
    }

    public void setChangeIp(String changeIp) {
        this.changeIp = changeIp;
    }
}
