package com.zhenming.demo.application.interfaces.impl;

import com.zhenming.demo.application.interfaces.PrivateMethodInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrivateMethodInterfaceImplTest {
    @Test
    void test() {
        PrivateMethodInterface instance = new PrivateMethodInterfaceImpl();
        instance.logError("error");
        instance.logFatal("fatal");
        instance.logInfo("info");
        instance.logWarn("warn");
        Assertions.assertTrue(true);
    }
}