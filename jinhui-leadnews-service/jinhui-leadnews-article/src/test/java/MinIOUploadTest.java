import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myhd.file.service.FileStorageService;
import com.myhd.jinhuileadnewsarticle.JinhuiLeadnewsArticleApplication;
import com.myhd.jinhuileadnewsarticle.mapper.ApArticleContentMapper;
import com.myhd.jinhuileadnewsarticle.service.ApArticleService;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: MinIOUpload
 * <br></br>
 * className: MinIOUpload
 * <br></br>
 * packageName: PACKAGE_NAME
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/18 19:30
 */
@SpringBootTest(classes = JinhuiLeadnewsArticleApplication.class)
@RunWith(SpringRunner.class)
public class MinIOUploadTest {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Value("change true article id")
    private String articleId;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;

    @Test
    public void createStaticUrlTest() throws Exception{
        /*已知文章id*/
        /*1. 获取文章内容*/
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, articleId));
        if (apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())){
            /*2. 文章内容通过freemarker生成html文件*/
            Template template = configuration.getTemplate("article.ftl");
            /*数据模型*/
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("content", JSONArray.parseArray(apArticleContent.getContent()));
            StringWriter out = new StringWriter();
            /*合成*/
            template.process(dataModel, out);

            /*3. 把html文件上传到minio中*/
            InputStream inputStream = new ByteArrayInputStream(out.toString().getBytes());
            String htmlUrl = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", inputStream);

            /*4. 修改ap_article表, 保存static_url字段*/
            apArticleService.update(Wrappers.<ApArticle>lambdaUpdate().eq(ApArticle::getId, apArticleContent.getArticleId()).set(ApArticle::getStaticUrl, htmlUrl));

        }





    }
}
