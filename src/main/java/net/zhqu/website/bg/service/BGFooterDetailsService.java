package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.framework.service.SessionService;
import net.zhqu.website.bg.dao.FooterDetailsDao;
import net.zhqu.website.bg.model.FooterDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao lai on 2018/11/14.
 */
@Service
public class BGFooterDetailsService extends AbstractZQServiceImp<FooterDetailsModel> {

    @Autowired
    private FooterDetailsDao footerDetailsDao;

    @Autowired
    private SessionService sessionService;

    @Override
    public ZQDao<FooterDetailsModel> getDao() {
        return footerDetailsDao;
    }


    public List<FooterDetailsModel> findAlliden() {
        return footerDetailsDao.findAllIden();
    }


    public void deleteForFooter(Param param) {
        footerDetailsDao.deleteForFooter(param);
    }
}
