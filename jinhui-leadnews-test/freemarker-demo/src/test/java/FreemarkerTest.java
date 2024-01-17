import com.myhd.freemarker.FreemarkerDemoApplication;
import com.myhd.freemarker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Description: FreemarkerTest
 * <br></br>
 * className: FreemarkerTest
 * <br></br>
 * packageName: PACKAGE_NAME
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2024/1/17 15:09
 */
@SpringBootTest(classes = FreemarkerDemoApplication.class)
public class FreemarkerTest {
    @Resource
    private Configuration configuration;

    @Test
    void test() throws IOException, TemplateException {
        /*获取freemarker的模板对象*/
        Template template = configuration.getTemplate("02-list.ftl");
        Map<String, Object> params = getData();
        /*合成*/
        /*第一个参数 数据模型*/
        /*第二个参数 输出流*/
        template.process(params, new FileWriter("src/main/resources/static/html/list.html"));
    }

    private Map<String, Object> getData() {
        Map<String, Object> map = new HashMap<>();

        //小强对象模型数据
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthDay(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);

        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //向map中存放List集合数据
        map.put("stus", stus);


        //创建Map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向map中存放Map数据
        map.put("stuMap", stuMap);

        //返回Map
        return map;
    }
}
