package net.zhqu.website.bg.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.*;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.PermissionService;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.form.RoleForm;
import net.zhqu.website.bg.service.BGRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created By yong On 2018/5/22
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/role/")
public class BGRoleController {
    @Autowired
    private ParamUtil paramUtil;

    @Autowired
    public BGRoleService bgRoleService;

    @Autowired
    public PermissionService permissionService;

    @GetMapping("/v/index.html")
    public String index() {
        return "bg/role_index";
    }

    @GetMapping("/r/all.json")
    @ResponseBody
    public BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false)Integer limit) {
        Param param = paramUtil.getPageParam(page, limit);
        param.add("isDelete", false);
        try {
            List<Role> list = bgRoleService.findAllPage(param);
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/v/add.html")
    public String add() {
        return "bg/role_add";
    }

    @GetMapping("/v/{id}.html")
    public String updateHtml(@PathVariable Long id, Model model) {
        try {
            RoleForm roleForm = bgRoleService.findRoleFormOne(id);
            model.addAttribute("roleForm", roleForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/role_add";
    }
    @GetMapping("/v/perm/{id}.html")
    public String permHtml(@PathVariable Long id, Model model) {
        try {
            Role role = bgRoleService.findOneWithPerm(Param.builder().build().add(Param.ID, id));
            RoleForm roleForm = bgRoleService.RoleToRoleForm(role);
            Set<Permission> rolePermissions = role.getPermissions();
            Long[] selectPerms = null;
            if (!CollectionUtils.isEmpty(rolePermissions)) {
                selectPerms = new Long[rolePermissions.size()];
                Iterator<Permission> iterable = rolePermissions.iterator();
                int i = 0;
                while (iterable.hasNext()) {
                    Permission permission = iterable.next();
                    selectPerms[i] = permission.getId();
                    i++;
                }

            }

            List<Permission> permissions = permissionService.findAll();
            String json = JSONObject.toJSONString(permissions);
            model.addAttribute("perms", json);
            model.addAttribute("selectPerms", JSONObject.toJSON(selectPerms));
            model.addAttribute("roleForm", roleForm);
        } catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

        return "bg/role_perm";
    }

    @PostMapping("/c")
    public @ResponseBody
    BaseResult create(RoleForm roleForm) {
        try {
            bgRoleService.insert(roleForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
    @PostMapping("/u")
    public @ResponseBody
    BaseResult update(RoleForm roleForm) {
        try {
            bgRoleService.update(roleForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/d")
    public @ResponseBody
    BaseResult delete(@RequestBody RoleForm roleForm) {
        try {
            bgRoleService.delete(roleForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @PostMapping("/authorize")
    public @ResponseBody
    BaseResult authorize(@RequestBody RoleForm roleForm) {
        try {
            bgRoleService.authorize(roleForm);
            return BaseResultUtil.success("");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
