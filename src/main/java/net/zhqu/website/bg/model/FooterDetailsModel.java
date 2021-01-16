package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * Created by hao lai on 2018/11/14.
 */
@Data
public class FooterDetailsModel extends ZQModel {
    private Long id;
    private String name;
    private String url;
    private String content;
    private FooterModel footer;
}
