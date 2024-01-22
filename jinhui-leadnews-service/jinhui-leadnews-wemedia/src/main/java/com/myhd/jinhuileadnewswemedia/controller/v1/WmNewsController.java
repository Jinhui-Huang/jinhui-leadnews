package com.myhd.jinhuileadnewswemedia.controller.v1;

import com.myhd.jinhuileadnewsmodel.common.dtos.ResponseResult;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsDto;
import com.myhd.jinhuileadnewsmodel.wemedia.dtos.WmNewsPageReqDto;
import com.myhd.jinhuileadnewswemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: WmNewsController
 * <br></br>
 * className: WmNewsController
 * <br></br>
 * packageName: com.myhd.jinhuileadnewswemedia.controller.v1
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/22 15:11
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto wmNewsPageReqDto) {
        return wmNewsService.findAll(wmNewsPageReqDto);
    }

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto wmNewsDto){
        return wmNewsService.submitNews(wmNewsDto);
    }
}
