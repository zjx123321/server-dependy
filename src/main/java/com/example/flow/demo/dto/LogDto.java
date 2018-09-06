package com.example.flow.demo.dto;

import com.example.flow.demo.entity.node.Module;
import com.example.flow.demo.entity.node.Service;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
public class LogDto {

    private String module;

    private String service;

    private String traceId;

    private String spanId;

    private String pSpanId;

    private String costTime;

    private LogDto parent; //上级节点

    private LogDto child; //下级节点

    private int level; // 层级

    public Module buildModule() {
        Module m = new Module();
        m.setName(module);
        Set<Service> set = Sets.newHashSet();
        Service s = new Service();
        s.setName(service);
        s.setModuleName(module);
        s.setModule(m);
        set.add(s);
        m.setServices(set);
        return m;
    }

//    public Service buildService() {
//        Service s = new Service();
//        s.setName(service);
//        return s;
//    }

    public boolean vaild() {
        if(StringUtils.isEmpty(module) || StringUtils.isEmpty(service) || StringUtils.isEmpty(traceId)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.module + "|" + this.service + "|" + this.spanId + "|" + this.spanId;
    }

    public boolean equals(Object obj) {
        return (this.toString().equals(obj.toString()));
    }

    public int hashCode() {
        return this.toString().hashCode();
    };

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getpSpanId() {
        return pSpanId;
    }

    public void setpSpanId(String pSpanId) {
        this.pSpanId = pSpanId;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public LogDto getParent() {
        return parent;
    }

    public void setParent(LogDto parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LogDto getChild() {
        return child;
    }

    public void setChild(LogDto child) {
        this.child = child;
    }
}
