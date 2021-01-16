package net.zhqu.website.bg.feController;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.LogoModel;
import net.zhqu.website.bg.service.BGHomeLogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/jhg/logo")
@ResponseBody
public class JHGHomeLogoController {


    @Autowired
    private BGHomeLogoService homeLogoService;


    @GetMapping("/get")
    @ResponseBody
    BaseResult findAllPage() {
        Page pageObj = Page.builder().currentPage(1).pageSize(50).build();
        try {
            Param param = Param.builder().build().add("page", pageObj);
            List<LogoModel> list = homeLogoService.findAllPage(param);
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
