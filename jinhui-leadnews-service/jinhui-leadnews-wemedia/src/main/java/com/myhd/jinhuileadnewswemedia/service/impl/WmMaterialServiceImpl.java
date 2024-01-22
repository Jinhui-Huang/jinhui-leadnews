package com.myhd.jinhuileadnewswemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.file.service.FileStorageService;
import com.myhd.jinhuileadnewsmodel.common.dtos.PageResponseResult;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.common.enums.AppHttpCodeEnum;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmMaterialDto;
import com.myhd.jinhuileadnewsmodel.wemedia.pojos.WmMaterial;
import com.myhd.jinhuileadnewsutils.utils.thread.WmThreadLocalUtil;
import com.myhd.jinhuileadnewswemedia.mapper.WmMaterialMapper;
import com.myhd.jinhuileadnewswemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * Description: WmMaterialServiceImpl
 * <br></br>
 * className: WmMaterialServiceImpl
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 13:57
 */
@Service
@Transactional
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Description: uploadPicture 图片上传
     *
     * @param multipartFile
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        /*1. 检查参数*/
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        String fileId = null;
        try {
            /*2. 上传图片到minio中*/
            String fileName = UUID.randomUUID().toString().replace("-", "");
            /*原图片名称*/
            String originalFilename = multipartFile.getOriginalFilename();
            /*图片类型*/
            String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileId = fileStorageService.uploadImgFile("", fileName + postfix, multipartFile.getInputStream());
            log.info("图片上传至minio， fileId: {}", fileId);

            /*3. 保存图片url到数据库中去*/
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WmMaterialServiceImpl-图片上传失败");
        }
        /*保存到数据库中*/
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(fileId);
        wmMaterial.setIsCollection((short) 0);
        wmMaterial.setType((short) 0);
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);
        return ResponseResult.okResult(wmMaterial);
    }

    /**
     * Description: findList 素材列表查询
     *
     * @param wmMaterialDto
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/22
     */
    @Override
    public ResponseResult findList(WmMaterialDto wmMaterialDto) {
        /*1. 检查参数*/
        wmMaterialDto.checkParam();

        /*2. 分页查询*/
        IPage<WmMaterial> page = new Page<>(wmMaterialDto.getPage(), wmMaterialDto.getSize());
        LambdaQueryWrapper<WmMaterial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*2.1 是否收藏*/
        if (wmMaterialDto.getIsCollection() != null && wmMaterialDto.getIsCollection() == 1) {
            lambdaQueryWrapper.eq(WmMaterial::getIsCollection, wmMaterialDto.getIsCollection());
        }
        /*2.2 按照用户查询*/
        lambdaQueryWrapper.eq(WmMaterial::getUserId, WmThreadLocalUtil.getUser().getId());

        /*2.3 按照时间倒序*/
        lambdaQueryWrapper.orderByDesc(WmMaterial::getCreatedTime);

        page = page(page, lambdaQueryWrapper);

        /*3. 返回结果*/
        ResponseResult responseResult = new PageResponseResult(wmMaterialDto.getPage(), wmMaterialDto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());

        return responseResult;
    }
}
