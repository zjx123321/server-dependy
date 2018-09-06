package com.example.flow.demo.service;

import com.example.flow.demo.dto.BaseDto;

import java.util.List;

/**
 * Created by zhengjiexiang on 2018/9/5
 * 日志采集
 */
public interface LogCollectService<T> {

    void collect();

    void collect(String traceId);

    void parse(List<T> dtos);

}
