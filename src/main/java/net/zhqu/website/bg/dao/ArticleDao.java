package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.ArticleModel;

import java.util.List;

/**
 * Created by hao lai on 2018/11/21.
 */
public interface ArticleDao extends ZQDao<ArticleModel> {

    int count(Param param);

    Long insertGetId(ArticleModel articleModel);

    void clickNumAddOne(Param param);

    List<ArticleModel> findAllPageClickNum(Param param);

    void deleteOfCategory(Param param);

    void deleteOfTwoMenu(Param param);

    List<ArticleModel> findSummaryPage(Param param);
}
