package com.myhd.jinhuileadnewsarticle.controller.v1;

import com.myhd.jinhuileadnewsarticle.service.ApArticleService;
import com.myhd.jinhuileadnewscommon.constants.ArticleConstants;
import com.myhd.jinhuileadnewsmodel.article.dtos.ArticleHomeDto;
import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: ArticleHomeController 文章首页加载控制器
 * <br></br>
 * className: ArticleHomeController
 * <br></br>
 * packageName: com.myhd.jinhuileadnewsarticle.controller.v1
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/16 16:48
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Autowired
    private ApArticleService apArticleService;

    /**
     * Description: load 主页加载
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/16
     * */
    @PostMapping("/load")
    public ResponseResult<Object> load(@RequestBody ArticleHomeDto homeDto){
        return apArticleService.load(homeDto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    /**
     * Description: loadMore 加载更多
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/16
     * */
    @PostMapping("/loadmore")
    public ResponseResult<Object> loadMore(@RequestBody ArticleHomeDto homeDto){
        return apArticleService.load(homeDto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    /**
     * Description: loadMore 加载最新
     * @return com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult
     * @author jinhui-huang
     * @Date 2024/1/16
     * */
    @PostMapping("/loadnew")
    public ResponseResult<Object> loadNew(@RequestBody ArticleHomeDto homeDto){
        return apArticleService.load(homeDto, ArticleConstants.LOADTYPE_LOAD_NEW);
    }



}
