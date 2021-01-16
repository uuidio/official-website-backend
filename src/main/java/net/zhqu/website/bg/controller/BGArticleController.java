package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.*;
import net.zhqu.website.bg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hao lai on 2018/11/22.
 */
@Controller
@Slf4j
@RequestMapping("/bg/article")
public class BGArticleController {

    @Autowired
    private BGArticleService articleService;

    @Autowired
    private ParamUtil paramUtil;



    @Autowired
    private SessionService sessionService;

    @GetMapping("/v/index.html")
    public String index(Model model) {
        return "bg/article_index";
    }


    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit,
                           @RequestParam(required = false) Long oneKeyCategoryId) {
        Param param = paramUtil.getPageParam(page, limit);
        try {
            List<ArticleModel> list = articleService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/{id}")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            ArticleModel articleModel = articleService.findOne(param);

            model.addAttribute("article", articleModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/article_add";
    }


    @GetMapping("/v/add.html")
    public String add(Model model) {
        return "bg/article_add";
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody ArticleModel articleModel) {
//        List<OneKeyLabelModel> oneKeyLabelModels = articleModel.getOneKeyLabel();
        try {
            articleModel.setArticleUser(sessionService.getCurrentUser().getNickname());
            if (articleModel.getId() != null) {
                articleService.update(articleModel);
//                oneKeyArticleLabelService.insertList(oneKeyLabelModels, articleModel.getId());
            } else {
                articleModel.setCreator(sessionService.getCurrentUser());
                articleModel.setOneKeyCollege(1l);
                articleModel.setOrg(sessionService.getCurrentOrg());
                articleService.insert(articleModel);
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
            articleService.delete(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }



}
