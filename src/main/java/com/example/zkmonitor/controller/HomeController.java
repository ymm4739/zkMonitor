package com.example.zkmonitor.controller;

import com.example.zkmonitor.domain.ZNode;
import com.example.zkmonitor.service.ZkService;
import com.example.zkmonitor.utils.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ZkService zkService;
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("")
    public String index() {
        return "home";
    }

    @GetMapping("/stat")
    public String stat(ZNode zNode, Model model) {
        zNode = zkService.get(zNode.getPath());
        model.addAttribute("zNode", zNode);
        return "stat";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<ZNode> list(ZNode parent) {
        List<ZNode> zNodes = zkService.getChildrenInfo(parent.getPath());
        return zNodes;
    }

    @GetMapping("/monitor")
    public String monitor(String path, Model model) {
        model.addAttribute("path", path);
        return "monitor";
    }

    @GetMapping("/dataChange")
    public String dataChange(String path, String sid, Model model) {
        ZNode zNode = zkService.dataChange(path, sid);
        model.addAttribute("zNode", zNode);
        return "stat";
    }
}
