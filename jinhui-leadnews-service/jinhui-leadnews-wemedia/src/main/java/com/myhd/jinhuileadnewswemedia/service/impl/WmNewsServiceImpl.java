package com.myhd.jinhuileadnewswemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.jinhuileadnewsmodel.common.dtos.PageResponseResult;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.common.enums.AppHttpCodeEnum;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsPageReqDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmNews;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmUser;
import com.myhd.jinhuileadnewsutils.utils.thread.WmThreadLocalUtil;
import com.myhd.jinhuileadnewswemedia.mapper.WmNewsMapper;
import com.myhd.jinhuileadnewswemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
