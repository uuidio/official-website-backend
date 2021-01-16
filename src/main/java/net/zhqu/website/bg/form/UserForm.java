package net.zhqu.website.bg.form;

import lombok.Data;
import net.zhqu.framework.entity.Org;
import net.zhqu.framework.entity.Role;

import java.util.Set;

/**
 * Created By yong On 2018/5/18
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class UserForm {
    private Long id;
    private String name;
    private String nickname;
    private String password;
    private String mobile;
    private String email;
    private Org mainOrg;
    private Set<Role> roles;
    private Set<Org> orgs;
    private String type;
    private Boolean isDisable;
}
