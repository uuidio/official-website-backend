package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.PictureGroupingDao;
import net.zhqu.website.bg.model.PictureGroupingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By xin cai On 2018/5/24
 *  t图片分组Service
 * @author xin cai (1428774847@qq.com)
 */
@Service
public class PictureGroupingService extends AbstractZQServiceImp<PictureGroupingModel> {
    @Autowired
    private PictureGroupingDao pictureGroupingDao;

    public ZQDao<PictureGroupingModel> getDao() {
        return pictureGroupingDao;
    }

    public List<PictureGroupingModel> findAll() {
        return pictureGroupingDao.findAll();
    }
}
