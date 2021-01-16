package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.User;
import net.zhqu.framework.entity.ZQModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created By xin cai On 2018/5/17
 *  系统参数
 * @author xin cai (1428774847@qq.com)
 */
@Data
public class CommonCodeModel extends ZQModel {
    private Long id;
    private String code; // 编码
    private String name; // 名称
    private String codeValue; // 值
    private String codeType; // 类型:系统参数,业务参数
    private Integer showOrder; // 展示顺序
    private String remark; // 备注
    private User creator; // 创建人
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;   //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedTime;//最后修改时间
}
