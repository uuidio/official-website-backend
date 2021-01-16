package net.zhqu.website.bg.service;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.dao.ZQDao;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.service.AbstractZQServiceImp;
import net.zhqu.website.bg.dao.ProductCategoryDao;
import net.zhqu.website.bg.dao.ProductDao;
import net.zhqu.website.bg.model.PictureModel;
import net.zhqu.website.bg.model.ProductContentModel;
import net.zhqu.website.bg.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yong y__ong@outlook.com
 * @date 2019-08-21 10:25
 */
@Slf4j
@Service
public class ProductService extends AbstractZQServiceImp<ProductModel> {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PictureService pictureService;

    @Override
    public ZQDao<ProductModel> getDao() {
        return productDao;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ProductModel model) throws CURDException {
        List<PictureModel> images = model.getImages();
        if (StringUtils.isEmpty(model.getMainImage())) {
            if (!CollectionUtils.isEmpty(images)) {
                String imagePath = images.get(0).getImagePath();
                if (images.get(0).getId() != null && StringUtils.isEmpty(imagePath)) {
                    PictureModel pictureModel = pictureService.findById(images.get(0).getId());
                    if (pictureModel != null) {
                        imagePath = pictureModel.getImagePath();
                    }
                }
                model.setMainImage(imagePath);
            }
        }
        if (model.getCategory() == null || model.getCategory().getId() == null) {
            throw new CURDException("类目不为空");
        }

        super.insert(model);
        deleteAllImages(model.getId());
        addImage(images,model.getId());

        deleteAllContents(model.getId());
        addContents(model.getContents(), model.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ProductModel model) throws CURDException {
        List<PictureModel> images = model.getImages();
        if (StringUtils.isEmpty(model.getMainImage())) {
            if (!CollectionUtils.isEmpty(images)) {
                String imagePath = images.get(0).getImagePath();
                if (images.get(0).getId() != null && StringUtils.isEmpty(imagePath)) {
                    PictureModel pictureModel = pictureService.findById(images.get(0).getId());
                    if (pictureModel != null) {
                        imagePath = pictureModel.getImagePath();
                    }
                }
                model.setMainImage(imagePath);
            }
        }
        deleteAllImages(model.getId());
        addImage(images,model.getId());

        deleteAllContents(model.getId());
        addContents(model.getContents(), model.getId());

        return super.update(model);
    }
    @Transactional(rollbackFor = Exception.class)
    public void addImage(List<PictureModel> images, Long productId) throws CURDException {
        if (CollectionUtils.isEmpty(images) || productId == null) {
            return;
        }
        for (PictureModel pictureModel : images) {
            if (pictureModel.getId() == null) {
                throw new CURDException("参数不对");
            }
        }
        productDao.addImages(paramUtil.getBlackParam().add("productId", productId).add("images", images));
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllImages(Long productId) throws CURDException {
        if (productId == null) {
            return;
        }
        productDao.deleteAllImages(paramUtil.getBlackParam().add("productId", productId));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addContents(List<ProductContentModel> contentModels, Long productId) throws CURDException {
        if (CollectionUtils.isEmpty(contentModels) || productId == null) {
            return;
        }
        productDao.addContents(paramUtil.getBlackParam().add("productId", productId).add("contents", contentModels));
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllContents(Long productId) throws CURDException {
        if (productId == null) {
            return;
        }
        productDao.deleteAllContents(paramUtil.getBlackParam().add("productId", productId));
    }

    public List<ProductModel> findAll(Param param) throws CURDException {
        return productDao.findAll(param);
    }
}
