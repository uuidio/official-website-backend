package net.zhqu.website.bg.feController;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.*;
import net.zhqu.website.bg.service.*;
import net.zhqu.website.util.MailUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by hao lai on 2018/11/23.
 */
@Controller
@Slf4j
public class PageController {

    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private BGHomePageService homePageService;

    @Autowired
    private BGFooterService footerService;

    @Autowired
    private BGHomeLogoService logoService;

    @Autowired
    private ParamUtil paramUtil;

    @Autowired
    private BGArticleService articleService;

    @Autowired
    private BGIntroducePageService introducePageService;

    @Autowired
    private BGCarouselPicCompService carouselPicCompService;

    @Autowired
    private BGPageInfoService pageInfoService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private BusCooperationService busCooperationService;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MailUtil mailUtil;

//    @GetMapping(value = {"/","/index.html"})
//    public String index(Model model){
//        Param param = Param.builder().build().add("id", 1l);
//        try {
//            HomePageModel homePageModel = homePageService.findOne(param);
//            model.addAttribute("pageInfo",homePageModel);
//
//            Page page = Page.builder().currentPage(1).pageSize(10).build();
//
//            List<LogoModel> logoModels = logoService.findAllPage(page);
//            model.addAttribute("logos", logoModels);
//
//
//            Page articlePage = Page.builder().currentPage(1).pageSize(3).build();
//            List<ArticleModel> articles = articleService.findAllPage(articlePage);
//            model.addAttribute("articles", articles);
//
//            Param picParam = paramUtil.getBlackParam().add("code", "index_banner").add("orderBySequenceAsc",true);
//            List<CarouselPicCompModel> carouselPics = carouselPicCompService.findAll(picParam);
//            model.addAttribute("carouselPics", carouselPics);
//
//            setFooter(model);
//        } catch (CURDException e) {
//            log.error(e.getMessage(), e);
//        }
//        return "fe/index";
//    }

    @GetMapping("/about-us.html")
    public String about(Model model) {

        try {
            Param param = Param.builder().build().add("id", 1);
            IntroducePageModel introducePageModel = introducePageService.findOne(param);
            model.addAttribute("pageInfo", introducePageModel);
        } catch (CURDException e) {
            e.printStackTrace();
        }

        setFooter(model);

        return "fe/about-us";
    }

    @GetMapping("/cooperation.html")
    public String cooperation(Model model) {
        setFooter(model);
        return "fe/cooperation";
    }

    @GetMapping("/help.html")
    public String help(Model model) {

        try {
            Param param = paramUtil.getBlackParam().add("code", "help");
            PageInfoModel pageInfoModel = pageInfoService.findOne(param);
            model.addAttribute("pageInfo", pageInfoModel);


            Param qParam = paramUtil.getPageParam(1, 4);
            List<QuestionsModel> list = questionsService.findAllPage(qParam);
            model.addAttribute("questions", list);

        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        setFooter(model);
        return "fe/help";
    }

    @GetMapping("/news-detail.html")
    public String newsDetail(Model model, @RequestParam Long id) {

        try {
            Param param = Param.builder().build().add("id", 1);
            HomePageModel homePageModel = homePageService.findOne(param);
            model.addAttribute("pageInfo", homePageModel);

            ArticleModel articleModel = articleService.findById(id);
            model.addAttribute("article", articleModel);
            if (articleModel != null) {
                homePageModel.setSeoTitle(articleModel.getTitle());
            }

        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        setFooter(model);

        return "fe/news-detail";
    }


    @GetMapping("/newsHomeList.html")
    public String getNewsHomeList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "3") Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);

        try {
            List<ArticleModel> list = articleService.findAllPage(param);
            model.addAttribute("articles", list);

        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "fe/news-home-list";
    }

    @GetMapping("/questionList.html")
    public String questionList(Model model, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "3") Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);

        try {
            List<QuestionsModel> list = questionsService.findAllPage(param);
            model.addAttribute("questions", list);

        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "fe/question-list";
    }


    @PostMapping("/jhg/cooperation")
    @ResponseBody
    public BaseResult postCooperation(@RequestBody BusCooperationModel busCooperationModel) {
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
            logger.debug("收到商务合作咨询信息，开始发邮件");
            String email = "linxuehua@smartconns.com,zhoubo@smartconns.com,tanlizhen@smartconns.com";
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

    @GetMapping("/product-center.html")
    public String productCenter(Model model) {
        List<ProductCategoryModel> list = categoryService.findAll();
        model.addAttribute("categories", list);
        setFooter(model);
        return "fe/product_center";
    }

    @GetMapping("/product-detail.html")
    public String productDetail(Model model, @RequestParam(required = true) Long id, @RequestParam(required = false) Long categoryId) {
        model.addAttribute("categoryId", categoryId);
        try {
            ProductModel productModel = productService.findById(id);
            model.addAttribute("product", productModel);
            List<ProductCategoryModel> list = categoryService.findAll();
            model.addAttribute("categories", list);
            setFooter(model);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "fe/product_detail";
    }

    @GetMapping("/product.json")
    @ResponseBody
    public BaseResult getProductByCategory(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit, @RequestParam(required = false) Long categoryId) {
        Page pageObj = Page.builder().currentPage(page).pageSize(limit).build();
        try {
            Param param = Param.builder().build().add("page", pageObj);
            if (categoryId != null) {
                param.put("categoryId", categoryId);
            }
            List<ProductModel> list = productService.findAll(param);
            return BaseResultUtil.success(pageObj, list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    public static String filteSQLStr(String Str) {

        Str = Str.replace("'", "");
        Str = Str.replace("\"", "");
        Str = Str.replace("&", "&amp");
        Str = Str.replace("<", "&lt");
        Str = Str.replace(">", "&gt");

        Str = Str.replace("delete", "");
        Str = Str.replace("update", "");
        Str = Str.replace("insert", "");
        return Str.trim();
    }


    private void setFooter(Model model) {
        Param param1 = Param.builder().build();
        List<FooterModel> footerModelList = footerService.findFooter(param1);
        model.addAttribute("footer", footerModelList);
    }

}
