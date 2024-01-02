package com.myhd.jinhuileadnewsmodel.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: LoginDto
 * <br></br>
 * className: LoginDto
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.user.dtos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/12/4 20:04
 */
@Data
public class LoginDto {
    /**手机号*/
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    /**密码*/
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
