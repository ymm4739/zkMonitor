package com.example.zkmonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zkmonitor.domain.History;
import com.example.zkmonitor.mapper.HistoryMapper;
import com.example.zkmonitor.service.HistoryService;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {
}
