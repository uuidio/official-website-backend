package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.QuestionsModel;
import net.zhqu.website.bg.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/bg/questions")
public class BGQuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private ParamUtil paramUtil;


    @GetMapping("/v/index.html")
    public String index(Model model) {
        try {
            Param param1 = Param.builder().build();
            List<QuestionsModel> oneKeyCategoryModels = questionsService.findAllPage(param1);
            model.addAttribute("questions", oneKeyCategoryModels);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }
        return "bg/questions_index";
    }

    @GetMapping("/r/all.json")
    public @ResponseBody
    BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);
        try {
            List<QuestionsModel> list = questionsService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/add.html")
    public String add(Model model) {
        return "bg/questions_add";
    }

    @GetMapping("/v/{id}.html")
    public String update(@PathVariable Long id, Model model) {
        Param param = Param.builder().build().add("id", id);
        try {
            QuestionsModel questionsModel = questionsService.findOne(param);
            model.addAttribute("question", questionsModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "bg/questions_add";
    }

    @DeleteMapping("/d/{id}")
    @ResponseBody
    public BaseResult delete(@PathVariable Long id) {
        Param param = Param.builder().build().add("id", id);
        try {
            questionsService.delete(param);
            return BaseResultUtil.success(null);
        } catch (CURDException e) {
            e.printStackTrace();
            return BaseResultUtil.fail("删除失败");
        }
    }


    @PostMapping("/c")
    @ResponseBody
    public BaseResult insert(@RequestBody QuestionsModel questionsModel) {
        try {
            if (questionsModel.getId() != null) {
                questionsService.update(questionsModel);
            } else {
                questionsService.insert(questionsModel);

            }
            return BaseResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResultUtil.fail("异常");
        }
    }
}
