package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.PictureModel;

/**
 * Created By xin cai On 2018/5/24
 *  t图片Dao
 * @author xin cai (1428774847@qq.com)
 */
public interface PictureDao extends ZQDao<PictureModel> {
    void updateNotEmpty(Param param);

    void deletePictureList(Param param);
}
