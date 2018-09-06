package com.example.flow.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
@ConfigurationProperties(prefix="business")
public class BusinessConfig {

    private String modules;

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }
}
