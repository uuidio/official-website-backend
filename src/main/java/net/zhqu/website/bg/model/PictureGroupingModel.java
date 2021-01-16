package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created By xin cai On 2018/5/24
 *  图片分组
 * @author xin cai (1428774847@qq.com)
 */
@Data
public class PictureGroupingModel extends ZQModel {
    private Long id;
    private String pictureOwnership; // 图片分组名称
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;   //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedTime;//最后修改时间
}
