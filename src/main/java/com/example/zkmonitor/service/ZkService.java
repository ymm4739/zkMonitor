package com.example.zkmonitor.service;

import com.example.zkmonitor.domain.ZNode;

import java.util.List;

public interface ZkService {
    ZNode get(String path);

    List<ZNode> getChildrenInfo(String s);

    ZNode dataChange(String path, String sid);
}
