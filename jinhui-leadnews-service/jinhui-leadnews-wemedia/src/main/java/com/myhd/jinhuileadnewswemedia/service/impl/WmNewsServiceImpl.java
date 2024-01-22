package com.myhd.jinhuileadnewswemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.jinhuileadnewscommon.constants.WeMediaConstants;
import com.myhd.jinhuileadnewscommon.exception.CustomException;
import com.myhd.jinhuileadnewsmodel.common.dtos.PageResponseResult;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.common.enums.AppHttpCodeEnum;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsDto;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsPageReqDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmMaterial;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmNews;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmNewsMaterial;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmUser;
import com.myhd.jinhuileadnewsutils.utils.thread.WmThreadLocalUtil;
import com.myhd.jinhuileadnewswemedia.mapper.WmMaterialMapper;
import com.myhd.jinhuileadnewswemedia.mapper.WmNewsMapper;
import com.myhd.jinhuileadnewswemedia.mapper.WmNewsMaterialMapper;
import com.myhd.jinhuileadnewswemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: WmNewsServiceImpl
 * <br></br>
 * className: WmNewsServiceImpl
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:14
 */
@Service
@Transactional
@Slf4j
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Autowired
    private WmMaterialMapper wmMaterialMapper;

    /**
     * Description: findAll 分页查询文章
     *
     * @param wmNewsPageReqDto
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    @Override
    public ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto) {
        /*1. 检查参数*/
        if (wmNewsPageReqDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        /*1.1 分页参数检查*/
        wmNewsPageReqDto.checkParam();
        /*1.2 获取当前登录人信息*/
        WmUser user = WmThreadLocalUtil.getUser();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        /*2. 分页条件查询*/
        IPage<WmNews> page = new Page<>(wmNewsPageReqDto.getPage(), wmNewsPageReqDto.getSize());
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*2.1 状态精确查询*/
        if (wmNewsPageReqDto.getStatus() != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, wmNewsPageReqDto.getStatus());
        }
        /*2.2 频道精确查询*/
        if (wmNewsPageReqDto.getChannelId() != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, wmNewsPageReqDto.getChannelId());
        }
        /*2.3 时间范围查询*/
        if (wmNewsPageReqDto.getBeginPubDate() != null && wmNewsPageReqDto.getEndPubDate() != null) {
            lambdaQueryWrapper.between(WmNews::getPublishTime, wmNewsPageReqDto.getBeginPubDate(), wmNewsPageReqDto.getEndPubDate());
        }
        /*2.4 关键字模糊查询*/
        if (StringUtils.isNotBlank(wmNewsPageReqDto.getKeyword())) {
            lambdaQueryWrapper.like(WmNews::getTitle, wmNewsPageReqDto.getKeyword());
        }

        /*2.5 查询当前登录用户的文章*/
        lambdaQueryWrapper.eq(WmNews::getUserId, user.getId());
        /*2.6 发布时间倒序查询*/
        lambdaQueryWrapper.orderByDesc(WmNews::getCreatedTime);

        page = page(page, lambdaQueryWrapper);
        /*3. 返回结果*/
        ResponseResult responseResult = new PageResponseResult(wmNewsPageReqDto.getPage(), wmNewsPageReqDto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());

        return responseResult;
    }

    /**
     * Description: submitNews 发布文章或者保存为草稿
     *
     * @param wmNewsDto
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    @Override
    public ResponseResult submitNews(WmNewsDto wmNewsDto) {
        /*1. 条件判断*/
        if (wmNewsDto == null || wmNewsDto.getContent() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        /*2. 保存或修改文章*/
        WmNews wmNews = new WmNews();
        /*2.1 属性拷贝 属性名词和类型相同才能拷贝*/
        BeanUtils.copyProperties(wmNewsDto, wmNews);

        /*2.2 封面图形 list --> string*/
        if (wmNewsDto.getImages() != null && wmNewsDto.getImages().size() > 0) {
            String imageStr = StringUtils.join(wmNewsDto.getImages(), ",");
            wmNews.setImages(imageStr);
        }

        /*2.3 如果当前封面类型为自动 -1*/
        if (wmNewsDto.getType().equals(WeMediaConstants.WM_NEWS_TYPE_AUTO)) {
            wmNews.setType(null);
        }

        /*新增或者修改文章*/
        saveOrUpdateWmNews(wmNews);

        /*3. 判断是否为草稿， 如果为草稿结束当前方法*/
        if (wmNewsDto.getStatus().equals(WmNews.Status.NORMAL.getCode())) {
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }

        /*4. 不是草稿， 保存文章内容图片与素材的关系*/
        /*4.1 获取到文章内容中的图片信息*/
        List<String> materials = ectractUrlInfo(wmNewsDto.getContent());
        saveRelativeInfoForContent(materials, wmNews.getId());
        /*5. 如果当前布局是自动， 需要匹配文章内容图片为封面图片*/
        saveRelativeInfoForCover(wmNewsDto, wmNews, materials);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    private void saveRelativeInfoForContent(List<String> materials, Integer id) {
        saveRelativeInfo(materials, id, WeMediaConstants.WM_CONTENT_REFERENCE);
    }

    /**
     * Description: ectractUrlInfo 提取文章内容中的图片内容
     * @return java.util.List<java.lang.String>
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    private List<String> ectractUrlInfo(String content) {
        List<String> materials = new ArrayList<>();

        List<Map> maps = JSON.parseArray(content, Map.class);
        for (Map map : maps) {
            if (map.get("type").equals("image")) {
                String imgUrl = (String) map.get("value");
                materials.add(imgUrl);
            }
        }
        return materials;
    }

    /**
     * Description: saveOrUpdateWmNews 保存或者修改文章
     * @return void
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        /*补全属性*/
        wmNews.setUserId(WmThreadLocalUtil.getUser().getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable((short) 1); /*默认上架*/
        if (wmNews.getId() == null) {
            /*保存*/
            save(wmNews);
        } else {
            /*修改*/
            /*删除文章图片与素材的关系*/
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId, wmNews.getId()));
            updateById(wmNews);
        }
    }

    /**
     * Description: saveRelativeInfoForCover<br>
     * 第一个功能：如果当前封面类型为自动，则设置封面类型的数据<br>
     * 匹配规则：<br>
     * 1，如果内容图片大于等于1，小于3  单图  type 1<br>
     * 2，如果内容图片大于等于3  多图  type 3<br>
     * 3，如果内容没有图片，无图  type 0<br>
     * 第二个功能：保存封面图片与素材的关系
     *
     * @return void
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    private void saveRelativeInfoForCover(WmNewsDto wmNewsDto, WmNews wmNews, List<String> materials) {
        List<String> images = wmNewsDto.getImages();
        /*如果当前封面类型为自动， 则设置封面类型的数据 --> materials*/
        if (wmNewsDto.getType().equals(WeMediaConstants.WM_NEWS_TYPE_AUTO)) {
            /*多图*/
            if (materials.size() >= 3) {
                wmNews.setType(WeMediaConstants.WM_NEWS_MANY_IMAGE);
                images = materials.stream().limit(3).collect(Collectors.toList());
            } else if (materials.size() >= 1) {
                /*单图*/
                wmNews.setType(WeMediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = materials.stream().limit(1).collect(Collectors.toList());
            } else {
                /*无图*/
                wmNews.setType(WeMediaConstants.WM_NEWS_NONE_IMAGE);
            }
            /*修改文章*/
            if (images != null && images.size() > 0) {
                wmNews.setImages(StringUtils.join(images, ","));
            }
            updateById(wmNews);
        }
        /*不是自动类型则存储images*/
        if (images != null && images.size() > 0) {
            /*保存封面图片与素材的关系到数据库中*/
            saveRelativeInfo(images, wmNews.getId(), WeMediaConstants.WM_COVER_REFERENCE);
        }
    }

    private void saveRelativeInfo(List<String> images, Integer newsId, Short wmCoverReference) {
        if (images != null && !images.isEmpty()){
            /*通过图片的url查询素材的id*/
            List<WmMaterial> wmMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery().in(WmMaterial::getUrl, images));

            /*判断素材是否有效*/
            if (wmMaterials == null || wmMaterials.size() == 0) {
                /*抛出异常， 提示素材调用失败， 回滚数据库*/
                throw new CustomException(AppHttpCodeEnum.MATERIASL_REFERENCE_FAIL);
            }

            /*获取ids*/
            List<Integer> idList = wmMaterials.stream().map(WmMaterial::getId).collect(Collectors.toList());

            /*批量保存*/
            wmNewsMaterialMapper.saveRelations(idList, newsId, wmCoverReference);

        }

    }
}
