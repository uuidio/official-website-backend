package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-20 16:42
 */
@Data
public class ProductCategoryModel extends ZQModel {
    private String name;
    private Integer sort;
}
