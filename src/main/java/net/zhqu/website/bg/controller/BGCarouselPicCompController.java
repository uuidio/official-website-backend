package net.zhqu.website.bg.controller;

import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.CarouselPicCompModel;
import net.zhqu.website.bg.service.BGCarouselPicCompService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bg/carouselPicComp")
public class BGCarouselPicCompController  {
    @Autowired
    private BGCarouselPicCompService carouselPicCompService;


    @GetMapping("/v/index.html")
    public String index() {
        return "bg/carousel_pic_comp_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit, @RequestParam(required = false) String code) {
        Page pageObj = Page.builder().currentPage(page).pageSize(limit).build();
        try {
            Param param = Param.builder().build().add("page", pageObj);
            if (StringUtils.isNotBlank(code)) {
                param.add("code", code);
            }
            List<CarouselPicCompModel> list = carouselPicCompService.findAllPage(param);
            list.forEach(item->{
                item.setCdnPic("http://rescdn.www.smartconns.com/" + item.getPic());
            });
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }


    @GetMapping("/v/add.html")
    public String add() {
        return "bg/carousel_pic_comp_add";
    }

    @PostMapping("/c/")
    public String insert(CarouselPicCompModel carouselPicCompModel) {
        try {
            if (carouselPicCompModel.getId() != null) {
                carouselPicCompService.update(carouselPicCompModel);
            } else {
                carouselPicCompService.insert(carouselPicCompModel);
            }
        } catch (CURDException e) {
        }
        return "redirect:/bg/carouselPicComp/v/index.html";
    }

    @GetMapping("/v/{id}")
    public String indexUpdateHtml(@PathVariable Long id, Model model) {
        try {
            CarouselPicCompModel carouselPicCompModel = carouselPicCompService.findById(id);
            model.addAttribute("carouselPicCompModel", carouselPicCompModel);
        } catch (CURDException e) {

        }
        return "bg/carousel_pic_comp_add";
    }

    @PostMapping("/u/")
    public String update(CarouselPicCompModel carouselPicCompModel) {
        try {
            carouselPicCompService.update(carouselPicCompModel);
        } catch (CURDException e) {

        }
        return "redirect:/bg/carouselPicComp/v/index.html";
    }

    @PostMapping("/d")
    public @ResponseBody
    BaseResult delete(@RequestBody CarouselPicCompModel model) {
        try {
            if (model.getId() == null) {
                throw new CURDException("参数错误");
            }
            carouselPicCompService.delete(Param.builder().build().add(Param.ID, model.getId()));
            return BaseResultUtil.success("ok");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

}
