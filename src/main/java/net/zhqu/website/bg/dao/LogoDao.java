package net.zhqu.website.bg.dao;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.website.bg.model.LogoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hao lai on 2018/11/9.
 */
public interface LogoDao extends ZQDao<LogoModel>{

    void insertList(@Param("logoModels") List<LogoModel> logoModels);

    int count();
}
