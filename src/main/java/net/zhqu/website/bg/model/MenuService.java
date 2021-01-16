package net.zhqu.website.bg.model;

import net.zhqu.framework.entity.Param;
import net.zhqu.framework.entity.Permission;
import net.zhqu.framework.entity.PermissionUrl;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.PermissionService;
import net.zhqu.framework.utils.ParamUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created By yong On 2018/6/27
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Service
public class MenuService {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ParamUtil paramUtil;


    public void insert(MenuModel model) throws CURDException {
        Permission permission = toPerm(model);
        permission.setType("menu");
        permissionService.insert(permission);

    }

    public void update(MenuModel model)  throws CURDException {
        Permission permission = toPerm(model);
        permission.setType("menu");
        permissionService.update(permission);
    }

    public List<MenuModel> findAllForTree(Integer level, Long parentId,boolean isAuthorized) throws CURDException {
        List<MenuModel> returnList = new ArrayList<MenuModel>();
        List<MenuModel> list = null;
        if (parentId == null) {
            list = findAllByLevel(level,isAuthorized);

        }else
        {
            list = findAllByParentId(parentId,isAuthorized);
        }
        for (MenuModel menu : list) {
            if (menu.getIsParent()) {
                List<MenuModel> children = findAllForTree(null,menu.getId(),isAuthorized);
                if (!CollectionUtils.isEmpty(children)) {
                    menu.setChildren(children);
                }
            }
            returnList.add(menu);
        }
        return returnList;
    }

    public List<MenuModel> findAll(Param param) throws CURDException {
        List<Permission> permissions = permissionService.findAllWithRolesAndUrls(param.add("type","menu"));
        return toMenu(permissions);
    }

    public List<MenuModel> toMenu(List<Permission> permissions) {
        List<MenuModel> menuModels = new ArrayList<MenuModel>();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (Permission permission : permissions) {
                MenuModel menuModel = toMenu(permission);
                if (menuModel != null) {
                    menuModels.add(menuModel);
                }
            }
        }
        return menuModels;
    }

    public MenuModel toMenu(Permission permission) {
        if (permission == null) {
            return null;
        }
        MenuModel menuModel = new MenuModel();
        BeanUtils.copyProperties(permission, menuModel);
        Set<PermissionUrl> permissionUrls = permission.getUrls();
        if (!CollectionUtils.isEmpty(permissionUrls)) {
            PermissionUrl permissionUrl = permissionUrls.iterator().next();
            menuModel.setUrl(permissionUrl.getUrl());
        }
        return menuModel;
    }

    public Permission toPerm(MenuModel menuModel) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(menuModel, permission);
        Set<PermissionUrl> permissionUrls = new HashSet<PermissionUrl>();
        if (StringUtils.isNotBlank(menuModel.getUrl())) {
            PermissionUrl permissionUrl = new PermissionUrl();
            permissionUrl.setUrl(menuModel.getUrl());
            permissionUrls.add(permissionUrl);
        }
        permission.setUrls(permissionUrls);
        return permission;
    }


    public List<MenuModel> findAllByLevel(Integer level , boolean isAuthorized) throws CURDException {
        Param param = Param.builder().build().add("level", level).add("isDelete", false).add("orderBySortAsc", true);
        if (isAuthorized && paramUtil.getCurrentUser().getId()!=0) {
            param.add("authorized", paramUtil.getCurrentUser().getId());
        }
        return this.findAll(param);
    }

    public List<MenuModel> findAllByParentId(Long parentId ,boolean isAuthorized) throws CURDException {
        Param param = Param.builder().build().add("parentId", parentId).add("isDelete", false).add("orderBySortAsc", true);
        if (isAuthorized && paramUtil.getCurrentUser().getId()!=0) {
            param.add("authorized", paramUtil.getCurrentUser().getId());
        }
        return this.findAll(param);
    }

    public MenuModel findById(Long id) throws CURDException {
        Permission permission = permissionService.findOneWithUrls(id);
        return toMenu(permission);
    }

    public void delete(Param param) throws CURDException {
        permissionService.delete(param);
    }
}
