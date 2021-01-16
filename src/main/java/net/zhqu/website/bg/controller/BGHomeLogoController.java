package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.LogoModel;
import net.zhqu.website.bg.service.BGHomeLogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/23.
 *
 */
@Controller
@Slf4j
@RequestMapping("/bg/homeLogo")
public class BGHomeLogoController {

    @Autowired
    private BGHomeLogoService logoService;

    @Autowired
    private ParamUtil paramUtil;

    @Autowired
    private SessionService sessionService;


    @GetMapping("/v/index.html")
    public String index() {
        return "bg/home_logo_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);
        try {
            List<LogoModel> list = logoService.findAllPage(param);
            int count = logoService.count();
            BaseResult baseResult = BaseResultUtil.success(list);
            baseResult.setCount(count);
            return baseResult;
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/{id}")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            LogoModel logoModel = logoService.findOne(param);
            model.addAttribute("logo", logoModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/home_logo_add";
    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/home_logo_add";
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(LogoModel logoModel) {
        try {
            if(logoModel.getId()!=null){
                logoService.update(logoModel);
            }else {
                logoModel.setOrg(sessionService.getCurrentOrg());
                logoModel.setCreator(sessionService.getCurrentUser());
                logoModel.setLogo(1L);
                logoService.insert(logoModel);
            }
            return BaseResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResultUtil.fail("异常");
        }
    }


    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id) {
        try {
            Param param = Param.builder().build().add("id", id);
            logoService.delete(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }


}
