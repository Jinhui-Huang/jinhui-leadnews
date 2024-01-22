import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myhd.file.service.FileStorageService;
import com.myhd.jinhuileadnewsarticle.JinhuiLeadnewsArticleApplication;
import com.myhd.jinhuileadnewsarticle.mapper.ApArticleContentMapper;
import com.myhd.jinhuileadnewsarticle.mapper.ApArticleMapper;
import com.myhd.jinhuileadnewsarticle.service.ApArticleService;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticle;
import com.myhd.jinhuileadnewsmodel.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiConsumer;

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

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Value("change true article id")
    private String articleId;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleService apArticleService;

    private List<String> imgUrls = new ArrayList<>();

    /**
     * Description: upLoadImgTest 上传图片测试
     *
     * @return void
     * @author jinhui-huang
     * @Date 2024/1/21
     */
    @Test
    public void upLoadImgTest() {
        /*1. 获取文章id*/
        String filePath = "src/main/resources/static/images";
        upImage(filePath, "");
        System.out.println(imgUrls);

        List<ApArticle> apArticles = apArticleMapper.selectList(Wrappers.<ApArticle>lambdaQuery().select(ApArticle::getId, ApArticle::getLayout));
        for (ApArticle apArticle : apArticles) {
            Short layout = apArticle.getLayout();
            StringJoiner images = new StringJoiner(",");
            for (int i = 0; i < layout.intValue(); i++) {
                String imgUrl = imgUrls.get(0);
                images.add(imgUrl);
                imgUrls.remove(imgUrl);
            }
            boolean update = apArticleService.update(Wrappers.<ApArticle>lambdaUpdate()
                    .eq(ApArticle::getId, apArticle.getId())
                    .set(ApArticle::getImages, images.toString()));
            System.out.println(update);
        }

    }

    private void upImage(String filePath, String nextFile) {

        try {
            File file = new File(filePath, nextFile);
            File[] files = file.listFiles();
            if (files == null) {
                System.out.println("文件路径不存在");
                return;
            }
            for (File file1 : files) {
                if (file1.isFile()) {
                    InputStream is = new FileInputStream(file1);
                    String fileName = RandomStringUtils.randomAlphanumeric(30) + ".jpg";
                    String imgUrl = fileStorageService.uploadImgFile("", fileName, is);
                    imgUrls.add(imgUrl);
                } else {
                    upImage(file.getPath(), file1.getName());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createStaticUrlTest() throws Exception {
        /*已知文章id*/
        /*1. 获取文章内容*/
        List<ApArticle> apArticles = apArticleMapper.selectList(Wrappers.<ApArticle>lambdaQuery().select(ApArticle::getId));
        for (ApArticle apArticle : apArticles) {
            ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, apArticle.getId()));
            if (apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())) {
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
}
