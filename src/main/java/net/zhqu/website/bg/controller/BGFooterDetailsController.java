package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.FooterDetailsModel;
import net.zhqu.website.bg.model.FooterModel;
import net.zhqu.website.bg.service.BGFooterDetailsService;
import net.zhqu.website.bg.service.BGFooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/14.
 */
@Controller
@Slf4j
@RequestMapping("/bg/footerDetails")
public class BGFooterDetailsController {

    @Autowired
    private BGFooterDetailsService footerDetailsService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BGFooterService footerService;

    @Autowired
    private ParamUtil paramUtil;

    @GetMapping("/v/index.html")
    public String index(Model model) {
        Param param1 = Param.builder().build();
        try {
            List<FooterModel> footerModelList = footerService.findAllPage(param1);
            model.addAttribute("footer", footerModelList);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "bg/footer_details_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit,
                           @RequestParam(required = false) Integer footerId) {
        Param param = paramUtil.getPageParam(page,limit);
        if (footerId != null){
            param.add("footerId",footerId);
        }
        try {
            List<FooterDetailsModel> footerDetailsModels = footerDetailsService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), footerDetailsModels);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody FooterDetailsModel footerDetailsModel){
        System.out.println(footerDetailsModel);
        try {
            if(footerDetailsModel.getId() != null){
                footerDetailsService.update(footerDetailsModel);
            }else{
                footerDetailsModel.setCreator(sessionService.getCurrentUser());
                footerDetailsModel.setOrg(sessionService.getCurrentOrg());
                footerDetailsService.insert(footerDetailsModel);
            }
            return BaseResultUtil.success("");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return BaseResultUtil.fail("失败");
        }
    }

    @GetMapping("/v/{id}")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            Param param1 = Param.builder().build();
            FooterDetailsModel footerDetailsModel = footerDetailsService.findOne(param);
            List<FooterModel> footerModelList = footerService.findAllPage(param1);
            model.addAttribute("footerDetails", footerDetailsModel);
            model.addAttribute("footer", footerModelList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/footer_details_add";
    }

    @GetMapping("/v/add.html")
    public String add(Model model) {
        Param param = Param.builder().build();
        try {
            List<FooterModel> footerModelList = footerService.findAllPage(param);
            model.addAttribute("footer", footerModelList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/footer_details_add";
    }

    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id){
        Param param = Param.builder().build().add("id",id);
        try {
            footerDetailsService.delete(param);
            return BaseResultUtil.success("");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return BaseResultUtil.fail("失败");
        }
    }
}
