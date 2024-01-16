package com.myhd.jinhuileadnewsarticle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myhd.jinhuileadnewsmodel.article.dtos.ArticleHomeDto;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: ApArticleMapper
 * <br></br>
 * className: ApArticleMapper
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsarticle.mapper
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 16:57
 */
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    /**
     * Description: loadArticleList 加载文章列表
     * @return java.util.List<com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle>
     * @author jinhui-huang
     * @param type 1 加载更多， 2 加载最新
     * @Date 2024/1/16
     * */
    List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}
