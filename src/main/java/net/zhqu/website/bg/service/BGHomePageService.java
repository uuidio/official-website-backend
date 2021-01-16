package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.HomePageDao;
import net.zhqu.website.bg.model.HomePageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hao lai on 2018/11/8.
 */
@Service
public class BGHomePageService extends AbstractZQServiceImp<HomePageModel> {

    @Autowired
    private HomePageDao homePageDao;


    @Override
    public ZQDao<HomePageModel> getDao() {
        return homePageDao;
    }

}
