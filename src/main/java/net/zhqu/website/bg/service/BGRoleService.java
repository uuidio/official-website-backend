package net.zhqu.website.bg.service;

import net.zhqu.framework.entity.Param;
import net.zhqu.framework.entity.Role;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.RoleService;
import net.zhqu.framework.utils.UrlPermissionPool;
import net.zhqu.website.bg.form.RoleForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created By yong On 2018/5/22
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Service
public class BGRoleService extends RoleService {
    @Autowired
    private RoleService roleService;

    public RoleForm findRoleFormOne(Long id)throws CURDException {

        Role role = roleService.findById(id);
        return RoleToRoleForm(role);
    }

    public void insert(RoleForm roleForm) throws CURDException {
        if (StringUtils.isEmpty(roleForm.getName())) {
            throw new CURDException("名称不为空");
        }
        if (!roleForm.getName().startsWith("ROLE_")) {
            throw new CURDException("名称必须以ROLE_开头");
        }

        boolean isExist = isExist(roleForm.getName());
        if (isExist) {
            throw new CURDException("名称 " + roleForm.getName() + "已存在");
        }
        Role role = RoleFormToRole(roleForm);

        roleService.insert(role);
    }

    public void update(RoleForm roleForm)throws CURDException {
        Role role = RoleFormToRole(roleForm);
        roleService.update(role);
        UrlPermissionPool.getInstance().clear();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(RoleForm roleForm)throws CURDException {
        roleService.deleteAllPermissions(roleForm.getId());
        roleService.delete(Param.builder().build().add(Param.ID, roleForm.getId()));
        UrlPermissionPool.getInstance().clear();
    }

    public Role RoleFormToRole(RoleForm roleForm) {
        if (roleForm == null) {
            return null;
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleForm, role);
        return role;
    }

    public RoleForm RoleToRoleForm(Role role) {
        if (role == null) {
            return null;
        }
        RoleForm roleForm = new RoleForm();
        BeanUtils.copyProperties(role, roleForm);
        return roleForm;
    }

    public boolean isExist(String name) throws CURDException {
        Role role = findOne(Param.builder().build().add("name", name));
        if (role != null) {
            return true;
        }else
        {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void authorize(RoleForm roleForm) throws CURDException {

        if (roleForm.getId() == null) {
            throw new CURDException("参数不对");
        }
        Role role = findOne(Param.builder().build().add(Param.ID, roleForm.getId()));
        if (role == null) {
            throw new CURDException(roleForm.getId() + "不存在");
        }
        roleService.deleteAllPermissions(roleForm.getId());
        if (roleForm.getPermissions().size() > 0) {
            roleService.addPermissions(roleForm.getId(), roleForm.getPermissions());
        }
        UrlPermissionPool.getInstance().clear();
    }
}
