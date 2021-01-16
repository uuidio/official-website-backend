package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

import java.util.Date;
import java.util.List;

/**
 * Created by hao lai on 2018/11/21.
 */
@Data
public class ArticleModel extends ZQModel {

    private Long    id;
    private String  title;
    private String  content;
    private Long    clickNum;
    private String  image;
    private String  cover;
    private Integer  sort;

    private Long  oneKeyCollege;
    private String articleUser;

    private String summary;

    private String lastMonth;
}
