package com.myhd.jinhuileadnewsuser.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.common.enums.AppHttpCodeEnum;
import com.myhd.jinhuileadnewsmodel.user.dtos.LoginDto;
import com.myhd.jinhuileadnewsmodel.user.pojos.ApUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.jinhuileadnewsuser.mapper.ApUserMapper;
import com.myhd.jinhuileadnewsuser.service.IApUserService;
import com.myhd.jinhuileadnewsutils.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * APP用户信息表 服务实现类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-12-04
 */
@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements IApUserService {

    /**
     * Description: APP端登录功能
     *
     * @param loginDto
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2023/12/4
     */
    @Override
    public ResponseResult login(LoginDto loginDto) {
        /*1. 正常登录, 用户名和密码*/
        if (StringUtils.isNoneBlank(loginDto.getPhone(), loginDto.getPassword())) {
            /*1.1. 根据手机号查询用户信息*/
            ApUser dbUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            if (dbUser == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户信息不存在");
            }
            /*1.2. 比对密码*/
            String salt = dbUser.getSalt();
            String password = loginDto.getPassword();
            /*加盐加密*/
            String pwd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!pwd.equals(dbUser.getPassword())) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            /*1.3. 返回数据 jwt*/
            String token = AppJwtUtil.getToken(dbUser.getId().longValue());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            dbUser.setSalt("");
            dbUser.setPassword("");
            map.put("user", dbUser);
            return ResponseResult.okResult(map);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token", AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
