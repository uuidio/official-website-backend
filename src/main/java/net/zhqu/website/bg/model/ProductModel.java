package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-21 10:23
 */
@Data
public class ProductModel extends ZQModel {
    private String name;
    private String mainImage;
    private ProductCategoryModel category;
    private List<PictureModel> images;
    private List<ProductContentModel> contents;
}
