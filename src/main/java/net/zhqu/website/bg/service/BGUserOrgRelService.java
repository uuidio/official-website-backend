package net.zhqu.website.bg.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Org;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.entity.User;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.framework.service.OrgService;
import net.zhqu.framework.service.SessionService;
import net.zhqu.framework.service.UserService;
import net.zhqu.website.bg.dao.UserOrgRelDao;
import net.zhqu.website.bg.form.UserOrgRelFrom;
import net.zhqu.website.bg.model.UserOrgRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By xin cai On 2018/6/26
 *  合伙人角色权限Service
 * @author xin cai (1428774847@qq.com)
 */
@Slf4j
@Service
public class BGUserOrgRelService extends AbstractZQServiceImp<UserOrgRel> {
    @Autowired
    private UserOrgRelDao userOrgRelDao;

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;


    @Autowired
    private SessionService sessionService;

    @Value("${net.zhqu.framework.data.authority}")
    private Boolean authority;

    public ZQDao<UserOrgRel> getDao() {
        return userOrgRelDao;
    }

    public void permIndex(Long partnerUserId, Model model) {
        try {
            Param param = Param.builder().build().add(UserOrgRelFrom.PARTNER_USER_ID, partnerUserId);
            List<UserOrgRel> userOrgRelList = userOrgRelDao.query(param);
            Long[] selectPerms = null;
            if (userOrgRelList.size() > 0) {
                int i = 0;
                selectPerms = new Long[userOrgRelList.size()];
                model.addAttribute("partnerUser", JSONObject.toJSON(userOrgRelList.get(0).getPartnerUser()));
                for (UserOrgRel userOrgRel : userOrgRelList) {
                    selectPerms[i] = userOrgRel.getPartnerOrg().getId();
                    i++;
                }
            }
            else {
                Param partnerParam = Param.builder().build().add("id", partnerUserId);
                User user = userService.findOne(partnerParam);
                model.addAttribute("partnerUser", JSONObject.toJSON(user));
            }
            Param orgParam = Param.builder().build().add("isDelete", false);
            List<Org> orgList = orgService.findAll(orgParam);

            String json = JSONObject.toJSONString(orgList);
            model.addAttribute("perms", json);
            model.addAttribute("selectPerms", JSONObject.toJSON(selectPerms));
        }catch (CURDException e) {
            log.error(e.getMessage(), e);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(UserOrgRelFrom userOrgRelFrom) {
        Param param = Param.builder().build().add(UserOrgRelFrom.PARTNER_USER_ID, userOrgRelFrom.getPartnerUserId());
        userOrgRelDao.delete(param);
        List<Org> orgList = userOrgRelFrom.getOrgList();
        User user = sessionService.getCurrentUser();
        Org org = sessionService.getCurrentOrg();
        for(Org partnerOrg : orgList) {
            UserOrgRel userOrgRel = new UserOrgRel();
            userOrgRel.setOrg(org);
            userOrgRel.setPartnerUser(user);
            userOrgRel.setPartnerOrg(partnerOrg);
            User partnerUser = new User();
            partnerUser.setId(userOrgRelFrom.getPartnerUserId());
            userOrgRel.setPartnerUser(partnerUser);
            userOrgRelDao.insert(userOrgRel);
        }
    }

    public List<UserOrgRel> getByUserId(Long userId) throws CURDException {
        List<UserOrgRel> userOrgRelList = new ArrayList<>();
        if(userId != 0 && !authority.equals(false)) {
            Param param = Param.builder().build().add(UserOrgRelFrom.PARTNER_USER_ID, userId);
            userOrgRelList = userOrgRelDao.query(param);
        }else {
            List<Org> orgList = orgService.findAll(Param.builder().build());
            for(Org org : orgList) {
                UserOrgRel userOrgRel = new UserOrgRel();
                userOrgRel.setPartnerOrg(org);
                userOrgRelList.add(userOrgRel);
            }
        }
        return userOrgRelList;
    }

    public List<Long> getUserOrgRel() {
        Long userId = sessionService.getCurrentUser().getId();
        return getUserOrgRel(userId);
    }
    public List<Long> getUserOrgRel(Long userId) {
        List<Long> orgIdList = new ArrayList<>();
        if(userId != 0 && !authority.equals(false)) {
            Param param = Param.builder().build().add(UserOrgRelFrom.PARTNER_USER_ID, userId);
            List<UserOrgRel> userOrgRelList = userOrgRelDao.query(param);
            if (userOrgRelList.size() > 0) {
                orgIdList = new ArrayList<>();
                for (UserOrgRel userOrgRel : userOrgRelList) {
                    orgIdList.add(userOrgRel.getPartnerOrg().getId());
                }
            }
        }
        return orgIdList;
    }

}
