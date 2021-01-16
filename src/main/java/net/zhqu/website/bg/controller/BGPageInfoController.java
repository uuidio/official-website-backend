package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.PageInfoModel;
import net.zhqu.website.bg.service.BGPageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/bg/pageInfo")
public class BGPageInfoController {
    @Autowired
    private BGPageInfoService pageInfoService;

    @Autowired
    private ParamUtil paramUtil;


    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(PageInfoModel pageInfoModel) {
        try {
            if (pageInfoModel.getId() == null) {
                pageInfoService.insert(pageInfoModel);
            }else
            {
                pageInfoService.update(pageInfoModel);
            }
            return BaseResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/v/help.html")
    public String helpHtml(Model model) {
        try {
            Param param = paramUtil.getBlackParam().add("code", "help");
            PageInfoModel pageInfoModel = pageInfoService.findOne(param);
            model.addAttribute("pageInfo", pageInfoModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/help_index";
    }
    @GetMapping("/v/cooperation.html")
    public String cooperationHtml(Model model) {
        try {
            Param param = paramUtil.getBlackParam().add("code", "cooperation");
            PageInfoModel pageInfoModel = pageInfoService.findOne(param);
            model.addAttribute("pageInfo", pageInfoModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/cooperation_index";
    }
}
