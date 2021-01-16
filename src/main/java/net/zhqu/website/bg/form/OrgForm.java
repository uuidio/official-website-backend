package net.zhqu.website.bg.form;

import lombok.Data;

/**
 * Created By yong On 2018/5/18
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class OrgForm {
    private Long id;   //orgId
    private String code;
    private String name;
    private Integer level;
    private String path;
    private Integer sort;
    private Boolean isDelete = false;
    private Long parentId;

}
