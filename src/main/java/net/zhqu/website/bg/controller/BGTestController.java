package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hao lai on 2018/12/8.
 */
@Slf4j
@Controller
@RequestMapping("/bg/test")
public class BGTestController {


    @GetMapping("/v/index.html")
    public String index(){
        return "bg/test_index";
    }



}
