package com.zhenming.demo.module.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntranceTest {

    @Test
    void testEnter() {
        Entrance.enter("test name");
        Assertions.assertTrue(true);
    }
}