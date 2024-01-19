package com.myhd.jinhuileadnewswemedia.controller.v1;

import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmLoginDto;
import com.myhd.jinhuileadnewswemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody WmLoginDto dto){
        return wmUserService.login(dto);
    }
}
