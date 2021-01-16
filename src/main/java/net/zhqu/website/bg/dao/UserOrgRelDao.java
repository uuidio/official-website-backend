package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.UserOrgRel;


import java.util.List;

/**
 * Created By xin cai On 2018/6/26
 *  合伙人角色权限Dao
 * @author xin cai (1428774847@qq.com)
 */
public interface UserOrgRelDao extends ZQDao<UserOrgRel> {
    List<UserOrgRel> query(Param param);
}
