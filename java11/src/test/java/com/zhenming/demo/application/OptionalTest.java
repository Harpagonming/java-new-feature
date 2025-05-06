package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class OptionalTest {
    private static final Logger LOG = LoggerFactory.getLogger(OptionalTest.class);

    @Test
    void isEmpty() {
        Optional<String> optional = Optional.empty();
        LOG.info("isPresent result = {}", optional.isPresent());
        LOG.info("isEmpty result = {}", optional.isEmpty());
        //isEmpty方法是isPresent方法的对立
        Assertions.assertTrue(true);
    }
}
