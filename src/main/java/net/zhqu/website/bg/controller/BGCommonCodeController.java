package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.CommonCodeModel;
import net.zhqu.website.bg.service.CommonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By xin cai On 2018/5/17
 *  系统参数
 * @author xin cai (1428774847@qq.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/commonCode")
public class BGCommonCodeController {
    @Autowired
    private CommonCodeService commonCodeService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/v/index.html")
    public String index() {
        return "bg/common_code_index";
    }

    @GetMapping("/r/all.json")
    @ResponseBody
    public BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false)Integer limit) {
        Page pageObj = Page.builder().currentPage(page).pageSize(limit).build();
        try {
            List<CommonCodeModel> list = commonCodeService.findAllPage(pageObj);
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/common_code_add";
    }

    @PostMapping("/c/")
    public String insert(CommonCodeModel commonCodeModel){
        try {
            if (commonCodeModel.getId() != null) {
                commonCodeService.updateWithCache(commonCodeModel);
            }else {
                commonCodeModel.setOrg(sessionService.getCurrentOrg());
                commonCodeModel.setCreator(sessionService.getCurrentUser());
                commonCodeService.insert(commonCodeModel);
            }
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "redirect:/bg/commonCode/v/index.html";
    }

    @GetMapping("/v/{id}")
    public String indexUpdateHtml(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id",id);
        try {
            CommonCodeModel commonCodeModel = commonCodeService.findOne(param);
            model.addAttribute("commonCode", commonCodeModel);
        } catch (CURDException e) {

        }
        return "bg/common_code_add";
    }

    @GetMapping("/u/{id}")
    public String update(CommonCodeModel commonCodeModel) {
        try {
            commonCodeService.updateWithCache(commonCodeModel);
        } catch (CURDException e) {

        }
        return "redirect:bg/commonCode/v/index.html";
    }

    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id) {
        try {
            Param param = Param.builder().build().add("id",id);
            commonCodeService.delete(param);
            return BaseResultUtil.success("OK");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
