package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TypeInferenceEnhancementTest {
    private static final Logger LOG = LoggerFactory.getLogger(TypeInferenceEnhancementTest.class);

    @Test
    void test() {
        /* 可以在Lambda表达式中使用类型推断，并且可以结合注解共同使用
         * Function<String, String> func = (var str) -> str.trim();
         *
         * BiFunction<String, String, String> biFunction = (@NotNull var a, @NotNull var b) -> a + b;
         */
        Assertions.assertTrue(true);
    }
}
