package net.zhqu.website.bg.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.zhqu.framework.entity.BaseResult;
import net.zhqu.framework.entity.Page;
import net.zhqu.framework.entity.Param;
import net.zhqu.framework.exception.CURDException;
import net.zhqu.framework.utils.BaseResultUtil;
import net.zhqu.framework.utils.ParamUtil;
import net.zhqu.website.bg.form.PictureFrom;
import net.zhqu.website.bg.model.PictureGroupingModel;
import net.zhqu.website.bg.model.PictureModel;
import net.zhqu.website.bg.service.PictureGroupingService;
import net.zhqu.website.bg.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By xin cai On 2018/5/24
 * 图片管理Controller
 *
 * @author xin cai (1428774847@qq.com)
 */
@Slf4j
@Controller
@RequestMapping("/bg/picture")
public class BGPictureController {
    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureGroupingService pictureGroupingService;

    @Autowired
    private ParamUtil paramUtil;

    @GetMapping("/v/index.html")
    public String productPictureUpload(Model model) {
        List<PictureGroupingModel> pictureGroupingList = pictureGroupingService.findAll();
        model.addAttribute("pictureGroupingList", pictureGroupingList);
        return "bg/picture_index";
    }

    @GetMapping("/r/all.json")
    @ResponseBody
    public BaseResult findAllPage(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit, Long pictureGroupingId) {
        Param param = paramUtil.getPageParam(page, limit);
        param.add("orderByCreateTimeDesc", true);

        if (pictureGroupingId != null && pictureGroupingId.intValue() != 0) {
            param.add("pictureGroupingId", pictureGroupingId);
        }

        try {
            List<PictureModel> list = pictureService.findAllPage(param);
            list.forEach(item -> {
                item.setCndImagePath("http://rescdn.www.smartconns.com/" + item.getImagePath());
            });
            return BaseResultUtil.success(param.get("page", Page.class), list);
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @GetMapping("/r/pictureOrGrouping")
    @ResponseBody
    public Map<String, Object> pageQuery(@RequestParam("pictureGroupingId") String pictureGroupingId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<PictureGroupingModel> pictureGroupingList = pictureGroupingService.findAll();
        result.put("pictureGroupingList", pictureGroupingList);
        Param param = paramUtil.getPageParam(page, limit);
        if (StringUtils.isEmpty(pictureGroupingId) && !CollectionUtils.isEmpty(pictureGroupingList)) {
            pictureGroupingId = String.valueOf(pictureGroupingList.get(0).getId());
        }
        param.add("pictureGroupingId", pictureGroupingId).add("orderByCreateTimeDesc", true);
        try {
            List<PictureModel> list = pictureService.findAllPage(param);
            list.forEach(item -> {
                item.setCndImagePath("http://rescdn.www.smartconns.com/" + item.getImagePath());
            });
            result.put("total", param.get("page", Page.class).getTotalResult());
            result.put("pictureList", list);
        } catch (CURDException e) {

        }
        return result;
    }

    @PostMapping(value = "/c/upload/{pictureGroupingId}")
    @ResponseBody
    public BaseResult uploadPicture(@RequestParam("file") MultipartFile[] multipartFile, HttpServletRequest request, @PathVariable Long pictureGroupingId) {
        return pictureService.upLoadPictureList(multipartFile, request, pictureGroupingId);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("code", 0);//0表示成功，1失败
        map.put("msg", "上传成功");//提示消息
        map.put("data", map2);
        map2.put("src", "");//图片url
        map2.put("title", "");//图片名称，这个会显示在输入框里
        String result = new JSONObject(map).toString();
        return result;
    }


    @DeleteMapping(value = "/d/{id}")
    @ResponseBody
    public BaseResult deletePicture(@PathVariable Long id) {
        Param param = Param.builder().build().add("id", id);
        try {
            pictureService.delete(param);
            return BaseResultUtil.success("删除成功");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }

    @DeleteMapping(value = "/pictures/{idList}")
    @ResponseBody
    public BaseResult deletePictureList(@PathVariable List<String> idList) {
        List<Long> pictureIdList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            String id = idList.get(i);
            pictureIdList.add(Long.parseLong(id));
        }
        pictureService.deletePictureList(pictureIdList);
        return BaseResultUtil.success("删除成功");
    }

    @PutMapping(value = "/u/multiple")
    @ResponseBody
    public BaseResult updatePicture(@RequestBody PictureFrom pictureFrom) {
        Param param = Param.builder().build().add("pictureIdList", pictureFrom.getPictureIdList())
                .add("pictureGroupingId", pictureFrom.getGropId());
        pictureService.updateNotEmpty(param);
        return BaseResultUtil.success("更新成功");

    }

    @PutMapping("/u/{id}")
    @ResponseBody
    public BaseResult updatePictureName(@PathVariable Long id, @RequestBody PictureModel pictureModel) {
        try {
            pictureModel.setId(id);
            pictureService.update(pictureModel);
            return BaseResultUtil.success("更新成功");
        } catch (CURDException e) {
            return BaseResultUtil.fail(e.getMessage());
        }
    }
}
