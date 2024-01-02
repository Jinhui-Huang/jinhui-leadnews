package com.myhd.jinhuileadnewsuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.user.dtos.LoginDto;
import com.myhd.jinhuileadnewsmodel.user.pojos.ApUser;

/**
 * <p>
 * APP用户信息表 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-12-04
 */
public interface IApUserService extends IService<ApUser> {
    /**
     * Description: APP端登录功能
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2023/12/4
     * */
    ResponseResult<Object> login(LoginDto loginDto);
}
