package com.myhd.jinhuileadnewsmodel.wemedia.dtos;

import com.myhd.jinhuileadnewsmodel.common.dtos.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: WmMaterialDto 查询自媒体素材列表分页参数
 * <br></br>
 * className: WmMaterialDto
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.wemedia.dtos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 14:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WmMaterialDto extends PageRequestDto {
    /**1 收藏 0 未收藏*/
    private Short isCollection;
}
