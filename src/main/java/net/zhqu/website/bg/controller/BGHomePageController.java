package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.*;
import net.zhqu.website.bg.service.*;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/8.
 */
@Controller
@Slf4j
@RequestMapping("/bg/homePage")
public class BGHomePageController {

    @Autowired
    private BGHomePageService homePageService;


    @GetMapping("/v/index.html")
    public String index(Model model) {
        Param param = Param.builder().build().add("id", 1l);
        try {
            HomePageModel homePageModel = homePageService.findOne(param);
            model.addAttribute("homepage", homePageModel);
            return "bg/homePage_index";
        } catch (CURDException e) {
            log.error("异常", e);
            return "bg/homePage_index";
        }
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody HomePageModel homePageModel) {
        try {
            if (homePageModel.getId() == null){
                homePageModel.setId(1L);
            }
            homePageService.update(homePageModel);
            return BaseResultUtil.success(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResultUtil.fail("异常");
        }
    }

}
