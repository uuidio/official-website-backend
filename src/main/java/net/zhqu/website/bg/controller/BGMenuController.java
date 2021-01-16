package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.model.MenuModel;
import net.zhqu.website.bg.model.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By yong On 2018/6/27
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/menu")
public class BGMenuController {
    @Autowired
    private MenuService menuService;


    @GetMapping("/v/index.html")
    public String index() {
        return "bg/menu_index";
    }


    @GetMapping("/r/finAllForTree.json")
    public @ResponseBody
    BaseResult finAllForTree() {
        try {
            List<MenuModel> orgs = menuService.findAllForTree(1, null, false);
            return BaseResultUtil.success(orgs);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }

    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/menu_add";
    }

    @GetMapping("/v/{id}.html")
    public String updateHtml(@PathVariable Long id, Model model) {
        try {
            MenuModel menuModel = menuService.findById(id);
            model.addAttribute("menu", menuModel);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/menu_add";
    }

    @PostMapping("/c")
    public @ResponseBody
    BaseResult create(MenuModel menuModel) {
        try {
            menuService.insert(menuModel);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/u")
    public @ResponseBody
    BaseResult update(MenuModel menuModel) {
        try {
            menuService.update(menuModel);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/d")
    public @ResponseBody
    BaseResult delete(@RequestBody MenuModel menuModel) {
        try {
            menuService.delete(Param.builder().build().add(Param.ID, menuModel.getId()));
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
