package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StringTest {
    private static final Logger LOG = LoggerFactory.getLogger(StringTest.class);

    @Test
    void isBlank() {
        LOG.info("content = \"\", result: {}", "".isBlank());
        LOG.info("content = \" \", result: {}", " ".isBlank());
        LOG.info("content = \"\\n\", result: {}", "\n".isBlank());
        LOG.info("content = \"\\t\", result: {}", "\t".isBlank());
        LOG.info("content = \" test\\n\", result: {}", " test\n".isBlank());
        //该方法是对isEmpty()的补充，isEmpty()只能检查字符串长度是否为0，而isBlank()则回进一步检查了字符串的内容是否也只包含空白字符。
        Assertions.assertTrue(true);
    }

    @Test
    void lines() {
        "line1\nline2\r\nline3\rline4\n\rline5".lines().forEach(LOG::info);
        //该方法可以处理不同平台上的换行符，无论是\n，\r还是\r\n。
        Assertions.assertTrue(true);
    }

    @Test
    void strip() {
        //半角空格
        String testText1 = " test content ";
        LOG.info("content = [{}]", testText1);
        LOG.info("strip result = [{}]", testText1.strip());
        LOG.info("trim result = [{}]", testText1.trim());

        //全角空格
        String testText2 = "　test content　";
        LOG.info("content = [{}]", testText2);
        LOG.info("strip result = [{}]", testText2.strip());
        LOG.info("trim result = [{}]", testText2.trim());

        //全角空格
        String testText3 = "\t\r\ntest content\n\r\t";
        LOG.info("content = [{}]", testText3);
        LOG.info("strip result = [{}]", testText3.strip());
        LOG.info("trim result = [{}]", testText3.trim());

        //trim() 只移除ASCII字符集中定义的空白字符。
        //strip() 移除所有Unicode字符集中定义的空白字符。

        //strip() 还有两个方法：
        //stripLeading()：仅移除开头的空白字符。
        //stripTrailing()：仅移除末尾的空白字符。
        Assertions.assertTrue(true);
    }

    @Test
    void repeat() {
        LOG.info("{}", "repeat\t".repeat(5));
        Assertions.assertTrue(true);
    }
}
