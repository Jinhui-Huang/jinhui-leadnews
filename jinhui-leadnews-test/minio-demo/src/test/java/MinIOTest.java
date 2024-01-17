import com.myhd.file.service.FileStorageService;
import com.myhd.minio.MinIOApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Description: MinIOTest
 * <br></br>
 * className: MinIOTest
 * <br></br>
 * packageName: PACKAGE_NAME
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/17 16:38
 */
@SpringBootTest(classes = MinIOApplication.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;

    public static void main(String[] args) {
        try {
            /*1. 读取文件*/
            FileInputStream fis = new FileInputStream("/home/huian/IdeaProjects/jinhui-leadnews/jinhui-leadnews-test/freemarker-demo/src/main/resources/static/html/list.html");

            /*2. 创建minio客户端*/
            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio123").endpoint("http://127.0.0.1:9000").build();

            /*3. 上传文件*/
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("list.html") /*文件名*/
                    .contentType("text/html") /*文件类型*/
                    .bucket("leadnews") /*桶名词，与minio创建的名词一致*/
                    .stream(fis, fis.available(), -1) /*文件流*/
                    .build();
            minioClient.putObject(putObjectArgs);
            System.out.println("上传成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateImgFile() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/static/img/test.jpg");
            String filePath = fileStorageService.uploadImgFile("", "test.jpg", fis);
            System.out.println(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
