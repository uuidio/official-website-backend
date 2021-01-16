package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * Created by hao lai on 2018/11/8.
 */
@Data
public class LogoModel extends ZQModel{
    private Long id;
    private Long logo;
    private String logoUrl;
    private String name;
}
