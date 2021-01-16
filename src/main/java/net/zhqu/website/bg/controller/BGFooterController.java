package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.FooterModel;
import net.zhqu.website.bg.service.BGFooterService;
import net.zhqu.website.bg.service.BGFooterDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/13.
 */
@Controller
@RequestMapping("/bg/footer")
@Slf4j
public class BGFooterController {

    @Autowired
    private BGFooterService footerService;

    @Autowired
    private BGFooterDetailsService footerDetailsService;
    @Autowired
    private ParamUtil paramUtil;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/v/index.html")
    public String index() {
        return "bg/footer_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
//        Page pageObj = Page.builder().currentPage(page).pageSize(limit).build();
//        Param param = paramUtil.getBlackParam().add("page", pageObj);
        Param param = paramUtil.getPageParam(page, limit);
        try {
            List<FooterModel> list = footerService.findAllPage(param);
            int count = footerService.count();
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
            FooterModel footerModel = footerService.findOne(param);
            model.addAttribute("footer", footerModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/footer_add";
    }

    @GetMapping("/v/add.html")
    public String update() {
        return "bg/footer_add";
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody FooterModel footerModel) {
        try {
            if (footerModel.getId() != null) {
                footerService.update(footerModel);
//                identifyService.insertIdentify(footerModel.getId(), footerModel.getIdentifys());
            } else {
                footerModel.setCreator(sessionService.getCurrentUser());
                footerModel.setOrg(sessionService.getCurrentOrg());
                Long id = footerService.insertFooter(footerModel);
//                identifyService.insertIdentify(id, footerModel.getIdentifys());
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
        Param param = Param.builder().build().add("id", id);
        try {
            footerService.delete(param);
            footerDetailsService.deleteForFooter(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }


    @GetMapping("/r")
    @ResponseBody
    public BaseResult r() {
        Param param = Param.builder().build();
        try {
            List<FooterModel> footerModelList = footerService.findAllPage(param);
            return BaseResultUtil.success(footerModelList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResultUtil.fail("获取失败");
        }
    }

}
