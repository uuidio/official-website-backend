package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.framework.service.SessionService;
import net.zhqu.website.bg.dao.LogoDao;
import net.zhqu.website.bg.model.LogoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hao lai on 2018/11/9.
 */
@Service
public class BGHomeLogoService extends AbstractZQServiceImp<LogoModel> {

    @Autowired
    private LogoDao logoDao;

    @Autowired
    private SessionService sessionService;

    @Override
    public ZQDao<LogoModel> getDao() {
        return logoDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<LogoModel> logoModelList) {
        Param param = Param.builder().build().add("logo", 1L);
        logoDao.delete(param);
        for (LogoModel logoModel : logoModelList) {
            logoModel.setCreator(sessionService.getCurrentUser());
            logoModel.setOrg(sessionService.getCurrentOrg());
            logoDao.insert(logoModel);
        }
    }


    public int count() {
        return logoDao.count();
    }
}
