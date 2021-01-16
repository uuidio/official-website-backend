package net.zhqu.website.bg.model;

import lombok.Data;

import java.util.List;

/**
 * Created By yong On 2018/6/27
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class MenuModel {
    private Long id;
    private String url;
    private String name;
    private String code;
    private Integer level;
    private String path;
    private Integer sort;
    private Long parentId;
    private Boolean isParent;
    private String type;
    private List<MenuModel> children;
    private Boolean spread = true;
    private String icon;

}
