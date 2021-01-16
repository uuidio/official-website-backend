package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.website.bg.model.ProductModel;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-21 10:24
 */
public interface ProductDao extends ZQDao<ProductModel> {
    void addImages(Param param) throws CURDException;
    void deleteAllImages(Param param) throws CURDException;

    void addContents(Param param) throws CURDException;
    void deleteAllContents(Param param) throws CURDException;


    List<ProductModel> findAll(Param param) throws CURDException;

}
