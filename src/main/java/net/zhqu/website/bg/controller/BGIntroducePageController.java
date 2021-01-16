package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.CarouselPicCompModel;
import net.zhqu.website.bg.model.IntroducePageModel;
import net.zhqu.website.bg.model.IntroducePageVo;
import net.zhqu.website.bg.service.BGCarouselPicCompService;
import net.zhqu.website.bg.service.BGIntroducePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/16.
 */
@Controller
@Slf4j
@RequestMapping("/bg/introducePage")
public class BGIntroducePageController {


    @Autowired
    private BGIntroducePageService introducePageService;


    @GetMapping("/v/index.html")
    public String index(Model model) {
        Param param = Param.builder().build().add("id", 1L);
        try {
            IntroducePageModel introducePageModel = introducePageService.findOne(param);
            model.addAttribute("introduce", introducePageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "bg/introduce_page_index";
    }


    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(IntroducePageModel introducePageModel) {
        try {
            if (introducePageModel.getId() == null) {
                introducePageService.insert(introducePageModel);
            }else
            {
                introducePageService.update(introducePageModel);
            }

            return BaseResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping("/r")
    @ResponseBody
    public BaseResult r() {
        try {
            Param param = Param.builder().build().add("id", 1l);
            IntroducePageVo introducePageVo = introducePageService.findAllR(param);
            return BaseResultUtil.success(introducePageVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResultUtil.fail("异常");
        }
    }

}
