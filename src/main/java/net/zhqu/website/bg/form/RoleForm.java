package net.zhqu.website.bg.form;

import lombok.Data;
import net.zhqu.framework.entity.Permission;

import java.util.Set;

/**
 * Created By yong On 2018/5/18
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class RoleForm {
    private Long id;
    private String name;
    private String alias;
    private Set<Permission> permissions;

}
