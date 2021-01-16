package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.IntroducePageDao;
import net.zhqu.website.bg.model.IntroducePageModel;
import net.zhqu.website.bg.model.IntroducePageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hao lai on 2018/11/16.
 */
@Service
public class BGIntroducePageService extends AbstractZQServiceImp<IntroducePageModel> {

    @Autowired
    private IntroducePageDao introducePageDao;


    @Override
    public ZQDao<IntroducePageModel> getDao() {
        return introducePageDao;
    }


    public IntroducePageVo findAllR(Param param) {
        return introducePageDao.findAllR(param);
    }
}
