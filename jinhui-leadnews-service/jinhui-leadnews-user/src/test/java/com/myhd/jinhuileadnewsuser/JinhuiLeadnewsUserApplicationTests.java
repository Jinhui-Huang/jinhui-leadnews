package com.myhd.jinhuileadnewsuser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class JinhuiLeadnewsUserApplicationTests {

    @Test
    void contextLoads() {
        String pwd = DigestUtils.md5DigestAsHex(("12345678jntm" + "abc").getBytes());
        System.out.println(pwd);
    }

}
