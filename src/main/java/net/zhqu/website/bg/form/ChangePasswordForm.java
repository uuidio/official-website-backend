package net.zhqu.website.bg.form;

import lombok.Data;

/**
 * Created By yong On 2018/5/3
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Data
public class ChangePasswordForm {
    private Long id;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
