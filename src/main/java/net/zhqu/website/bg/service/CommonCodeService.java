package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.CommonCodeDao;
import net.zhqu.website.bg.model.CommonCodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By xin cai On 2018/5/17
 *  系统参数Service层
 * @author xin cai (1428774847@qq.com)
 */
@Service
public class CommonCodeService extends AbstractZQServiceImp<CommonCodeModel> {
    @Autowired
    private CommonCodeDao commonCodeDao;

    public ZQDao<CommonCodeModel> getDao() {
        return commonCodeDao;
    }


    //@Cacheable(value = "commonCode",key = "#code")
    public String findByCode(String code, String defaultValue) throws CURDException {
        CommonCodeModel commonCodeModel = findOne(Param.builder().build().add("code", code));
        if (commonCodeModel == null) {
            return defaultValue;
        }
        return commonCodeModel.getCodeValue();
    }

   // @CachePut(value = "commonCode", key = "#commonCodeModel.getCode()")
    public String updateWithCache(CommonCodeModel commonCodeModel) throws CURDException {
        update(commonCodeModel);
        return commonCodeModel.getCodeValue();
    }

    public List<CommonCodeModel> query(Param param) {
        return commonCodeDao.query(param);
    }
}
