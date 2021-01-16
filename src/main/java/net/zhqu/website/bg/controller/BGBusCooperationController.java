package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.BusCooperationModel;
import net.zhqu.website.bg.model.QuestionsModel;
import net.zhqu.website.bg.service.BusCooperationService;
import net.zhqu.website.bg.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/bg/buscooperation")
public class BGBusCooperationController {
    @Autowired
    private BusCooperationService busCooperationService;

    @Autowired
    private ParamUtil paramUtil;


    @GetMapping("/v/index.html")
    public String index(Model model) {
        try {
            Param param1 = Param.builder().build();
            List<BusCooperationModel> busCooperationModels = busCooperationService.findAllPage(param1);
            model.addAttribute("busCooperations", busCooperationModels);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "bg/bus_cooperation_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);
        try {
            List<BusCooperationModel> list = busCooperationService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/add.html")
    public String add(Model model) {
        return "bg/bus_cooperation_add";
    }

    @GetMapping("/v/{id}.html")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            BusCooperationModel busCooperationModel = busCooperationService.findOne(param);
            model.addAttribute("busCooperation", busCooperationModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/bus_cooperation_add";
    }

    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id) {
        Param param = Param.builder().build().add("id", id);
        try {
            busCooperationService.delete(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }


    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody BusCooperationModel busCooperationModel) {
        try {
            if (busCooperationModel.getId() != null) {
                busCooperationService.update(busCooperationModel);
            } else {
                busCooperationService.insert(busCooperationModel);

            }
            return BaseResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResultUtil.fail("异常");
        }
    }
}
