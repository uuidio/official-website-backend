package net.zhqu.website.bg.controller;

import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Org;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.entity.User;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.OrgService;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.service.UserService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.form.UserOrgRelFrom;
import net.zhqu.website.bg.json.OrgJson;
import net.zhqu.website.bg.model.UserOrgRel;
import net.zhqu.website.bg.service.BGUserOrgRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bg/userOrgRel")
public class BGUserOrgRelController {
    @Autowired
    private BGUserOrgRelService userOrgRelService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrgService orgService;


    @GetMapping("v/perm/{partnerUserId}.html")
    public String permIndex(@PathVariable Long userId, Model model) {

        try {
            User user = userService.findOneWithAll(userId);
            user.getOrgs();
            model.addAttribute("user", user);
        } catch (CURDException e) {
            e.printStackTrace();
        }

        return "bg/partner_perm";
    }

    @PostMapping("/c")
    @ResponseBody
    public BaseResult inset(@RequestBody UserOrgRelFrom userOrgRelFrom) {
        userOrgRelService.insert(userOrgRelFrom);
        return BaseResultUtil.success("添加成功");
    }

    @GetMapping("/r/{orgId}")
    public String updateOrgId(@PathVariable Long orgId) {
        Param param = Param.builder().build().add("id", orgId);
        param.add("currentUserAuthData", sessionService.getCurrentUser().getId());
        try {
            Org org = orgService.findOne(param);
            if (org == null) {
                throw new CURDException("数据未授权");
            }
            sessionService.setCurrentOrg(null);
            sessionService.setCurrentOrg(org);

            return "redirect:/";
        }catch (CURDException e) {
            return "redirect:/";
        }
    }

    @GetMapping("/r")
    @ResponseBody
    public BaseResult getUserOrgRel() {
        Long userId = sessionService.getCurrentUser().getId();
        OrgJson orgJson = new OrgJson();
        try {
            List<UserOrgRel> userOrgRelList = userOrgRelService.getByUserId(userId);
            List<Org> orgList = new ArrayList<>();
            for (UserOrgRel userOrgRel : userOrgRelList) {
                orgList.add(userOrgRel.getPartnerOrg());
            }
            orgJson.setOrgList(orgList);
                orgJson.setSelectOrg(sessionService.getCurrentOrg());

            return BaseResultUtil.success(orgJson);
        }catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
