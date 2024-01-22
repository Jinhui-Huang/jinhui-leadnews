package com.myhd.jinhuileadnewswemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmMaterialDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: WmMaterialService
 * <br></br>
 * className: WmMaterialService
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 13:56
 */
public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * Description: uploadPicture 图片上传
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * Description: findList 素材列表查询
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     * */
    ResponseResult findList(WmMaterialDto wmMaterialDto);
}
