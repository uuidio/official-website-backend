package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Org;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.form.OrgForm;
import net.zhqu.website.bg.service.BgOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By yong On 2018/5/16
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/org")
public class BGOrgController {

    @Autowired
    private BgOrgService bgOrgService;

    @GetMapping("/v/index.html")
    public String index() {
        return "bg/org_index";
    }


    @GetMapping("/r/finAllForTree.json")
    public @ResponseBody
    BaseResult finAllForTree() {
        try {
            List<Org> orgs = bgOrgService.findAllForTree(1, null);
            return BaseResultUtil.success(orgs);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }

    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/org_add";
    }

    @GetMapping("/v/{id}.html")
    public String updateHtml(@PathVariable Long id, Model model) {
        try {
            OrgForm orgForm = bgOrgService.findOrgFormOne(id);
            model.addAttribute("orgForm", orgForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/org_add";
    }

    @PostMapping("/c")
    public @ResponseBody
    BaseResult create(OrgForm orgForm) {
        try {
            bgOrgService.insert(orgForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/u")
    public @ResponseBody
    BaseResult update(OrgForm orgForm) {
        try {
            bgOrgService.update(orgForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/d")
    public @ResponseBody
    BaseResult delete(@RequestBody OrgForm orgForm) {
        try {
            bgOrgService.delete(orgForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

}
