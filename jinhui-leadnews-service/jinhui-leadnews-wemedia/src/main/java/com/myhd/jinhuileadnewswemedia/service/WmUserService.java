package com.myhd.jinhuileadnewswemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmLoginDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}