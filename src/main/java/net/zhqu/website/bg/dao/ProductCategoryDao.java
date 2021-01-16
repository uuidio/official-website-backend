package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.website.bg.model.ProductCategoryModel;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-20 16:43
 */
public interface ProductCategoryDao extends ZQDao<ProductCategoryModel> {
    List<ProductCategoryModel> findAll(Param param);

}
