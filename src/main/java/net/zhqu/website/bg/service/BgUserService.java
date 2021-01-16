package net.zhqu.website.bg.service;

import net.zhqu.framework.entity.Param;
import net.zhqu.framework.entity.User;
import net.zhqu.framework.enums.UserLoginLogLoginTypeEnum;
import net.zhqu.framework.enums.UserTypeEnum;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.UserService;
import net.zhqu.framework.utils.Util;
import net.zhqu.website.bg.form.UserForm;
import net.zhqu.website.util.GlobalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created By yong On 2018/5/18
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Service
public class BgUserService extends UserService {



    public void delete(UserForm userForm) throws CURDException {
        _delete(userForm.getId());
    }

    public void update(UserForm userForm)throws CURDException {
        if (userForm.getId() == null) {
            throw new CURDException("id 不为空");
        }
        User user = userFormToUser(userForm);
        super.update(user);

    }

    public void insert(UserForm userForm)throws CURDException {
        if (StringUtils.isEmpty(userForm.getName())) {
            throw new CURDException("name 不为空");
        }
        if (StringUtils.isEmpty(userForm.getPassword())) {
            throw new CURDException("password 不为空");
        }
        if (StringUtils.isEmpty(userForm.getType())) {
            userForm.setType(UserTypeEnum.ADMIN.getValue());
        }
        if (userForm.getMainOrg() == null || userForm.getMainOrg().getId() == null) {

            throw new CURDException("组织不为空");
        }

        User userData = findByName(userForm.getName());

        if (userData != null) {
            throw new CURDException("用户" + userData.getName() + "已注册");
        }
        User user = userFormToUser(userForm);
        super.insert(user);

    }

    public UserForm findUserFormOne(Long id)throws CURDException {
        User user = findOne(Param.builder().build().add(Param.ID, id).add("idDelete", false).add("ignorePerm", true));
        UserForm userForm = userToUserForm(user);
        return userForm;
    }


    public User userFormToUser(UserForm userForm) {
        if (userForm == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        return user;
    }

    public UserForm userToUserForm(User user) {
        if (user == null) {
            return null;
        }
        UserForm userForm = new UserForm();
        BeanUtils.copyProperties(user, userForm);
        return userForm;
    }

    @Transactional(rollbackFor = Exception.class)
    public void authorize(UserForm userForm) throws CURDException {
        if (userForm.getId() == null) {
            throw new CURDException("参数不对");
        }
        User user = findOne(Param.builder().build().add(Param.ID, userForm.getId()).add("ignorePerm",true));
        if (user == null) {
            throw new CURDException(userForm.getId() + "不存在");
        }
        saveUserRoles(user.getId(), userForm.getRoles());
    }

    @Transactional(rollbackFor = Exception.class)
    public void authorizeData(UserForm userForm) throws CURDException {
        if (userForm.getId() == null) {
            throw new CURDException("参数不对");
        }
        User user = findOne(Param.builder().build().add(Param.ID, userForm.getId()).add("ignorePerm",true));
        if (user == null) {
            throw new CURDException(userForm.getId() + "不存在");
        }

        saveUserOrgs(user.getId(), userForm.getOrgs());
    }


    public void logout(User user, String pushRegId, String type) throws CURDException {
        if (user != null) {

            addLoginLog(user.getId(), Util.getIp(GlobalUtil.getHttpServletRequest()), type, pushRegId, UserLoginLogLoginTypeEnum.LOGOUT.getValue(), "", paramUtil.getCurrentUser(), paramUtil.getCurrentOrg());
        }
        SecurityContextHolder.getContext().setAuthentication(null);  //每次进来都去掉认证，重新认证token
        GlobalUtil.getHttpServletRequest().getSession().removeAttribute("user");
        GlobalUtil.getHttpServletRequest().getSession().removeAttribute("org");
    }

    public void disable(UserForm userForm) throws CURDException {
        User user = new User();
        user.setId(userForm.getId());
        user.setIsDisable(true);
        update(user);
    }
    public void enable(UserForm userForm) throws CURDException {
        User user = new User();
        user.setId(userForm.getId());
        user.setIsDisable(false);
        update(user);
    }

}
