package com.zhenming.demo.module.application;

import com.zhenming.demo.module.component.Entrance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModuleApplicationTest {
    @Test
    public void test() {
        Entrance.enter("test name");
        //当component模块中module-info文件requires加入transitive关键字，即可将引入的包向外传递，否则，无法使用未引入的包的内容
//        new ModuleTest("name").sayName();
        Assertions.assertTrue(true);
    }
}
