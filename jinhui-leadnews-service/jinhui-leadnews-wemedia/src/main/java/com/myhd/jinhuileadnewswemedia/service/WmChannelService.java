package com.myhd.jinhuileadnewswemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmChannel;

/**
 * Description: WmChannelService
 * <br></br>
 * className: WmChannelService
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:02
 */
public interface WmChannelService extends IService<WmChannel> {

    /**
     * Description: findAll 查询全部频道
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    ResponseResult findAll();
}
