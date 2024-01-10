package com.myhd.jinhuileadnewstest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JinhuiLeadnewsTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        int i;
        int j;
        for (i = 0; i < 5; i++) {
            for (j = 5; j >= 0; j--) {
                if (i + j == 5) {
                    System.out.println(i + "+" + j + "=" + (i + j));
                }
            }
        }
    }

}
