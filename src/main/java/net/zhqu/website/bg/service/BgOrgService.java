package net.zhqu.website.bg.service;

import net.zhqu.framework.entity.Org;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.OrgService;
import net.zhqu.website.bg.form.OrgForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created By yong On 2018/5/18
 *
 * @author taoyong xu(y__ong@outlook.com)
 */
@Service
public class BgOrgService extends OrgService {


    /**
     * 1.org增加记录
     * 2.学校增加记录
     * @param orgForm
     * @throws CURDException
     */
    @Transactional(rollbackFor=Exception.class)
    public void insert(OrgForm orgForm) throws CURDException {
        if (orgForm.getParentId() == null) {
            throw new CURDException("父级id不为空");
        }
        if (StringUtils.isEmpty(orgForm.getName())) {

            throw new CURDException("名称不为空");
        }
        Org org = new Org();
        BeanUtils.copyProperties(orgForm, org);
        super.insert(org);

    }

    public void update(OrgForm orgForm) throws CURDException {
        if (orgForm.getId() == null) {
            throw new CURDException("id不为空");
        }
        Org org = new Org();
        BeanUtils.copyProperties(orgForm, org);
        super.update(org);

    }

    public void delete(OrgForm orgForm) throws CURDException {
        super._delete(orgForm.getId());
    }

    public OrgForm findOrgFormOne(Long id) throws CURDException {
        Org org =findOne(Param.builder().build().add(Param.ID, id).add("isDelete", false));
        OrgForm orgForm = new OrgForm();
        BeanUtils.copyProperties(org, orgForm);
        return orgForm;
    }
}
