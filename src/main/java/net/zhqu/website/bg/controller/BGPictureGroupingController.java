package net.zhqu.website.bg.controller;

import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.model.PictureGroupingModel;
import net.zhqu.website.bg.service.PictureGroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By xin cai On 2018/5/24
 * t图片分组Controller
 *
 * @author xin cai (1428774847@qq.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/pictureGrouping")
public class BGPictureGroupingController {
    @Autowired
    private PictureGroupingService pictureGroupingService;

    @Autowired
    private ParamUtil paramUtil;

    @PostMapping(value = "/c/")
    @ResponseBody
    public BaseResult save(PictureGroupingModel pictureGroupingModel) {
        try {
            Param param = Param.builder().build().add("pictureOwnership", pictureGroupingModel.getPictureOwnership());
            PictureGroupingModel pictureGrouping = pictureGroupingService.findOne(param);
            if (pictureGrouping != null) {
                throw new CURDException("分组名已存在,请修改为不同分组名!");
            }
            pictureGroupingService.insert(pictureGroupingModel);
            return BaseResultUtil.success("OK");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }

    }

    @GetMapping("/r/all.json")
    @ResponseBody
    public BaseResult findAll() {
        List<PictureGroupingModel> pictureGroupingModelList = pictureGroupingService.findAll();
        return BaseResultUtil.success(pictureGroupingModelList);
    }

    @PutMapping("/u/{id}")
    @ResponseBody
    public BaseResult updatePictureGroupingName(@PathVariable Long id, @RequestBody PictureGroupingModel pictureGroupingModel) {
        try {
            pictureGroupingModel.setId(id);
            pictureGroupingService.update(pictureGroupingModel);
            return BaseResultUtil.success("更新成功!");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

}
