package com.myhd.jinhuileadnewsmodel.article.dtos;

import lombok.Data;

import java.util.Date;

/**
 * Description: ArticleHomeDto
 * <br></br>
 * className: ArticleHomeDto
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.article.dtos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 16:50
 */
@Data
public class ArticleHomeDto {
    /**最大时间*/
    private Date maxBehotTime;

    /**最小时间*/
    private Date minBehotTime;

    /**分页size*/
    private Integer size;

    /**频道tag*/
    private String tag;

}
