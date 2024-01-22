package com.myhd.jinhuileadnewswemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsDto;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsPageReqDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmNews;

/**
 * Description: WmNewsService
 * <br></br>
 * className: WmNewsService
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:13
 */
public interface WmNewsService extends IService<WmNews> {

    /**
     * Description: findAll 分页查询文章
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * Description: submitNews 发布文章或者保存为草稿
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    ResponseResult submitNews(WmNewsDto wmNewsDto);
}
