package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

class ImmutableCollectionTest {
    private static final Logger LOG = LoggerFactory.getLogger(ImmutableCollectionTest.class);

    @Test
    void test() {
        //利用copyOf可以创建一个不可变副本List<@NotNull String>，但是源List不可以存在空元素
        var list1 = Stream.of("test1", "test2", "test3").toList();
        LOG.info("list1 address = {}", System.identityHashCode(list1));

        var list2 = List.copyOf(list1);
        LOG.info("list2 address = {}", System.identityHashCode(list2));

        var list3 = List.of("test1", "test2", "test3");
        LOG.info("list3 address = {}", System.identityHashCode(list3));

        var list4 = List.copyOf(list3);
        LOG.info("list4 address = {}", System.identityHashCode(list4));

        Assertions.assertTrue(true);
    }
}
