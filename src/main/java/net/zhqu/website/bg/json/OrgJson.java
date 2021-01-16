package net.zhqu.website.bg.json;

import lombok.Data;
import net.zhqu.framework.entity.Org;

import java.util.List;

/**
 * Created By xin cai 2018/6/19
 * 组织JSON数据
 * @author xin cai(1428774847@qq.com)
 *
 */
@Data
public class OrgJson {
    private List<Org> orgList; // 分配的组织集合
    private Org selectOrg; // 默认选择的组织
}
