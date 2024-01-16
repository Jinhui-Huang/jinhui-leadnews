package com.myhd.jinhuileadnewsarticle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myhd.jinhuileadnewsarticle.mapper.ApArticleMapper;
import com.myhd.jinhuileadnewsarticle.service.ApArticleService;
import com.myhd.jinhuileadnewscommon.constants.ArticleConstants;
import com.myhd.jinhuileadnewsmodel.article.dtos.ArticleHomeDto;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description: ApArticleServiceImpl
 * <br></br>
 * className: ApArticleServiceImpl
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsarticle.service.impl
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 17:12
 */
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;
    /**
     * 最大分页数
     */
    private final static short MAX_PAGE_SIZE = 50;


    /**
     * Description: load 加载文章列表
     *
     * @param type 1 加载更多, 2 加载最新
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult<java.lang.Object>
     * @author jinhui-huang
     * @Date 2024/1/16
     */
    @Override
    public ResponseResult<Object> load(ArticleHomeDto homeDto, Short type) {
        /*1. 参数校验*/
        /*1.1 分页参数的校验*/
        Integer size = homeDto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        /*分页的值不超过50*/
        size = Math.min(size, MAX_PAGE_SIZE);
        homeDto.setSize(size);
        /*1.2 校验type参数*/
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        /*1.3 频道参数校验*/
        if (StringUtils.isBlank(homeDto.getTag())) {
            homeDto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        /*1.4 时间校验*/
        if (homeDto.getMaxBehotTime() == null) {
            homeDto.setMaxBehotTime(new Date());
        }
        if (homeDto.getMinBehotTime() == null) {
            homeDto.setMinBehotTime(new Date());
        }

        /*2 查询*/
        List<ApArticle> articleList = apArticleMapper.loadArticleList(homeDto, type);
        return ResponseResult.okResult(articleList);
    }
}
