package com.zhenming.demo.module.component;

import com.zhenming.demo.module.core.model.ModuleTest;

public class Entrance {
    private Entrance() {
    }

    public static void enter(String name) {
        new ModuleTest(name).sayName();
    }

    public static void main(String[] args) {
        Entrance.enter("test name");
    }
}
