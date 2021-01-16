package net.zhqu.website.bg.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.*;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.form.ChangePasswordForm;
import net.zhqu.website.bg.form.UserForm;
import net.zhqu.website.bg.service.BGRoleService;
import net.zhqu.website.bg.service.BgOrgService;
import net.zhqu.website.bg.service.BgUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created By yong On 2018/5/17
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/user")
public class BGUserController {

    @Autowired
    private BgUserService bgUserService;

    @Autowired
    private ParamUtil paramUtil;

    @Autowired
    private BGRoleService bgRoleService;

    @Autowired
    private BgOrgService bgOrgService;

    @GetMapping("/r/findByOrg.json")
    public @ResponseBody
    BaseResult findByOrg(@RequestParam(required = false) Long orgId, @RequestParam(required = false) Integer page, @RequestParam(required = false)Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);
        param.add(Param.CUR_ORG_ID, orgId);
        param.add("isDelete", false);
        if (orgId == null || orgId == 1) {

            param.add("ignorePerm", true);
        }
        try {
            List<User> list = bgUserService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/user_add";
    }

    @GetMapping("/v/{id}.html")
    public String updateHtml(@PathVariable Long id, Model model) {
        try {
            UserForm userForm = bgUserService.findUserFormOne(id);
            model.addAttribute("userForm", userForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/user_add";
    }

    @PostMapping("/c")
    public @ResponseBody
    BaseResult create(UserForm orgForm) {
        try {
            bgUserService.insert(orgForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/u")
    public @ResponseBody
    BaseResult update(UserForm userForm) {
        try {
            bgUserService.update(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/d")
    public @ResponseBody
    BaseResult delete(@RequestBody UserForm userForm) {
        try {
            bgUserService.delete(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/perm/{id}.html")
    public String permHtml(@PathVariable Long id, Model model) {
        try {
            Param param = Param.builder().build().add(Param.ID, id).add("ignorePerm", true);

            User user = bgUserService.findOneWithAll(param);

            if (user == null) {
                throw new CURDException(id+"不存在");
            }
            UserForm userForm = bgUserService.userToUserForm(user);
            Set<Role> rolePermissions = user.getRoles();
            Long[] selectRole = null;
            if (!CollectionUtils.isEmpty(rolePermissions)) {
                selectRole = new Long[rolePermissions.size()];
                Iterator<Role> iterable = rolePermissions.iterator();
                int i = 0;
                while (iterable.hasNext()) {
                    Role role = iterable.next();
                    selectRole[i] = role.getId();
                    i++;
                }

            }

            List<Role> roles = bgRoleService.findAll(Param.builder().build());
            String json = JSONObject.toJSONString(roles);
            model.addAttribute("roles", json);
            model.addAttribute("selectRoles", JSONObject.toJSON(selectRole));
            model.addAttribute("userForm", userForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/user_perm";
    }


    @GetMapping("/v/permData/{id}.html")
    public String permDataHtml(@PathVariable Long id, Model model) {
        try {

            Param param = Param.builder().build().add(Param.ID, id).add("ignorePerm", true);

            User user = bgUserService.findOneWithAll(param);
            if (user == null) {
                throw new CURDException(id+"不存在");
            }
            UserForm userForm = bgUserService.userToUserForm(user);
            Set<Org> orgPermissions = user.getOrgs();
            Long[] selectOrg = null;
            if (!CollectionUtils.isEmpty(orgPermissions)) {
                selectOrg = new Long[orgPermissions.size()];
                Iterator<Org> iterable = orgPermissions.iterator();
                int i = 0;
                while (iterable.hasNext()) {
                    Org org = iterable.next();
                    selectOrg[i] = org.getId();
                    i++;
                }
            }

            List<Org> orgs = bgOrgService.findAll(Param.builder().build().add("isDelete", false));
            String json = JSONObject.toJSONString(orgs);
            model.addAttribute("orgs", json);
            model.addAttribute("selectOrgs", JSONObject.toJSON(selectOrg));
            model.addAttribute("userForm", userForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/user_perm_data";
    }

    @PostMapping("/authorize")
    public @ResponseBody
    BaseResult authorize(@RequestBody UserForm userForm) {
        try {
            bgUserService.authorize(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/authorizeData")
    public @ResponseBody
    BaseResult authorizeData(@RequestBody UserForm userForm) {
        try {
            bgUserService.authorizeData(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }



    @GetMapping("/changePassword.html")
    public String changePassword() {
        return "bg/user_changePassword";
    }

    @PostMapping("/changePassword")
    public @ResponseBody
    BaseResult
    changePassword(ChangePasswordForm changePasswordForm, Model model) {

        if (StringUtils.isBlank(changePasswordForm.getOldPassword())) {
            model.addAttribute("errorOldPassword", "密码不为空");
            return BaseResultUtil.fail("密码不为空");
        }
        if (StringUtils.isBlank(changePasswordForm.getNewPassword())) {
            model.addAttribute("errorNewPassword", "新密码不为空");
            return BaseResultUtil.fail("新密码不为空");
        }

        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getNewPasswordConfirm())) {
            model.addAttribute("errorNewPasswordConfirm", "新密码两次输入不一样");
            return BaseResultUtil.fail("新密码两次输入不一样");
        }
        boolean b = false;
        try {
            b = bgUserService.changePassordWord(paramUtil.getCurrentUser().getId(), changePasswordForm.getOldPassword(), changePasswordForm.getNewPassword(), changePasswordForm.getNewPasswordConfirm());
        } catch (CURDException e) {
            model.addAttribute("error", "更新失败");
            return BaseResultUtil.fail("更新失败");
        }
        if (b) {
            model.addAttribute("success", "修改成功");
        }else
        {
            model.addAttribute("errorOldPassword", "旧密码不对");
            return BaseResultUtil.fail("旧密码不对");
        }
        return BaseResultUtil.success("修改成功");

    }

    @PostMapping("/u/disable")
    public @ResponseBody
    BaseResult disable(@RequestBody UserForm userForm) {
        try {
            bgUserService.disable(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/u/enable")
    public @ResponseBody
    BaseResult enable(@RequestBody UserForm userForm) {
        try {
            bgUserService.enable(userForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

}
