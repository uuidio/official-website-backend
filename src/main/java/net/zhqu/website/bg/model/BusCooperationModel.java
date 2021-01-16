package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

@Data
public class BusCooperationModel extends ZQModel {

    private Long id;
    private String name;
    private String company;
    private String mobile;
    private String email;
    private String content;

}
