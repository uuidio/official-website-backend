package net.zhqu.website.bg.service;

import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.website.bg.dao.PictureDao;
import net.zhqu.website.bg.model.PictureGroupingModel;
import net.zhqu.website.bg.model.PictureModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created By xin cai On 2018/5/24
 *  t图片Service
 * @author xin cai (1428774847@qq.com)
 */
@Service
public class PictureService extends AbstractZQServiceImp<PictureModel> {
    @Autowired
    private PictureDao pictureDao;

    private static Logger log = LoggerFactory.getLogger(PictureService.class);

    @Value("${net.zhqu.framework.relativeUploadPath}")
    private String relativeUploadPath;

    @Value("${net.zhqu.framework.uploadPath}")
    private String uploadPath;

    public ZQDao<PictureModel> getDao() {
        return pictureDao;
    }

    /**
     *  图片上传
     * @param multipartFiles
     * @param request
     * @param pictureGroupingId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public BaseResult upLoadPictureList(MultipartFile[] multipartFiles, HttpServletRequest request, Long pictureGroupingId) {
        List<PictureModel> dataList = new ArrayList<PictureModel>();
        if (multipartFiles.length > 0) {
            try {
                for (MultipartFile multipartFile : multipartFiles) {
                    // 图片名
                    String originImgName = multipartFile.getOriginalFilename();
                    String newImgName = new Date().getTime() + ".jpg";
                    // 新图片路径
                    File targetFile = new File(uploadPath, newImgName);
                    multipartFile.transferTo(targetFile);
                    String url =  relativeUploadPath+newImgName;
                    PictureModel productPicture = new PictureModel();
                    productPicture.setImagePath(url);
                    productPicture.setOldName(originImgName);
                    productPicture.setNewName(newImgName);
                    PictureGroupingModel pictureGroupingModel = new PictureGroupingModel();
                    pictureGroupingModel.setId(pictureGroupingId);
                    productPicture.setPictureGrouping(pictureGroupingModel);
                    pictureDao.insert(productPicture);
                    dataList.add(productPicture);
                }
            } catch (IOException e) {
                log.info(e.getMessage());
                return BaseResultUtil.fail(e.getMessage());
            }
        }
        return BaseResultUtil.success("上传成功!");
    }

    /**
     *  更新图片信息非空字段
     * @param param
     */
    @Transactional(rollbackFor=Exception.class)
    public void updateNotEmpty(Param param) {
        pictureDao.updateNotEmpty(param);
    }

    /**
     *  批量删除图片
     * @param idList
     */
    @Transactional(rollbackFor=Exception.class)
    public void deletePictureList(List<Long> idList){
        Param param = Param.builder().build().add("pictureIdList", idList);
        pictureDao.deletePictureList(param);
    }
}
