package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

import java.util.List;

/**
 * Created by hao lai on 2018/11/13.
 */
@Data
public class FooterModel extends ZQModel {
    private Long id;
    private int sort;
    private List<FooterDetailsModel> footerDetails;
    private String name;

}
