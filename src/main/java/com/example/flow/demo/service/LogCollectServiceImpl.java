package com.example.flow.demo.service;

import com.example.flow.demo.dto.KibanaLogDto;
import com.example.flow.demo.dto.LogDto;
import com.example.flow.demo.dto.Response;
import com.example.flow.demo.utils.HttpClientUtil;
import com.example.flow.demo.utils.JsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhengjiexiang on 2018/9/5
 */
@Service
public class LogCollectServiceImpl implements LogCollectService<KibanaLogDto> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LogPersistService logPersistService;

    @Override
    public void collect() {

        List<String> traceIds = Lists.newArrayList();

        List<KibanaLogDto> kibanaLogDtos = Lists.newArrayList();
        try{
            HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
            String url = "http://192.168.136.118:9200/dic-*/_search?q=module:DIC-base-match-service&size=20";
            String res = httpClientUtil.sendHttpGet(url);
            System.out.println("----" + res);
            Response response = JsonUtils.json2Bean(res, Response.class);
            Response.Hits hits = response.getHits();
            if(hits != null) {
                kibanaLogDtos = hits.getHits();
            }
            if(!CollectionUtils.isEmpty(kibanaLogDtos)) {
                kibanaLogDtos.forEach(kibanaLogDto -> {
                    KibanaLogDto.Source source = kibanaLogDto.get_source();
                    if(source != null) {
                        KibanaLogDto.Items items = source.getItems();
                        if(items != null && !StringUtils.isEmpty(items.getTraceid()) && !traceIds.contains(items.getTraceid())) {
                            traceIds.add(items.getTraceid());
                            collect(items.getTraceid());
                        }
                    }
                });
            }
        }catch (Exception e) {
            logger.error("采集报错", e);
        }
    }

    @Override
    public void collect(String traceId) {
        List<KibanaLogDto> kibanaLogDtos = Lists.newArrayList();
        try{
            HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
            String url = "http://192.168.136.118:9200/dic-*/_search?q=items.traceId:" + traceId + "&size=1000";
            String res = httpClientUtil.sendHttpGet(url);
//            System.out.println("----" + res);
            Response response = JsonUtils.json2Bean(res, Response.class);
            Response.Hits hits = response.getHits();
            if(hits != null) {
                kibanaLogDtos = hits.getHits();
            }
            if(!CollectionUtils.isEmpty(kibanaLogDtos)) {
                parse(kibanaLogDtos);
            }
        }catch (Exception e) {
            logger.error("根据tracdId采集报错", e);
        }
    }

    @Override
    public void parse(List<KibanaLogDto> dtos) {
        Set<LogDto> logDtos = Sets.newHashSet();
        // 1、转实体，去重
        dtos.forEach(kibanaLogDto -> {
            KibanaLogDto.Source source = kibanaLogDto.get_source();
            if(source != null) {
                KibanaLogDto.Items items = source.getItems();
                if(items != null && !StringUtils.isEmpty(items.getTraceid())) {
                    LogDto logDto = source.buildLogDto();
                    if(logDto.vaild()) {
                        logDtos.add(logDto);
                    }
                }
            }
        });

        // 2、处理父子关系
        Map<String, LogDto> logDtoMap = Maps.newHashMap();
        logDtos.forEach(logDto -> {
            logDtoMap.put(logDto.getSpanId(), logDto);
        });
        logDtos.forEach(logDto -> {
            String pSpanId = logDto.getpSpanId();
            if(!StringUtils.isEmpty(pSpanId)) {
                LogDto parentLogDto = logDtoMap.get(pSpanId);
                if(parentLogDto == null) {
//                    logger.error("异常，未找到父SpanId模块" + pSpanId);
                    return;
                }
                parentLogDto.setChild(logDto);
                logDto.setParent(parentLogDto);
            }
        });

        // 3、获取到最下层的实体,可能存在多个
        List<LogDto> result = Lists.newArrayList();
        for(LogDto logDto : logDtos) {
            if(logDto.getChild() == null) {
                result.add(logDto);
            }
        }

        // 4、入库
        for(LogDto dto : result) {
            logPersistService.persist(dto);
        }

    }
}
