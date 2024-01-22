package com.myhd.jinhuileadnewsmodel.wemedia.dtos;

import com.myhd.jinhuileadnewsmodel.common.dtos.PageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Description: WmNewsPageReqDto 图文内容展示列表
 * <br></br>
 * className: WmNewsPageReqDto
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.wemedia.dtos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WmNewsPageReqDto extends PageRequestDto {

    /**
     * 状态
     */
    private Short status;
    /**
     * 开始时间
     */
    private Date beginPubDate;
    /**
     * 结束时间
     */
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    private Integer channelId;
    /**
     * 关键字
     */
    private String keyword;
}