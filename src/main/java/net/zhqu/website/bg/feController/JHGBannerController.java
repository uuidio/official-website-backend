package net.zhqu.website.bg.feController;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.BusCooperationModel;
import net.zhqu.website.bg.model.CarouselPicCompModel;
import net.zhqu.website.bg.service.BGCarouselPicCompService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/jhg/banner")
@ResponseBody
public class JHGBannerController {


    @Autowired
    private BGCarouselPicCompService carouselPicCompService;


    @GetMapping("/get")
    @ResponseBody
    BaseResult findAllPage() {
        Page pageObj = Page.builder().currentPage(1).pageSize(100).build();
        try {
            Param param = Param.builder().build().add("page", pageObj);
            List<CarouselPicCompModel> list = carouselPicCompService.findAllPage(param);
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }


}
