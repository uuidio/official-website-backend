package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * Created by hao lai on 2018/11/15.
 */
@Data
public class PageInfoModel extends ZQModel {

    private Long id;
    private String banner;
    private String code;
    private String title;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;

}
