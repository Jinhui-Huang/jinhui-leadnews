package com.myhd.jinhuileadnewsarticle.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myhd.jinhuileadnewsmodel.article.dtos.ArticleHomeDto;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;

/**
 * Description: ApArticleService
 * <br></br>
 * className: ApArticleService
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsarticle.service
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 17:11
 */
public interface ApArticleService extends IService<ApArticle> {

    /**
     * Description: load 加载文章列表
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult<java.lang.Object>
     * @param type 1 加载更多, 2 加载最新
     * @author jinhui-huang
     * @Date 2024/1/16
     * */
    ResponseResult<Object> load(ArticleHomeDto homeDto, Short type);
}
