package net.zhqu.website.bg.service;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.CarouselPicCompDao;
import net.zhqu.website.bg.model.CarouselPicCompModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By yong On 2018/7/4
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Service
@Slf4j
public class BGCarouselPicCompService extends AbstractZQServiceImp<CarouselPicCompModel> {

    @Autowired
    private CarouselPicCompDao carouselPicCompDao;

    @Override
    public ZQDao<CarouselPicCompModel> getDao() {
        return carouselPicCompDao;
    }


    public List<CarouselPicCompModel> findAllByCode(String code) throws CURDException {
        return findAll(Param.builder().build().add("code", code).add("orderBySequenceAsc", true));
    }

    public List<CarouselPicCompModel> findAll(Param param) throws CURDException {
        return carouselPicCompDao.findAll(param);
    }

    public CarouselPicCompModel randOne(Param param) throws CURDException {
        return carouselPicCompDao.randOne(param);
    }
}
