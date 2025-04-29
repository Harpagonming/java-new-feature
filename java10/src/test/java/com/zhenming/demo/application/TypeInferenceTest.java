package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

class TypeInferenceTest {
    private static final Logger LOG = LoggerFactory.getLogger(TypeInferenceTest.class);

    @Test
    void test() {
        var list = new ArrayList<String>();  // 编译器推断 list 是 ArrayList<String> 类型
        list.add("hello");
        list.add("world");
        list.add("java");
        for (var a : list) {
            LOG.info(a);
        }

        var integerNumber = 1;
        var longNumber = 1L;

        //数组静态初始化是不能省略的。
        //正常情况
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        //从左边推断后面，可以省略
        int[] arr2 = {1, 2, 3, 4, 5};
        //从右边推断前面，可以省略
        var arr3 = new int[]{1, 2, 3, 4, 5};
        //无法推断，不能省略
//        var arr4 = {1,2,3,4,5}

        Assertions.assertTrue(true);
    }
}
