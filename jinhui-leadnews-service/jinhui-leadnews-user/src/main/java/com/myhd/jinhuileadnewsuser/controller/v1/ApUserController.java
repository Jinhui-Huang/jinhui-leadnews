package com.myhd.jinhuileadnewsuser.controller.v1;


import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.user.dtos.LoginDto;
import com.myhd.jinhuileadnewsuser.service.IApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * APP用户信息表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-12-04
 */
@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登录", tags = "app端用户登录")
public class ApUserController {
    @Autowired
    private IApUserService apUserService;

    @PostMapping("/login_auth")
    @ApiOperation("用户登录")
    public ResponseResult<Object> login(@RequestBody LoginDto loginDto){
        return apUserService.login(loginDto);
    }
}
