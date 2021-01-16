package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.IntroducePageModel;
import net.zhqu.website.bg.model.IntroducePageVo;

/**
 * Created by hao lai on 2018/11/16.
 */
public interface IntroducePageDao extends ZQDao<IntroducePageModel> {

    IntroducePageVo findAllR(Param param);
}
