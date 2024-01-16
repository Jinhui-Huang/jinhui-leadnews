package com.myhd.jinhuileadnewsmodel.article.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: ApArticleContent 文章实体类
 * <br></br>
 * className: ApArticleContent
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsmodel.article.pojos
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 15:26
 */

@Data
@TableName("ap_article_content")
public class ApArticleContent implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**文章id*/
    @TableField("article_id")
    private Long articleId;

    /**文章内容*/
    private String content;

}
