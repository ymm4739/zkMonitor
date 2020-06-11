package com.example.zkmonitor.domain;

import org.apache.zookeeper.data.Stat;

import java.io.Serializable;
import java.util.List;

public class ZNode implements Serializable {
    private static final Long serialVersionId = 1L;
    private String path;
    private String name;
    private String data;
    private List<ZNode> children;
    private Stat stat;
    private Boolean isParent;

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<ZNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ZNode{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", children=" + children +
                ", isParent=" + isParent +
                ", stat=" + stat +
                '}';
    }
}
