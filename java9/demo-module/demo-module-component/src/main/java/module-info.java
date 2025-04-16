module com.zhenming.demo.module.component {
    //引入com.zhenming.demo.module.core
    requires transitive com.zhenming.demo.module.core;
    //暴露component
    exports com.zhenming.demo.module.component;
}