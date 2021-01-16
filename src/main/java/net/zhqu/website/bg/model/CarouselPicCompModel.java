package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;

/**
 * Created By yong On 2018/7/4
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class CarouselPicCompModel extends ZQModel {
    private Long id;
    private String name;
    private String code;
    private String pic;
    private String cdnPic;
    private String url;
    private Boolean isBlank;
    private String sequence;
}
