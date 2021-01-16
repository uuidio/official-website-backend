package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.FooterDetailsModel;

import java.util.List;

/**
 * Created by hao lai on 2018/11/14.
 */
public interface FooterDetailsDao extends ZQDao<FooterDetailsModel>{


    List<FooterDetailsModel> findAllIden();

    void deleteForFooter(Param param);
}
