package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.FooterModel;

import java.util.List;

/**
 * Created by hao lai on 2018/11/13.
 */
public interface FooterDao extends ZQDao<FooterModel> {
    Long insertFooter(FooterModel footerModel);

    int count();

    List<FooterModel> findFooter(Param param);
}
