package net.zhqu.website.bg.service;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.ProductCategoryDao;
import net.zhqu.website.bg.model.ProductCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-20 16:43
 */
@Slf4j
@Service
public class ProductCategoryService extends AbstractZQServiceImp<ProductCategoryModel> {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ZQDao<ProductCategoryModel> getDao() {
        return productCategoryDao;
    }

    @Override
    public void insert(ProductCategoryModel model) throws CURDException {
        if (model.getSort() == null) {
            model.setSort(1);
        }
        super.insert(model);
    }

    public List<ProductCategoryModel> findAll() {
        return productCategoryDao.findAll(Param.builder().build());
    }
}
