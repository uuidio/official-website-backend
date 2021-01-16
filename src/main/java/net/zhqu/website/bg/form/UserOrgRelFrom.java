package net.zhqu.website.bg.form;

import lombok.Data;
import net.zhqu.framework.entity.Org;

import java.util.List;

/**
 * Created By xin cai On 2018/6/26
 *  合伙人角色权限提交数据实体
 * @author xin cai (1428774847@qq.com)
 */
@Data
public class UserOrgRelFrom {
    public static final String PARTNER_USER_ID = "partnerUserId";
    public static final String PARTNER_ORG_ID = "partnerOrgId";
    private Long partnerUserId; // 合伙人
    private List<Org> orgList; // 学校集合
}
