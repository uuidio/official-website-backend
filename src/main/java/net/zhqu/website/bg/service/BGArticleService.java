package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.ArticleDao;
import net.zhqu.website.bg.model.ArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao lai on 2018/11/21.
 */
@Service
public class BGArticleService extends AbstractZQServiceImp<ArticleModel> {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public ZQDao<ArticleModel> getDao() {
        return articleDao;
    }

    public int count(Param param) {
        return articleDao.count(param);
    }

    public void clickNumAddOne(Param param) {
        articleDao.clickNumAddOne(param);
    }

    public List<ArticleModel> findAllPageClickNum(Param param) {
        return articleDao.findAllPageClickNum(param);
    }

    public void deleteOfCategory(Param param) {
        articleDao.deleteOfCategory(param);
    }

    public void deleteOfTwoMenu(Param param) {
        articleDao.deleteOfTwoMenu(param);
    }


    public List<ArticleModel> findSummaryPage(Param param) {
        return articleDao.findSummaryPage(param);
    }
}
