package net.zhqu.website.bg.model;

import lombok.Data;
import net.zhqu.framework.entity.ZQModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created By xin cai On 2018/5/24
 *  图片
 * @author xin cai (1428774847@qq.com)
 */
@Data
public class PictureModel extends ZQModel {
    private Long id;
    private String imagePath; // 上传图片地址
    private String cndImagePath;//cdn图片地址
    private String oldName; // 上传图片旧名称
    private String newName; // 上传图片新名称
    private PictureGroupingModel pictureGrouping; // 图片归属分组
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;   //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedTime;//最后修改时间
}
