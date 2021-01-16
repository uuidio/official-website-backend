package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.framework.service.SessionService;
import net.zhqu.website.bg.dao.FooterDao;
import net.zhqu.website.bg.model.FooterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao lai on 2018/11/13.
 */
@Service
public class BGFooterService extends AbstractZQServiceImp<FooterModel> {

    @Autowired
    private FooterDao footerDao;

    @Autowired
    private SessionService sessionService;


    @Override
    public ZQDao<FooterModel> getDao() {
        return footerDao;
    }


    public Long insertFooter(FooterModel footerModel) {
        footerDao.insertFooter(footerModel);
        Long id = footerModel.getId();
        return id;
    }

    public int count() {
        return footerDao.count();
    }

    public List<FooterModel> findFooter(Param param){
        return footerDao.findFooter(param);
    }
}
