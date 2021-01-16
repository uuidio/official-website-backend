package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.Org;
import net.zhqu.framework.entity.User;
import net.zhqu.framework.entity.ZQModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created By xin cai On 2018/6/26
 *  合伙人角色权限表
 * @author xin cai (1428774847@qq.com)
 */
@Data
public class UserOrgRel extends ZQModel {
    private Long id;
    private User partnerUser; // 合伙人
    private Org partnerOrg; // 学校
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;   //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedTime;//最后修改时间
}
