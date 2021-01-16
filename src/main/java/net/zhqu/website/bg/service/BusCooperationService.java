package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.BusCooperationDao;
import net.zhqu.website.bg.model.BusCooperationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusCooperationService extends AbstractZQServiceImp<BusCooperationModel> {

    @Autowired
    private BusCooperationDao busCooperationDao;

    @Override
    public ZQDao<BusCooperationModel> getDao() {
        return busCooperationDao;
    }
}
