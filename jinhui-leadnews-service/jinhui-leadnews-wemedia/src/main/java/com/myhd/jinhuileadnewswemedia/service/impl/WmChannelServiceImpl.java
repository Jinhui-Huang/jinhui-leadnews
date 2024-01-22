package com.myhd.jinhuileadnewswemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmChannel;
import com.myhd.jinhuileadnewswemedia.mapper.WmChannelMapper;
import com.myhd.jinhuileadnewswemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: WmChannelServiceImpl
 * <br></br>
 * className: WmChannelServiceImpl
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:03
 */
@Service
@Transactional
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    /**
     * Description: findAll 查询全部频道
     *
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}
