package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.CommonCodeModel;

import java.util.List;

/**
 * Created By xin cai On 2018/5/17
 *  系统参数Dao层
 * @author xin cai (1428774847@qq.com)
 */
public interface CommonCodeDao extends ZQDao<CommonCodeModel> {
    List<CommonCodeModel> query(Param param);
}
