package com.ppdream.xweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: x43125
 * @Date: 22/01/29
 */
@Controller
@RequestMapping("/game")
public class GameCenterController {

    @GetMapping("/center")
    public String gameCenter() {
        return "index";
    }
}
