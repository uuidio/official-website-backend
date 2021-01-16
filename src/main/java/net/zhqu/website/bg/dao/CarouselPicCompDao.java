package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.CarouselPicCompModel;

import java.util.List;

public interface CarouselPicCompDao extends ZQDao<CarouselPicCompModel> {
    List<CarouselPicCompModel> findAll(Param param);

    CarouselPicCompModel randOne(Param param);
}
