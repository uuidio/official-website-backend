package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.PageInfoDao;
import net.zhqu.website.bg.model.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BGPageInfoService extends AbstractZQServiceImp<PageInfoModel> {

    @Autowired
    private PageInfoDao pageInfoDao;

    @Override
    public ZQDao<PageInfoModel> getDao() {
        return pageInfoDao;
    }

    @Override
    public int update(PageInfoModel model) throws CURDException {
        if (model.getId() == null || StringUtils.isEmpty(model.getCode())) {
            throw new CURDException("id或code不为空");
        }
        return super.update(model);
    }
}
