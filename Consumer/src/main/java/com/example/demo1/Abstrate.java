package com.example.demo1;

/**
 * @Author baiyu
 * @Date 2023/3/21 11:15
 * @Description
 */
public abstract class Abstrate {

    String name;

    public Abstrate(String name) {
        this.name = name;
    }

    abstract void a();
    abstract void b();
}
