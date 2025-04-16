package com.zhenming.demo.module.core.model;

public class ModuleTest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayName() {
        System.out.println(this.name);
    }

    public ModuleTest(String name) {
        this.name = name;
    }
}
