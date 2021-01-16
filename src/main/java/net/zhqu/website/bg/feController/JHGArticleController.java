package net.zhqu.website.bg.feController;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.ArticleModel;
import net.zhqu.website.bg.model.BusCooperationModel;
import net.zhqu.website.bg.service.BGArticleService;
import net.zhqu.website.bg.service.BusCooperationService;
import net.zhqu.website.util.MailUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static net.zhqu.website.bg.feController.PageController.filteSQLStr;

@Controller
@Slf4j
@RequestMapping("/jhg")
@ResponseBody
public class JHGArticleController {


    @Autowired
    private BGArticleService articleService;

    @Autowired
    private BusCooperationService busCooperationService;

    @Autowired
    private ParamUtil paramUtil;

    @Autowired
    private MailUtil mailUtil;

    @GetMapping("/summaryList")
    public BaseResult getArticleSummary() {
        Param param = paramUtil.getPageParam(1, 100);
        try {
            List<ArticleModel> list = articleService.findSummaryPage(param);
            Calendar cal = Calendar.getInstance();
            list.forEach(item -> {
                cal.setTime(item.getLastModifiedTime());
                item.setLastMonth(cal.get(Calendar.YEAR) + "M" + cal.get(Calendar.MONTH));
            });
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (Exception e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/getDetail")
    public BaseResult getArticleDetail(@RequestParam(required = false) Long articleId) {
        Param param = Param.builder().build().add("id", articleId);
        try {
            ArticleModel articleModel = articleService.findOne(param);
            return BaseResultUtil.success("ok", articleModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/busCooperation")
    @ResponseBody
    public BaseResult busCooperation(@RequestBody BusCooperationModel busCooperationModel) {
        try {

            if (StringUtils.isBlank(busCooperationModel.getName())) {
                return BaseResultUtil.fail("请填写姓名");
            }
            if (StringUtils.isBlank(busCooperationModel.getMobile())) {
                return BaseResultUtil.fail("请填写手机");
            }

            if (StringUtils.isNotBlank(busCooperationModel.getName())) {
                busCooperationModel.setName(filteSQLStr(busCooperationModel.getName()));
            }
            if (StringUtils.isNotBlank(busCooperationModel.getCompany())) {
                busCooperationModel.setCompany(filteSQLStr(busCooperationModel.getCompany()));
            }
            if (StringUtils.isNotBlank(busCooperationModel.getContent())) {
                busCooperationModel.setContent(filteSQLStr(busCooperationModel.getContent()));
            }
            if (StringUtils.isNotBlank(busCooperationModel.getEmail())) {
                busCooperationModel.setEmail(filteSQLStr(busCooperationModel.getEmail()));
            }
            if (StringUtils.isNotBlank(busCooperationModel.getMobile())) {
                busCooperationModel.setMobile(filteSQLStr(busCooperationModel.getMobile()));
            }

            String email = "linxuehua@smartconns.com,zhoubo@smartconns.com,tanlizhen@smartconns.com,luming@smartconns.com";
            mailUtil.sendMail(email, String.format("姓名:%s <br/> 手机号:%s <br/> 公司:%s <br/> 内容:%s <br/> Email:%s <br/> 时间:%tc <br/>",
                    busCooperationModel.getName(),
                    busCooperationModel.getMobile(),
                    busCooperationModel.getCompany(),
                    busCooperationModel.getContent(),
                    busCooperationModel.getEmail(),
                    new Date()), "官网商务合作");

            busCooperationService.insert(busCooperationModel);
            return BaseResultUtil.success("成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResultUtil.fail("异常");
        }
    }

}
