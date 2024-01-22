package com.myhd.jinhuileadnewsmodel.wemedia.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Description: WmNewsDto
 * <br></br>
 * className: WmNewsDto
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.wemedia.dtos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:57
 */
@Data
public class WmNewsDto {

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 频道id
     */
    private Integer channelId;
    /**
     * 标签
     */
    private String labels;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章封面类型  0 无图 1 单图 3 多图 -1 自动
     */
    private Short type;
    /**
     * 提交时间
     */
    private Date submitedTime;
    /**
     * 状态 提交为1  草稿为0
     */
    private Short status;

    /**
     * 封面图片列表 多张图以逗号隔开
     */
    private List<String> images;
}
