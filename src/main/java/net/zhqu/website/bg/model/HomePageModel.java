package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

import java.util.List;

/**
 * Created by hao lai on 2018/11/8.
 */
@Data
public class HomePageModel extends ZQModel{
    private Long id;
    private String seoTitle;
    private String seoKeywords;
    private String seoDescription;

}
