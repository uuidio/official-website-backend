package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.website.bg.model.PictureGroupingModel;

import java.util.List;

/**
 * Created By xin cai On 2018/5/24
 *  图片分组Dao
 * @author xin cai (1428774847@qq.com)
 */
public interface PictureGroupingDao extends ZQDao<PictureGroupingModel> {
    List<PictureGroupingModel> findAll();
}
