package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.QuestionsDao;
import net.zhqu.website.bg.model.QuestionsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService extends AbstractZQServiceImp<QuestionsModel> {

    @Autowired
    private QuestionsDao questionsDao;

    @Override
    public ZQDao<QuestionsModel> getDao() {
        return questionsDao;
    }
}
