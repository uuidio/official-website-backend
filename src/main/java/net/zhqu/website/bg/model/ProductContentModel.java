package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-21 16:27
 */
@Data
public class ProductContentModel extends ZQModel {
    private String content;
    private String title;
}
