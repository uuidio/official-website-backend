package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * Created by hao lai on 2018/11/20.
 */
@Data
public class QuestionsModel extends ZQModel{

    private Long id;
    private String title;
    private String content;

}
